package fr.guiguilechat.jcelechat.model.sde.locations.route;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;

/**
 * Router that try to respect a specific set of sec status, in addition to the
 * source and destination systems' status
 *
 */
public class SecStatusRouter implements IRouter {

	private final SECSTATUS[] allowedStatus;

	public SecStatusRouter(SECSTATUS... allowedStatus) {
		this.allowedStatus = allowedStatus;
	}

	public static final SecStatusRouter HS = new SecStatusRouter(SECSTATUS.HS);
	public static final SecStatusRouter LS = new SecStatusRouter(SECSTATUS.LS);
	public static final SecStatusRouter NS = new SecStatusRouter(SECSTATUS.NS);
	public static final SecStatusRouter ALL = new SecStatusRouter(SECSTATUS.HS, SECSTATUS.LS, SECSTATUS.NS);

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
	 * explore the systems to get their distance and parent until a given system
	 * is reached. This is used to find a route.
	 *
	 * @param idFrom
	 *          starting system
	 * @param idTo
	 *          stop system. When reached, no more system is searched for.
	 * @return
	 */
	protected Map<Integer, DisTree> explore(int idFrom, int idTo) {
		// need to add from and to otherwise we may not be able to go in dest
		// system.
		EnumSet<SECSTATUS> allowed = EnumSet.noneOf(SECSTATUS.class);
		allowed.add(SolarSystem.getSystem(idFrom).secStatus());
		allowed.add(SolarSystem.getSystem(idTo).secStatus());
		if (allowedStatus != null && allowedStatus.length != 0) {
			Stream.of(allowedStatus).forEach(allowed::add);
		}

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
					if (!ret.containsKey(adj.id) && allowed.contains(adj.secStatus())) {
						ret.put(adj.id, new DisTree(sid, dist));
						next.add(adj.id);
					}
					if (adj.id == idTo) {
						return ret;
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
