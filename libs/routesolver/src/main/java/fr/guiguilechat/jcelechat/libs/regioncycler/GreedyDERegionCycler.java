package fr.guiguilechat.jcelechat.libs.regioncycler;

import fr.lelouet.tools.solver.fondhamilton.GreedyDeadEndFH;

public class GreedyDERegionCycler extends GreedyDeadEndFH implements IRegionCycler {

	public static final GreedyDERegionCycler INSTANCE = new GreedyDERegionCycler();

}