package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;

/**
 * route between solar systems, when esi rout is down<br />
 * <p>
 * Solar systems are reindexed to only keep KS
 * </p>
 */
public class Route {

	public static final Route INSTANCE = new Route();

	private int[] IDX2ID;

	private HashMap<Integer, Integer> ID2IDX;

	/**
	 * if systems i and j are reachable through a stargate, then
	 * {@link #indexedNeighbours}[i] contains j and reciprocally.
	 */
	private int[][] indexedNeighbours;

	/** indexFrom -> indexTo -> ids of systems */
	private int[][][] knownroutes ;

	/**
	 * make the index of KS.
	 */
	private synchronized void ensureIndexedKS() {
		if (IDX2ID != null) {
			return;
		}
		List<SolarSystem> KSSystems = SolarSystem.load().values().stream().filter(syst -> syst.isKS)
				.collect(Collectors.toList());
		IDX2ID = KSSystems.stream().mapToInt(s -> s.id).toArray();
		ID2IDX = new HashMap<>();
		for (int i = 0; i < KSSystems.size(); i++) {
			ID2IDX.put(KSSystems.get(i).id, i);
		}
		indexedNeighbours = new int[IDX2ID.length][];
		for (SolarSystem ksSystem : KSSystems) {
			SolarSystem syst = ksSystem;
			indexedNeighbours[ID2IDX.get(syst.id)] = syst.adjacentSystems.stream()
					.mapToInt(name -> ID2IDX.get(SolarSystem.load().get(name).id)).toArray();
		}
		knownroutes = new int[IDX2ID.length][IDX2ID.length][];
	}

	protected int[] makeRoute(int indexFrom, int indexTo) {
		if (indexFrom == indexTo) {
			return new int[indexFrom];
		}
		int[] previous = new int[IDX2ID.length];
		Arrays.fill(previous, -1);
		previous[indexFrom] = indexFrom;
		Set<Integer> nextPass = new HashSet<>(Arrays.asList(indexFrom));
		findit: for (int dist = 1; dist < 100; dist++) {
			Set<Integer> futurePass = new HashSet<>();
			for (int passIdx : nextPass) {
				for (int nbg : indexedNeighbours[passIdx]) {
					if (previous[nbg] == -1) {
						previous[nbg] = passIdx;
						if (nbg == indexTo) {
							break findit;
						} else {
							futurePass.add(nbg);
						}
					}
				}
			}
			nextPass = futurePass;
		}
		if (previous[indexTo] == -1) {
			System.err.println("could not find path from idx " + indexFrom + " to " + indexTo);
			return null;
		} else {
			List<Integer> indexes = new ArrayList<>();
			int next = indexTo;
			while (next != indexFrom) {
				indexes.add(0, next);
				next = previous[next];
			}
			indexes.add(0, indexFrom);
			return indexes.stream().mapToInt(i -> i).toArray();
		}
	}

	public int[] getRoute(int idFrom, int idTo) {
		ensureIndexedKS();
		Integer idxFrom = ID2IDX.get(idFrom);
		Integer idxTo = ID2IDX.get(idTo);
		if (idxFrom == null || idxTo == null) {
			// systems are not in knownspace
			return new int[0];
		}
		synchronized (knownroutes) {
			int[] ret = knownroutes[idxFrom][idxTo];
			if (ret == null) {
				int[] retIdx = makeRoute(idxFrom, idxTo);
				ret = IntStream.of(retIdx).map(idx -> IDX2ID[idx]).toArray();
				knownroutes[idxFrom][idxTo] = ret;
			}
			return ret;
		}
	}

	public int getJump(int idFrom, int idTo) {
		var route = getRoute(idFrom, idTo);
		if (route == null || route.length == 0) {
			return Integer.MAX_VALUE;
		} else {
			return route.length - 1;
		}
	}

	public static class DisTree {
		int parent;
		int dist;

		public DisTree() {
		}

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
	 * @param allowedStatus
	 *          allowed sec status more than the sec status of from and to.
	 * @return
	 */
	public static Map<Integer, DisTree> explore(int idFrom, int idTo, SECSTATUS... allowedStatus) {
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

	public static int[] route(int idFrom, int idTo, SECSTATUS... allowedStatus) {
		return route(idFrom, idTo, explore(idFrom, idTo, allowedStatus));
	}

	public static int[] route(int idFrom, int idTo, Map<Integer, DisTree> explored) {
		List<Integer> ret = new LinkedList<>();
		for (int next = idTo; next != idFrom && explored.containsKey(next); next = explored.get(next).parent) {
			ret.add(0, next);
		}
		return ret.stream().mapToInt(i -> i).toArray();
	}

}
