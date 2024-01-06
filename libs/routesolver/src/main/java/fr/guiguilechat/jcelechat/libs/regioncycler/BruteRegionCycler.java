package fr.guiguilechat.jcelechat.libs.regioncycler;

import fr.lelouet.tools.solver.fondhamilton.BruteFH;

/**
 * enumerates all the possible permutation of the index
 *
 * If you have N system ith a source, then first item is source, you need to
 * iterate over the N-1 other. so that makes (N-1)! possible enumerations.
 *
 */
public class BruteRegionCycler extends BruteFH implements IRegionCycler {

	public static final BruteRegionCycler INSTANCE = new BruteRegionCycler();

}
