package fr.guiguilechat.jcelechat.model.sde.industry.activities;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.sde.industry.Activity;

/**
 * manufacturing activity tools
 */
public class Manufacture {

	/**
	 * get the multiplication to manuf time based on the skills
	 *
	 * @param skills
	 * @return
	 */
	public static double timeMult(Map<Integer, Integer> skills) {
		double ret = 1.0;
		// industry skill reduces by 5% per level
		ret *= 1.0 - 0.04 * skills.getOrDefault(3380, 0);
		// advanced industry reduces by 3% per level
		ret *= 1.0 - 0.03 * skills.getOrDefault(3388, 0);
		return ret;
	}

	/**
	 * @return item quantity required to launch nbRuns runs.
	 */
	public static long requirement(long baseQtty, int nbRuns, int bpME, double locMult) {
		return baseQtty == 1 ? nbRuns : (long) Math.ceil(0.01 * (long) Math.floor(baseQtty * nbRuns * locMult * (100 - bpME)));
	}

	public static double installationCost(double bpEIV, int nbRuns, double costIndexMult, double locationManufCostMult,
			double taxMult, boolean alphaclone) {
		return Activity.installationCost(bpEIV, nbRuns, costIndexMult, locationManufCostMult, taxMult, alphaclone, 1.0);
	}

}
