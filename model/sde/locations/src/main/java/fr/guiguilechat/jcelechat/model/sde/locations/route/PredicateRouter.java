package fr.guiguilechat.jcelechat.model.sde.locations.route;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.Invasions;
import fr.guiguilechat.jcelechat.model.sde.locations.Invasions.JsonEntry.STATUS;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;

/**
 * Router that try to respect a specific predicate on the system between the
 * origin and the dest. Since distances between systems are 1, is an unwedigthed
 * graph , and BFS is used rather than Dijkstra for faster algo and less memory
 * usage.
 *
 */
public class PredicateRouter implements IRouter {

	public final Predicate<SolarSystem> predicate;

	public PredicateRouter(Predicate<SolarSystem> predicate) {
		this.predicate = predicate;
	}

// //
// // only HS, no invasion. Good for negative to both
// //
//
// private static final Set<SolarSystem> INVADED =
// Invasions.INSTANCE.getPointSystems(false, false);
//
// /**
// * only accept intermediate systems between source and dest that in HS, and
// * not invaded
// */
// public static final PredicateRouter HSNOINVASION = new PredicateRouter(
// s -> s.secStatus() == SECSTATUS.HS && !INVADED.contains(s));

	//
	// only HS, no sec reduced. good for neutral to both.
	//

	private static final Set<SolarSystem> SECREDUCED = Invasions.INSTANCE.getReducedSystems();

	/**
	 * Only accept intermediates systems that are in HS after modification from
	 * the trigs (so avoid liminality)
	 */
	public static final PredicateRouter HS = new PredicateRouter(
			s -> s.secStatus() == SECSTATUS.HS && !SECREDUCED.contains(s));

	//
	// only fortress. To be sure the system does not change.
	//
	private static final Map<SolarSystem, STATUS> SEC2STATUS = Invasions.INSTANCE.getSystems2Status();

	/**
	 * only accept systems that are in HS, not invaded or invaded in state
	 * fotress.
	 */
	public static final PredicateRouter HSONLYFORTRESS = new PredicateRouter(
			s -> s.secStatus() == SECSTATUS.HS && SEC2STATUS.getOrDefault(s, STATUS.FORTRESS) == STATUS.FORTRESS);

	//
	// only HS and good for edencom. Goof for dencom friendly
	//

	private static final Set<SolarSystem> DANGEROUS2DENCOM = Invasions.INSTANCE.getDangerousHSSystems(false, true);
	/**
	 * only accept systems that are in HS, not invaded or invaded in state
	 * fotress.
	 */
	public static final PredicateRouter HSDENCOM = new PredicateRouter(
			s -> s.secStatus() == SECSTATUS.HS && !DANGEROUS2DENCOM.contains(s));

	//
	// only HS and good for trig. Good for trig friendly.
	//

	private static final Set<SolarSystem> DANGEROUS2TRIG = Invasions.INSTANCE.getDangerousHSSystems(true, false);
	/**
	 * only accept systems that are in HS, and if invaded not sec reduced and trig
	 * winning.
	 */
	public static final PredicateRouter HSTRIGLAVIAN = new PredicateRouter(
			s -> s.secStatus() == SECSTATUS.HS && !DANGEROUS2TRIG.contains(s));

	//
	// only go through LS
	//

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
