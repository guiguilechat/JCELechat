package fr.guiguilechat.jcelechat.libs.denlicker;

import java.util.List;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IDenLicker;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

/**
 * enumerates all the possible permutation of the index
 *
 * If you have N system ith a source, then first item is source, you need to
 * iterate over the N-1 other. so that makes (N-1)! possible enumerations.
 *
 */
public class BruteDenLicker implements IDenLicker {

	@Override
	public List<SolarSystem> list(SysIndex idx, int[][] distances, int sourceIdx) {

		// TODO Auto-generated method stub
		return null;
	}

}
