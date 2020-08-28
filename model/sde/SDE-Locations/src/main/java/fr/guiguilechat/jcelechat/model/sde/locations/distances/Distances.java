package fr.guiguilechat.jcelechat.model.sde.locations.distances;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;
import fr.guiguilechat.jcelechat.model.sde.locations.route.IRouter;
import fr.guiguilechat.jcelechat.model.sde.locations.route.PredicateRouter;

/**
 * compute and cache distances between locations
 *
 */
public class Distances {

	protected HashMap<String, HashMap<String, Integer>> cachedSysDistances = new HashMap<>();
	protected HashMap<String, HashMap<String, Integer>> cachedConstelDistances = new HashMap<>();

	/**
	 * @param sys1
	 * @param sys2
	 * @return the minimum distance, in jumps, to make to reach sys1 from sys2
	 */
	public int between(SolarSystem sys1, SolarSystem sys2) {
		if (sys1 == null || sys2 == null) {
			return Integer.MAX_VALUE;
		}
		// keep them in order to avoid computing twice a-b AND b-a
		if (sys1.name.compareTo(sys2.name) > 0) {
			SolarSystem tmp = sys1;
			sys1 = sys2;
			sys2 = tmp;
		}
		HashMap<String, Integer> cache = cachedSysDistances.get(sys1.name);
		if (cache == null) {
			cache = new HashMap<>();
			cachedSysDistances.put(sys1.name, cache);
		} else {
			if (cache.containsKey(sys2.name)) {
				return cache.get(sys2.name);
			}
		}

		int dst = distJumps(sys1, sys2);
		cache.put(sys2.name, dst);
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
	public int distJumps(SolarSystem sys1, SolarSystem sys2) {
		// dont consider we can jump to a system in this set
		Set<SolarSystem> visited = new HashSet<>(Arrays.asList(sys1));

		// iterate over the number of jumps
		int jumps = 0;

		// which systems we can reach within exactly "jumps" jumps
		Set<SolarSystem> atDistance = new HashSet<>(Arrays.asList(sys1));

		while (!atDistance.isEmpty()) {
			if (atDistance.contains(sys2)) {
				return jumps;
			}
			Set<SolarSystem> nextJump = new HashSet<>();
			// add all the system adjacent to those in nextIteration and not done and
			// not in nextIteration
			for (SolarSystem loc : atDistance) {
				loc.adjacentSystems.stream().map(SolarSystem.load()::get).filter(l -> !visited.contains(l))
				.forEach(nextJump::add);
			}

			jumps++;
			visited.addAll(nextJump);
			atDistance = nextJump;
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * make the matrix of distances for a set of indexes system, using a router.
	 *
	 * @param idx
	 *          indexed systems
	 * @param router
	 *          method to get the distances between two systems. if set to null,
	 *          then {@link PredicateRouter#HSNOINVASION}, which is the most
	 *          restrictive one, is used.
	 * @return a new matrix of the distances, so symmetrical with diag 0, with
	 *         ret[i][j] = distance from idx.system(i) to id.system(j) according
	 *         to the router.
	 */
	public static int[][] of(SysIndex idx, IRouter router) {
		if (router == null) {
			router = PredicateRouter.HSNOINVASION;
		}
		int[][] jumps = new int[idx.size()][idx.size()];
		for (int i = 0; i < idx.size(); i++) {
			SolarSystem from = idx.system(i);
			jumps[i][i] = 0;
			for (int j = i + 1; j < idx.size(); j++) {
				SolarSystem to = idx.system(j);
				int dist = router.getRoute(from.id, to.id).length;
				jumps[i][j] = jumps[j][i] = dist;
			}
		}
		return jumps;
	}

}
