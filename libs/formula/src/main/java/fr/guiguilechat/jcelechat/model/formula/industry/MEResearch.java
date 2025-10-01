package fr.guiguilechat.jcelechat.model.formula.industry;

public class MEResearch {

	public static final double EIV_MULT = Research.EIV_MULT;

	public static double installationCost(double bpEIV, int startME, int endME, double costIndexMult,
			double locationMECostMult,
			double taxMult, boolean alphaclone) {
		double levelMult = (endME == 0 ? 0 : Math.pow(2.378696113, endME - 1))
				- (startME == 0 ? 0 : Math.pow(2.378696113, startME - 1));
		return Activity.installationCost(bpEIV * levelMult, 1, costIndexMult, locationMECostMult, taxMult, alphaclone,
		    EIV_MULT);
	}
}
