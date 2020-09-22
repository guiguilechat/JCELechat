package fr.guiguilechat.jcelechat.libs.regioncycler;

import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.lelouet.tools.solver.fondhamilton.GreedyFH;

public class GreedyRegionCycler extends GreedyFH implements IRegionCycler {

	public static final GreedyRegionCycler INSTANCE = new GreedyRegionCycler();

}
