package fr.guiguilechat.eveonline.model.database.locations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.database.EveDatabase;
import fr.guiguilechat.eveonline.model.database.yaml.Location;

/**
 * compute and cache distances between locations
 *
 */
public class Distances {

	public final EveDatabase db;

	public Distances(EveDatabase db) {
		this.db = db;
	}

	protected HashMap<String, HashMap<String, Integer>> cachedSysDistances = new HashMap<>();
	protected HashMap<String, HashMap<String, Integer>> cachedConstelDistances = new HashMap<>();

	/**
	 * get all systems in a system/constellation/region
	 *
	 * @param locname
	 *          the name of the location
	 * @return for a system, itself ; for a constellation or region, its systems.
	 */
	public Set<Location> getAllSystems(String locname) {
		Location l1 = db.getLocation(locname);
		if (l1 == null) {
			System.err.println("null location "+locname);
		}
		if (l1.parentRegion == null) {
			return findSystemsInRegion(l1.name);
		} else if (l1.parentConstellation == null) {
			return findSystemsInConstel(l1.name);
		} else {
			return new HashSet<>(Arrays.asList(l1));
		}
	}

	/**
	 *
	 * get all constellations in a system/constellation/region
	 *
	 * @param locname
	 *          the name of the location
	 * @return for system its constel ; for constel itself ; for region the set of
	 *         its constellations
	 */
	public Set<Location> getAllConstels(String locname) {
		Location l1 = db.getLocation(locname);
		if (l1.parentRegion == null) {
			return findConstelsInRegion(l1.name);
		} else if (l1.parentConstellation == null) {
			return new HashSet<>(Arrays.asList(l1));
		} else {
			return new HashSet<>(Arrays.asList(db.getLocations().get(l1.parentConstellation)));
		}
	}

	/**
	 * @param loc1n
	 *          the name of a system/constelation/region
	 * @param loc2n
	 *          the name of a system/constelation/region
	 * @return the minimum distance, in jumps, to make to reach loc2 from loc1
	 */
	public int distJumps(String loc1n, String loc2n) {
		if (loc1n == null || loc2n == null) {
			return Integer.MAX_VALUE;
		}
		// keep them in order to avoid computing twice a-b AND b-a
		if (loc1n.compareTo(loc2n) > 0) {
			String tmp = loc1n;
			loc1n = loc2n;
			loc2n = tmp;
		}
		HashMap<String, Integer> cache = cachedSysDistances.get(loc1n);
		if (cache == null) {
			cache = new HashMap<>();
			cachedSysDistances.put(loc1n, cache);
		} else {
			if (cache.containsKey(loc2n)) {
				return cache.get(loc2n);
			}
		}
		Set<Location> l1s = getAllSystems(loc1n);
		Set<Location> l2s = getAllSystems(loc2n);

		int dst = distJumps(l1s, l2s);
		cache.put(loc2n, dst);
		return dst;
	}

	/**
	 *
	 * @param locs1
	 *          set of system locations
	 * @param locs2
	 *          set of system locations
	 * @return
	 */
	public int distJumps(Set<Location> locs1, Set<Location> locs2) {
		// dont consider we can jump to a system in this set
		Set<Location> avoid = new HashSet<>(locs1);

		// iterate over the number of jumps
		int jumps = 0;

		// which systems we can reach within exactly "jumps" jumps ?
		Set<Location> atDistance = locs1;

		while (!atDistance.isEmpty()) {
			for (Location loc : atDistance) {
				if (locs2.contains(loc)) {
					return jumps;
				}
			}
			Set<Location> nextJump = new HashSet<>();
			// add all the system adjacent to those in nextIteration and not done and
			// not in nextIteration
			for (Location loc : atDistance) {
				Stream.of(loc.adjacentSystems).map(db::getLocation).filter(l -> !avoid.contains(l))
				.forEach(nextJump::add);
			}

			jumps++;
			avoid.addAll(nextJump);
			atDistance = nextJump;
		}
		return Integer.MAX_VALUE;
	}

	public int distConstels(String loc1n, String loc2n) {
		if (loc1n == null || loc2n == null) {
			return Integer.MAX_VALUE;
		}
		// keep them in order to avoid computing twice a-b AND b-a
		if (loc1n.compareTo(loc2n) > 0) {
			String tmp = loc1n;
			loc1n = loc2n;
			loc2n = tmp;
		}
		HashMap<String, Integer> cache = cachedConstelDistances.get(loc1n);
		if (cache == null) {
			cache = new HashMap<>();
			cachedConstelDistances.put(loc1n, cache);
		} else {
			if (cache.containsKey(loc2n)) {
				return cache.get(loc2n);
			}
		}
		Set<Location> l1s = getAllConstels(loc1n);
		Set<Location> l2s = getAllConstels(loc2n);

		int dst = distConstels(l1s, l2s);
		cache.put(loc2n, dst);
		return dst;
	}

	/**
	 *
	 * @param locs1
	 *          set of constel locations
	 * @param locs2
	 *          set of constel locations
	 * @return
	 */
	public int distConstels(Set<Location> locs1, Set<Location> locs2) {
		// System.err.println("constrl dist from " + locs1.stream().map(l ->
		// l.name).collect(Collectors.toSet()) + " to "
		// + locs2.stream().map(l -> l.name).collect(Collectors.toSet()));
		// dont consider we can jump to a system in this set
		Set<Location> avoid = new HashSet<>(locs1);

		// iterate over the number of jumps
		int jumps = 0;

		// which constels we can reach within exactly "jumps" jumps ?
		Set<Location> atDistance = locs1;

		while (!atDistance.isEmpty()) {
			for (Location loc : atDistance) {
				if (locs2.contains(loc)) {
					return jumps;
				}
			}
			Set<Location> nextJump = new HashSet<>();
			// add all the system adjacent to those in nextIteration and not done and
			// not in nextIteration
			for (Location loc : atDistance) {
				Stream.of(loc.adjacentConstels).map(db::getLocation).filter(l -> !avoid.contains(l))
				.forEach(nextJump::add);
			}

			jumps++;
			avoid.addAll(nextJump);
			atDistance = nextJump;
		}
		return Integer.MAX_VALUE;
	}

	public double avgDistWithinSys(Location system, int maxSysJumps) {
		int total = 0;
		int totaljumps = 0;

		Set<Location> atDistance = new HashSet<>(Arrays.asList(system));
		HashSet<Location> done = new HashSet<>(Arrays.asList(system));
		for (int jumps = 0; jumps <= maxSysJumps; jumps++) {
			total += atDistance.size();
			totaljumps += atDistance.size() * jumps;

			Set<Location> nextJump = new HashSet<>();
			// add all the system adjacent to those in nextIteration and not done and
			// not in nextIteration
			for (Location loc : atDistance) {
				Stream.of(loc.adjacentSystems).map(db::getLocation).filter(l -> !done.contains(l))
				.forEach(nextJump::add);
			}
			done.addAll(nextJump);
			atDistance = nextJump;

		}

		return totaljumps * 1.0 / total;
	}

	public Set<Location> systemsAtDistance(Location system, int jumps) {
		Set<Location> systemDone = new HashSet<>(Arrays.asList(system));
		Set<Location> nextIteration = new HashSet<>(Arrays.asList(system));
		for (int jump = 1; jump <= jumps; jump++) {
			Set<Location> iterationAdjacent = new HashSet<>();
			// add all the system adjacent to those in nextIteration and not done
			for (Location loc : nextIteration) {
				Stream.of(loc.adjacentSystems).map(db::getLocation).filter(l -> !systemDone.contains(l))
				.forEach(iterationAdjacent::add);
			}
			systemDone.addAll(iterationAdjacent);
			nextIteration = iterationAdjacent;
		}
		return nextIteration;
	}

	public Set<Location> systemsAtDistance(String locname, int jumps) {
		return systemsAtDistance(db.getLocation(locname), jumps);
	}

	/**
	 *
	 * @param system
	 *          a system location
	 * @param maxJumps
	 *          number of constel jumps allowed
	 * @return the number of average system jumps from the given system to any
	 *         system within maxJump constels
	 */
	public double avgDistWithinConstels(Location system, int maxConstelJumps) {

		// get all the constellations within range in done
		Set<Location> atDistance = new HashSet<>(Arrays.asList(db.getLocation(system.parentConstellation)));
		HashSet<Location> done = new HashSet<>(Arrays.asList(db.getLocation(system.parentConstellation)));
		for (int cstlJumps = 0; cstlJumps <= maxConstelJumps; cstlJumps++) {
			Set<Location> nextJump = new HashSet<>();
			for (Location loc : atDistance) {
				Stream.of(loc.adjacentConstels).map(db.getLocations()::get).filter(l -> !done.contains(l))
				.forEach(nextJump::add);
			}
			done.addAll(nextJump);
			atDistance = nextJump;
		}

		Set<Location> systems = done.stream().flatMap(l -> findSystemsInConstel(l.name).stream())
				.collect(Collectors.toSet());
		int totaljumps = systems.stream().mapToInt(l -> distJumps(l.name, system.name)).sum();
		return totaljumps * 1.0 / systems.size();
	}

	public Set<Location> findSystemsInRegion(String regionName) {
		return db.getLocations().values().stream()
				.filter(l -> l.parentConstellation != null && regionName.equals(l.parentRegion))
				.collect(Collectors.toSet());
	}

	public Set<Location> findSystemsInConstel(String constelName) {
		return db.getLocations().values().stream().filter(l -> constelName.equals(l.parentConstellation))
				.collect(Collectors.toSet());
	}

	public Set<Location> findConstelsInRegion(String regionName) {
		return db.getLocations().values().stream()
				.filter(l -> l.parentConstellation == null && regionName.equals(l.parentRegion)).collect(Collectors.toSet());
	}

}
