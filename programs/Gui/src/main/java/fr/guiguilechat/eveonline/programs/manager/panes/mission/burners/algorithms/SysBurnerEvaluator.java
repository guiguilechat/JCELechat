package fr.guiguilechat.eveonline.programs.manager.panes.mission.burners.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.sde.locations.Constellation;
import fr.guiguilechat.eveonline.model.sde.locations.SolarSystem;

/**
 * rank a high-sec system for an agent with burner missions
 * <ul>
 * <li>the true sec multiplies the reward of agent.</li>
 * <li>the number of jumps to the next constellation systems increase the delay
 * to missions</li>
 * <li>low/null system in next constel reduce the missions that are
 * available</li>
 * </ul>
 *
 *
 * @author guigui lechat
 *
 */
public class SysBurnerEvaluator {

	private static final Logger logger = LoggerFactory.getLogger(SysBurnerEvaluator.class);

	/**
	 *
	 * informations on a system
	 */
	protected static class SystemData {
		public double burnerAvgDist;
		public double constelAvgDist;
		public double bonusTrueSec;
		public double freqHS;
	}

	public static boolean constelHasMarket(SolarSystem loc) {
		switch (loc.constellation) {
		case "Barvigrad":
		case "Coriault":
		case "Kimotoro":
		case "Ortner":
		case "ThroneWorlds":
			return true;
		default:
			return false;
		}
	}

	/**
	 * max distance to consider the systems
	 */
	public int distance;

	public SysBurnerEvaluator(int distance) {
		this.distance = distance;
	}

	protected final HashMap<String, SystemData> cache = new HashMap<>();

	public void clearCache() {
		cache.clear();
	}

	public SystemData evaluate(String sn) {
		synchronized (cache) {
			if (cache.containsKey(sn)) {
				return cache.get(sn);
			}
			SolarSystem sys = SolarSystem.load().get(sn);

			SystemVisitor sv = new SystemVisitor(sys);
			visitSystemsWithDistance(sys, distance, sv);
			SystemData ret = new SystemData();
			ret.burnerAvgDist = sv.sumWHSjumps / sv.sumWHS;
			ret.constelAvgDist = sv.sumWHSConstJumps / sv.sumWeightHSConst;
			ret.bonusTrueSec = 1.63 - sys.truesec;
			ret.freqHS = sv.sumWHS / sv.sumWeight;
			logger
			.trace("system " + sys.name + " avgdistHS" + ret.burnerAvgDist + " bonus" + ret.bonusTrueSec + " pbHigh" + ret.freqHS);
			cache.put(sn, ret);
			return ret;
		}
	}

	/**
	 *
	 * @param Origin
	 * @param maxJumps
	 * @param sv
	 */
	protected void visitSystemsWithDistance(SolarSystem Origin, int maxJumps, SystemVisitor sv) {
		// distance through high-sec to system
		HashMap<SolarSystem, Integer> hsDistances = new HashMap<>();
		// distance through non-high sec to systems
		HashMap<SolarSystem, Integer> lnsDistances = new HashMap<>();
		int jumps = 0;
		HashSet<SolarSystem> nextHSLocations = new HashSet<>(Arrays.asList(Origin));
		HashSet<SolarSystem> nextLNSLocations = new HashSet<>();
		while (jumps <= maxJumps) {
			HashSet<SolarSystem> futHSLocations = new HashSet<>();
			HashSet<SolarSystem> futLNSLocations = new HashSet<>();

			// for each new system we can reach through HS
			for (SolarSystem loc : nextHSLocations) {
				if (hsDistances.containsKey(loc)) {
					continue;
				}
				if (loc.isHS()) {
					for (String adjname : loc.adjacentSystems) {
						SolarSystem adj = SolarSystem.load().get(adjname);
						if (adj != null) {
							futHSLocations.add(adj);
						}
					}
					hsDistances.put(loc, jumps);
				} else {
					if (lnsDistances.containsKey(loc)) {
						continue;
					} else {
						lnsDistances.put(loc, jumps);
						for (String adjname : loc.adjacentSystems) {
							SolarSystem adj = SolarSystem.load().get(adjname);
							if (adj != null) {
								futLNSLocations.add(adj);
							}
						}
					}
				}
			}
			// for each new system we reach from low or null sec
			for (SolarSystem loc : nextLNSLocations) {
				if (hsDistances.containsKey(loc) || lnsDistances.containsKey(loc)) {
					continue;
				}
				lnsDistances.put(loc, jumps);
				for (String adjname : loc.adjacentSystems) {
					SolarSystem adj = SolarSystem.load().get(adjname);
					if (adj != null) {
						futLNSLocations.add(adj);
					}
				}
			}

			nextHSLocations = futHSLocations;
			nextLNSLocations = futLNSLocations;
			jumps++;
		}

		for (Entry<SolarSystem, Integer> e : hsDistances.entrySet()) {
			sv.acceptSystem(e.getKey(), e.getValue(), true);
		}
		for (Entry<SolarSystem, Integer> e : lnsDistances.entrySet()) {
			sv.acceptSystem(e.getKey(), e.getValue(), false);
		}

	}

	// weight data. we weight a system in a constellation depending on the
	// distance in constels we have to jump


	protected double weightSameConstel = 1;

	public SysBurnerEvaluator withWeightSameConstel(double w) {
		weightSameConstel = w;
		clearCache();
		return this;
	}

	protected double weightAdjConstel = 1;

	public SysBurnerEvaluator withWeightAdjConstel(double w) {
		weightAdjConstel = w;
		clearCache();
		return this;
	}

	// if constel has a hub we divide the weight by given value
	protected double multWeightHub = 1;

	public SysBurnerEvaluator withMultWeightHub(double w) {
		multWeightHub = w;
		clearCache();
		return this;
	}

	public class SystemVisitor {

		public final SolarSystem origin;
		protected Set<String> adjacentConstels;

		public SystemVisitor(SolarSystem origin) {
			this.origin = origin;
			adjacentConstels = Constellation.load().get(origin.constellation).adjacentConstellations.stream()
					.collect(Collectors.toSet());
		}

		// we make a ponderated sum of jumps in HS and HS systems.
		public double sumWHSjumps = 0, sumWeight = 0, sumWHS, sumWHSConstJumps = 0, sumWeightHSConst = 0;


		public void acceptSystem(SolarSystem loc, int dst, boolean hasHSRoute) {
			double sysWeight = 0;
			if (!constelHasMarket(loc)) {
				if (loc.equals(origin)) {
					sysWeight = 1;
					sumWeightHSConst += 1;
				} else if (loc.constellation.equals(origin.constellation)) {
					sysWeight = weightSameConstel;
					sumWeightHSConst += weightSameConstel;
					sumWHSConstJumps += weightSameConstel * dst;
				} else if (adjacentConstels.contains(loc.constellation)) {
					sysWeight = weightAdjConstel;
				}
			}
			// set weight to 0 if ignored system, eg a hub
			if (sysWeight != 0) {
				if (constelHasMarket(loc)) {
					sysWeight *= multWeightHub;
					// System.err.println("system " + loc.name + " has hub in constelation
					// so weight is " + sysWeight);
				}
				sumWeight += sysWeight;
				// if the system is not in HS we count it as decreasing the probability
				// to play the burner, and we dont consider it has increasing the avg
				// distance
				if (hasHSRoute) {
					sumWHSjumps += sysWeight * dst;
					sumWHS += sysWeight;
				}
			}
		}
	}

}
