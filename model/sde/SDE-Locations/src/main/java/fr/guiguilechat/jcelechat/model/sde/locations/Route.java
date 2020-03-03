package fr.guiguilechat.jcelechat.model.sde.locations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * route between solar systems<br />
 * <p>
 * Solar systems are reindexed to only keep KS
 * </p>
 */
public class Route {

	public static final Route INSTANCE = new Route();

	private int[] IDX2ID;

	private HashMap<Integer, Integer> ID2IDX;

	private int[][] indexedNeighbours;

	/** indexFrom -> indexTo -> ids of systems */
	private int[][][] knownroutes ;

	private synchronized void reIndex() {
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
			// System.err.println(
			// "after pass, next ids are "
			// + futurePass.stream().map(i ->
			// SolarSystem.loadById().get(IDX2ID[i])).collect(Collectors.toList()));
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
		reIndex();
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

}
