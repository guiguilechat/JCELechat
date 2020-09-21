package fr.guiguilechat.jcelechat.libs.regioncycler;

import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.lelouet.tools.solver.fondhamilton.GreedyFondHamilton;

public class GreedyRegionCycler extends GreedyFondHamilton implements IRegionCycler {

	public static final GreedyRegionCycler INSTANCE = new GreedyRegionCycler();

}
