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
		// average jumps to do for a burner mission
		public double burnerAvgJumps;
		// average jumps to go in a system in SAME constel
		public double constelAvgJumps;
		// multiplier of reward
		public double bonusTrueSec;
		// probability for burners to go in HS
		public double freqHSBurner;
	}

	public static boolean constelHasMarket(SolarSystem loc) {
		switch (loc.constellation) {
		case "Barvigrard":
		case "Coriault":
		case "Kimotoro":
		case "Ortner":
		case "ThroneWorlds":
			return true;
		default:
			return false;
		}
	}

	public static boolean systemIsMarket(SolarSystem loc) {
		switch (loc.name) {
		case "Jita":
		case "Amarr":
		case "Dodixie":
		case "Hek":
		case "Rens":
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
			ret.burnerAvgJumps = sv.sumWHSjumps / sv.sumWHS;
			ret.constelAvgJumps = sv.sumWHSConstJumps / sv.sumWeightHSConst;
			ret.bonusTrueSec = 1.63 - sys.truesec;
			ret.freqHSBurner = sv.sumWHS / sv.sumWeight;
			logger
			.trace("system " + sys.name + " avgdistHS" + ret.burnerAvgJumps + " bonus" + ret.bonusTrueSec + " pbHigh"
					+ ret.freqHSBurner);
			cache.put(sn, ret);
			return ret;
		}
	}

	/**
	 *
	 * @param origin
	 * @param maxJumps
	 * @param sv
	 */
	protected void visitSystemsWithDistance(SolarSystem origin, int maxJumps, SystemVisitor sv) {
		Set<String> allowedConstels = new HashSet<>();
		allowedConstels.add(origin.constellation);
		allowedConstels.addAll(Constellation.load().get(origin.constellation).adjacentConstellations);
		// distance through high-sec to system
		HashMap<SolarSystem, Integer> hsDistances = new HashMap<>();
		// distance through non-high sec to systems
		HashMap<SolarSystem, Integer> lnsDistances = new HashMap<>();
		int jumps = 0;
		HashSet<SolarSystem> nextHSLocations = new HashSet<>(Arrays.asList(origin));
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
						if (adj != null && allowedConstels.contains(adj.constellation)) {
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
							if (adj != null && allowedConstels.contains(adj.constellation)) {
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
					if (adj != null && allowedConstels.contains(adj.constellation)) {
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

	// if constel has a hub we multiply the weight by this value
	protected double weightMarketConstelMult = 0.1;

	public SysBurnerEvaluator withMultWeightHub(double w) {
		weightMarketConstelMult = w;
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
		public double sumWHSjumps = 0, sumWeight = 0, sumWHS = 0;
		// sum(dist * weight) for systems in same constellation
		public double sumWHSConstJumps = 0;
		public double sumWeightHSConst = 0;


		public void acceptSystem(SolarSystem loc, int dst, boolean hasHSRoute) {
			double sysWeight = 0;

			if (!systemIsMarket(loc)) {
				sysWeight = constelHasMarket(loc) ? weightMarketConstelMult : 1;
				if (loc.constellation.equals(origin.constellation)) {
					if (loc.equals(origin)) {
						// same system, multiply weight by 1.
					} else {
						sysWeight *= weightSameConstel;
					}
					sumWeightHSConst += sysWeight;
					sumWHSConstJumps += sysWeight * dst;
				} else if (adjacentConstels.contains(loc.constellation)) {
					sysWeight *= weightAdjConstel;
				}
				sumWeight += sysWeight;
				if (hasHSRoute) {
					sumWHSjumps += sysWeight * dst;
					sumWHS += sysWeight;
				}
			}
		}
	}

}
