package fr.guiguilechat.jcelechat.libs.spring.anon.lp.eval;

import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OpportunisticCost {

	/**
	 * the type being evaluated
	 */
	private final int typeId;

	private final LPEvalParams params;

	/**
	 * lowest BO to place for this type regarding the opportunity cost of the
	 * corporations that provide it
	 */
	private final double lowestBO;

	/** evaluated isk/lp of each corp */
	private final Map<NpcCorporation, Double> corpsIskPLP;

}
