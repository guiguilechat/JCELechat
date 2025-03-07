package fr.guiguilechat.jcelechat.model.formula.industry;

import java.util.Map;

public class Copying {

	/**
	 * get the multiplication to copying time based on the skills
	 *
	 * @param skills
	 * @return
	 */
	public static double timeMult(Map<Integer, Integer> skills) {
		double ret = 1.0;
		// science skill reduces by 5% per level
		ret *= 1.0 - 0.05 * skills.getOrDefault(3402, 0);
		// advanced industry reduces by 3% per level
		ret *= 1.0 - 0.03 * skills.getOrDefault(3388, 0);
		return ret;
	}

	public static final double EIV_MULT = Research.EIV_MULT;

	public static double installationCost(double bpEIV, int nbRuns, int runsPerCopy, double costIndexMult,
			double locationManufCostMult,
			double taxMult, boolean alphaclone) {
		return Activity.installationCost(bpEIV, nbRuns * runsPerCopy, costIndexMult, locationManufCostMult, taxMult,
				alphaclone,
		    EIV_MULT);
	}
}
