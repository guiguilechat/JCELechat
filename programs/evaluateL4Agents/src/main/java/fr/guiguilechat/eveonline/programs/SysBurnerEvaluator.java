package fr.guiguilechat.eveonline.programs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.yaml.Location;

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

	protected static class SystemData{
		public double avgDist;
		public double bonusSys;
		public double freqHS;
	}

	/**
	 * max distance to consider the systems
	 */
	public int distance;

	public final EveDatabase db;

	public SysBurnerEvaluator(int distance, EveDatabase db) {
		this.distance = distance;
		this.db = db;
	}

	protected final HashMap<String, SystemData> cache = new HashMap<>();

	/**
	 * get the avg distance you need to jump for a burner in given system
	 * @param sn
	 * @return
	 */
	public double avgDist(String sn) {
		return evaluate(sn).avgDist;
	}

	public double secBonus(String sn) {
		return evaluate(sn).bonusSys;
	}

	public double freqHS(String sn) {
		return evaluate(sn).freqHS;
	}

	protected SystemData evaluate(String sn) {
		if (cache.containsKey(sn)) {
			return cache.get(sn);
		}
		Location sys = db.getLocation(sn);

		SystemVisitor sv = new SystemVisitor(sys);
		visitSystemsWithDistance(sys, distance, sv);
		SystemData ret= new SystemData();
		ret.avgDist = sv.sumPHSjumps / sv.nbPHS;
		ret.bonusSys = 2 - sys.minSec;
		ret.freqHS = sv.nbPHS / sv.sumPonderations;
		System.err.println("system " + sys.name + " [nbpond" + sv.sumPonderations + " phsjumps" + sv.sumPHSjumps + " phs"
				+ sv.nbPHS + "] avgdistHS" + ret.avgDist + " bonus" + ret.bonusSys + " pbHigh" + ret.freqHS);
		cache.put(sn, ret);
		return ret;
	}

	/**
	 *
	 * @param Origin
	 * @param maxJumps
	 * @param sv
	 */
	public void visitSystemsWithDistance(Location Origin, int maxJumps, SystemVisitor sv) {
		// distance through high-sec to system
		HashMap<Location, Integer> hsDistances = new HashMap<>();
		// distance through non-high sec to systems
		HashMap<Location, Integer> lnsDistances = new HashMap<>();
		int jumps = 0;
		HashSet<Location> nextHSLocations = new HashSet<>(Arrays.asList(Origin));
		HashSet<Location> nextLNSLocations = new HashSet<>();
		while (jumps <= maxJumps) {
			HashSet<Location> futHSLocations = new HashSet<>();
			HashSet<Location> futLNSLocations = new HashSet<>();

			// for each new system we can reach through HS
			for (Location loc : nextHSLocations) {
				if (hsDistances.containsKey(loc) || isSystemIgnored(loc)) {
					continue;
				}
				if (loc.hasHighSec()) {
					for (String adjname : loc.adjacentSystems) {
						Location adj = db.getLocation(adjname);
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
							Location adj = db.getLocation(adjname);
							if (adj != null) {
								futLNSLocations.add(adj);
							}
						}
					}
				}
			}
			// for each new system we reach from low or null sec
			for (Location loc : nextLNSLocations) {
				if (hsDistances.containsKey(loc) || lnsDistances.containsKey(loc) || isSystemIgnored(loc)) {
					continue;
				}
				lnsDistances.put(loc, jumps);
				for (String adjname : loc.adjacentSystems) {
					Location adj = db.getLocation(adjname);
					if (adj != null) {
						futLNSLocations.add(adj);
					}
				}
			}

			nextHSLocations = futHSLocations;
			nextLNSLocations = futLNSLocations;
			jumps++;
		}

		for (Entry<Location, Integer> e : hsDistances.entrySet()) {
			sv.acceptSystem(e.getKey(), e.getValue(), true);
		}

		for (Entry<Location, Integer> e : lnsDistances.entrySet()) {
			sv.acceptSystem(e.getKey(), e.getValue(), false);
		}

	}

	boolean ignoreHubs = true;
	protected static final HashSet<String> hubs = new HashSet<>(Arrays.asList("Jita", "Hek", "Amarr", "Dodixie"));

	/**
	 * is a system forbiden to jump through ? basically true iff sys is trade hub
	 *
	 * @param loc
	 * @return
	 */
	public boolean isSystemIgnored(Location loc) {
		if (ignoreHubs) {
			return hubs.contains(loc.name);
		} else {
			return false;
		}
	}

	public class SystemVisitor {

		public final Location origin;
		protected Set<String> adjacentConstels;

		public SystemVisitor(Location origin) {
			this.origin = origin;
			adjacentConstels = new HashSet<>(Arrays.asList(db.getLocation(origin.parentConstellation).adjacentConstels));
		}

		// a system in same const is ponderated 1, in next
		// const is ponderated 4
		public double pondAdjConstel = 3;
		public double pondSameConstel = 1;

		// we make a ponderated sum of jumps in HS and HS systems.
		public double sumPHSjumps = 0, sumPonderations = 0, nbPHS;

		public void acceptSystem(Location loc, int dst, boolean hasHSRoute) {
			double pond = 0;
			if (loc.parentConstellation.equals(origin.parentConstellation)) {
				pond = pondSameConstel;
			} else if(adjacentConstels.contains(loc.parentConstellation)) {
				pond = pondAdjConstel;
			}
			// pond is set to 0 if ignored system, eg more than 1 constel jump
			if (pond != 0) {
				sumPonderations += pond;
				// if the system is not in HS we count it as decreasing the probability
				// to
				// play the burner, and we dont consider it has increasing the avg
				// distance
				if (hasHSRoute) {
					sumPHSjumps += pond * dst;
					nbPHS += pond;
				}
			}
		}
	}

}
