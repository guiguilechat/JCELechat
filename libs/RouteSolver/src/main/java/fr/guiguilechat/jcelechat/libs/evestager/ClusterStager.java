package fr.guiguilechat.jcelechat.libs.evestager;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionStager;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

/**
 * two-phases stager that does not find an optimal. First step is to cluster the
 * graph in the number of clusters, second step is to select for each cluster
 * the best node
 *
 *
 */
public class ClusterStager implements IRegionStager {

	private static class Cluster {
		List<Integer> nodes = new ArrayList<>();
	}

	@Override
	public List<SolarSystem> around(SysIndex idx, int[][] jumps, int clusters, boolean useSquareDistance) {
		// TODO Auto-generated method stub
		return null;
	}

}
