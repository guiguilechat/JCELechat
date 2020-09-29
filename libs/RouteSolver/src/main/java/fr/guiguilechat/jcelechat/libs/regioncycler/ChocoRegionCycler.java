package fr.guiguilechat.jcelechat.libs.regioncycler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.lelouet.tools.solver.fondhamilton.ChocoFH;

public class ChocoRegionCycler extends ChocoFH implements IRegionCycler {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ChocoRegionCycler.class);

	public static final ChocoRegionCycler INSTANCE = new ChocoRegionCycler();

}
