package fr.guiguilechat.jcelechat.model.sde.locations.route;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;
import fr.guiguilechat.jcelechat.model.sde.translate.Invasions;

/**
 * Router that try to respect a specific predicate on the system between the
 * origin and the dest. Since distances between systems are 1, is an unwedigthed
 * graph , and BFS is used rather than Dijkstra for faster algo and less memory
 * usage.
 *
 */
public class PredicateRouter implements IRouter {

	private final Predicate<SolarSystem> predicate;

	public PredicateRouter(Predicate<SolarSystem> predicate) {
		this.predicate = predicate;
	}

	private static final Set<SolarSystem> INVADED = Invasions.INSTANCE.getDangerousSystems();
	private static final Set<SolarSystem> SECREDUCED = Invasions.INSTANCE.getBadsecSystems();

	/**
	 * only accept intermediate systems between source and dest that in HS, and
	 * not invaded besides Fortress
	 */
	public static final PredicateRouter HSNOINVASION = new PredicateRouter(
			s -> s.secStatus() == SECSTATUS.HS && !INVADED.contains(s));
	/**
	 * Only accept intermediates systems that are in HS after modification from
	 * the trigs (so avoid liminal)
	 */
	public static final PredicateRouter HS = new PredicateRouter(
			s -> s.secStatus() == SECSTATUS.HS && !SECREDUCED.contains(s));

	public static final PredicateRouter LS = new PredicateRouter(s -> s.secStatus() == SECSTATUS.LS);
	public static final PredicateRouter NS = new PredicateRouter(s -> s.secStatus() == SECSTATUS.NS);
	public static final PredicateRouter KS = new PredicateRouter(
			s -> s.secStatus() == SECSTATUS.HS || s.secStatus() == SECSTATUS.LS
			|| s.secStatus() ==SECSTATUS.NS);

	/**
	 * Node of the tree of systems reachable from another one.
	 *
	 */
	public static class DisTree {

		/**
		 * parent node in the tree
		 */
		public int parent;

		/**
		 * distance from the root system.
		 */
		public int dist;

		public DisTree(int parent, int distance) {
			this.parent = parent;
			dist = distance;
		}
	}

	/**
	 * BFS algo to explore the systems to get their distance and parent until a
	 * given system is reached. This is used to find a route.
	 *
	 * @param idFrom
	 *          starting system
	 * @param idTo
	 *          stop system. When reached, no more system is searched for.
	 * @return
	 */
	protected Map<Integer, DisTree> explore(int idFrom, int idTo) {
		Map<Integer, DisTree> ret = new HashMap<>();
		ret.put(idFrom, new DisTree(idFrom, 0));
		Set<Integer> next = new HashSet<>(Arrays.asList(idFrom));
		int dist = 1;
		while (!next.isEmpty()) {
			Set<Integer> step = next;
			next = new HashSet<>();
			for (int sid : step) {
				SolarSystem ss = SolarSystem.getSystem(sid);
				for (String sn : ss.adjacentSystems) {
					SolarSystem adj = SolarSystem.getSystem(sn);
					if (adj.id == idTo) {
						ret.put(adj.id, new DisTree(sid, dist));
						return ret;
					}
					if (!ret.containsKey(adj.id) && predicate.test(adj)) {
						ret.put(adj.id, new DisTree(sid, dist));
						next.add(adj.id);
					}
				}
			}
			dist++;
		}
		return ret;
	}

	/**
	 * iterate over the parent of the destination until finds the root system, and
	 * return the reversed list.
	 *
	 * @param idFrom
	 * @param idTo
	 * @param explored
	 * @return
	 */
	protected static int[] route(int idFrom, int idTo, Map<Integer, DisTree> explored) {
		List<Integer> ret = new LinkedList<>();
		for (int next = idTo; next != idFrom && explored.containsKey(next); next = explored.get(next).parent) {
			ret.add(0, next);
		}
		return ret.stream().mapToInt(i -> i).toArray();
	}

	@Override
	public int[] getRoute(int idFrom, int idTo) {
		return route(idFrom, idTo, explore(idFrom, idTo));
	}

}
