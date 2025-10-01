package fr.guiguilechat.jcelechat.model.formula.industry;

public class TEResearch {

	public static final double EIV_MULT = Research.EIV_MULT;

	public static double installationCost(double bpEIV, int startTE, int endTE, double costIndexMult,
			double locationTECostMult,
			double taxMult, boolean alphaclone) {
		double levelMult = (endTE == 0 ? 0 : Math.pow(2.378696113, endTE / 2 - 1))
				- (startTE == 0 ? 0 : Math.pow(2.378696113, startTE / 2 - 1));
		return Activity.installationCost(bpEIV * levelMult, 1, costIndexMult, locationTECostMult, taxMult, alphaclone,
		    EIV_MULT);
	}

}
