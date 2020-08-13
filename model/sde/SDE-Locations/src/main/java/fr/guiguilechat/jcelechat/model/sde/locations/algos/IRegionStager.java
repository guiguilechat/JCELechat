package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Collection;
import java.util.List;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public interface IRegionStager {

	/**
	 * index the systems around the source. Default is expand using
	 * {@link ReachableRegionHs.#around(SolarSystem)}
	 *
	 * @param source
	 *          the system we start the expansion around
	 * @return
	 */
	public default SysIndex index(SolarSystem source) {
		return new SysIndex(expand(source));
	}

	public default Collection<SolarSystem> expand(SolarSystem source) {
		return ReachableRegionHs.around(source);
	}

	/**
	 * evaluate the distances between the systems indexed.
	 *
	 * @param idx
	 * @return a new matrix of the distances between each system.
	 */
	public default int[][] jumps(SysIndex idx) {
		int[][] jumps = new int[idx.size()][idx.size()];
		for (int i = 0; i < idx.size(); i++) {
			SolarSystem from = idx.system(i);
			jumps[i][i] = 0;
			for (int j = i + 1; j < idx.size(); j++) {
				SolarSystem to = idx.system(j);
				jumps[i][j] = jumps[j][i] = fr.guiguilechat.jcelechat.model.sde.locations.route.SecStatusRouter.HS
						.getRoute(from.id, to.id).length;
			}
		}
		return jumps;
	}

	/**
	 * split the set of systems, around a source, accessible with jumps in the
	 * same region and in HS, in a given number of clusters around system.
	 *
	 * @param source
	 *          system to start the exploration of the region, in HS, around.
	 * @param clusters
	 *          number of central systems.
	 * @return a list of the systems that make the sum of square distances of each
	 *         concerned system in the region the lowest possible.
	 */
	public default List<SolarSystem> around(SolarSystem source, int clusters, boolean useSquareDistance) {
		SysIndex idx = index(source);
		int[][] jumps = jumps(idx);
		return around(idx, jumps, clusters, useSquareDistance);
	}

	public List<SolarSystem> around(SysIndex idx, int[][] jumps, int clusters, boolean useSquareDistance);

}
