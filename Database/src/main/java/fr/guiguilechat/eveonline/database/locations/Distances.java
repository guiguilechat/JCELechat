package fr.guiguilechat.eveonline.database.locations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.yaml.Location;

/**
 * compute and cache distances between locations
 *
 */
public class Distances {

	public final EveDatabase db;

	public Distances(EveDatabase db) {
		this.db = db;
	}

	protected HashMap<String, HashMap<String, Integer>> cachedDistances = new HashMap<>();

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
		HashMap<String, Integer> cache = cachedDistances.get(loc1n);
		if (cache == null) {
			cache = new HashMap<>();
			cachedDistances.put(loc1n, cache);
		} else {
			if (cache.containsKey(loc2n)) {
				return cache.get(loc2n);
			}
		}
		Location l1 = db.getLocations().get(loc1n);
		Location l2 = db.getLocations().get(loc2n);
		Set<Location> l1s;
		Set<Location> l2s;
		if (l1.parentRegion == null) {
			l1s = findLocationsInRegion(l1.name);
		} else if (l1.parentConstelation == null) {
			l1s = findLocationsInConstel(l1.name);
		} else {
			l1s = new HashSet<>();
			l1s.add(l1);
		}
		if (l2.parentRegion == null) {
			l2s = findLocationsInRegion(l2.name);
		} else if (l2.parentConstelation == null) {
			l2s = findLocationsInConstel(l2.name);
		} else {
			l2s = new HashSet<>();
			l2s.add(l2);
		}
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
				Stream.of(loc.adjacentSystems).map(db.getLocations()::get).filter(l -> !avoid.contains(l))
				.forEach(nextJump::add);
			}

			jumps++;
			avoid.addAll(nextJump);
			atDistance = nextJump;
		}

		return Integer.MAX_VALUE;
	}

	public double avgDistWithin(Location system, int maxJumps) {
		int total = 0;
		int totaljumps = 0;

		Set<Location> atDistance = new HashSet<>(Arrays.asList(system));
		HashSet<Location> done = new HashSet<>(Arrays.asList(system));
		for (int jumps = 0; jumps <= maxJumps; jumps++) {
			total += atDistance.size();
			totaljumps += atDistance.size() * jumps;

			Set<Location> nextJump = new HashSet<>();
			// add all the system adjacent to those in nextIteration and not done and
			// not in nextIteration
			for (Location loc : atDistance) {
				Stream.of(loc.adjacentSystems).map(db.getLocations()::get).filter(l -> !done.contains(l))
						.forEach(nextJump::add);
			}
			done.addAll(nextJump);
			atDistance = nextJump;

		}

		return totaljumps * 1.0 / total;
	}

	public Set<Location> findLocationsInRegion(String regionName) {
		return db.getLocations().values().stream().filter(l -> regionName.equals(l.parentRegion))
				.collect(Collectors.toSet());
	}

	public Set<Location> findLocationsInConstel(String constelName) {
		return db.getLocations().values().stream().filter(l -> constelName.equals(l.parentConstelation))
				.collect(Collectors.toSet());
	}

}
