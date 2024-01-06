package fr.guiguilechat.jcelechat.model.sde.industry.activities;

import fr.guiguilechat.jcelechat.model.sde.industry.Activity;

public class MEResearch {

	public static double installationCost(double bpEIV, int startME, int endME, double costIndexMult,
			double locationMECostMult,
			double taxMult, boolean alphaclone) {
		double levelMult = (endME == 0 ? 0 : Math.pow(2.378696113, endME - 1))
				- (startME == 0 ? 0 : Math.pow(2.378696113, startME - 1));
		return Activity.installationCost(bpEIV * levelMult, 1, costIndexMult, locationMECostMult, taxMult, alphaclone,
				2.0 / 100);
	}
}
