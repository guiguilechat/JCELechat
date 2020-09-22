package fr.guiguilechat.jcelechat.libs.regioncycler;

import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.lelouet.tools.solver.fondhamilton.GreedyDeadEndFH;

public class GreedyDERegionCycler extends GreedyDeadEndFH implements IRegionCycler {

	public static final GreedyDERegionCycler INSTANCE = new GreedyDERegionCycler();

}