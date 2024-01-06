package fr.guiguilechat.jcelechat.model.sde.locations.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

/**
 * route between solar systems, when esi rout is down<br />
 * <p>
 * Solar systems are reindexed to only keep KS ; routes are cached.
 * </p>
 */
public class CachedKSRouter implements IRouter {

	public static final CachedKSRouter INSTANCE = new CachedKSRouter();

	private SysIndex systemIndex = null;

	/**
	 * if systems i and j are reachable through a stargate, then
	 * {@link #indexedNeighbours}[i] contains j and reciprocally.
	 */
	private int[][] indexedNeighbours;

	/** indexFrom -> indexTo -> ids of systems */
	private int[][][] knownroutes;

	/**
	 * make the index of KS.
	 */
	private synchronized SysIndex getIndex() {
		if (systemIndex == null) {
			List<SolarSystem> KSSystems = SolarSystem.load().values().stream().filter(syst -> syst.isKS)
					.collect(Collectors.toList());
			systemIndex = new SysIndex(KSSystems);
			indexedNeighbours = new int[systemIndex.size()][];
			for (SolarSystem ksSystem : KSSystems) {
				SolarSystem syst = ksSystem;
				indexedNeighbours[systemIndex.index(syst)] = syst.adjacentSystems.stream()
						.mapToInt(name -> systemIndex.index(SolarSystem.load().get(name))).toArray();
			}
			knownroutes = new int[systemIndex.size()][systemIndex.size()][];
		}
		return systemIndex;
	}

	protected int[] makeRoute(int indexFrom, int indexTo) {
		if (indexFrom == indexTo) {
			return new int[indexFrom];
		}
		int[] previous = new int[systemIndex.size()];
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
		SysIndex index = getIndex();
		Integer idxFrom = index.index(SolarSystem.getSystem(idFrom));
		Integer idxTo = index.index(SolarSystem.getSystem(idTo));
		if (idxFrom == null || idxTo == null) {
			// systems are not in knownspace
			return new int[0];
		}
		synchronized (knownroutes) {
			int[] ret = knownroutes[idxFrom][idxTo];
			if (ret == null) {
				int[] retIdx = makeRoute(idxFrom, idxTo);
				ret = IntStream.of(retIdx).map(idx -> index.system(idx).id).toArray();
				knownroutes[idxFrom][idxTo] = ret;
			}
			return ret;
		}
	}

}
