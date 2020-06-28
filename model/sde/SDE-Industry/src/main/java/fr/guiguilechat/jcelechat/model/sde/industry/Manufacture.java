package fr.guiguilechat.jcelechat.model.sde.industry;

import java.util.Map;
import java.util.function.Function;

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

	public static double baseCost(Blueprint bp, Function<Integer, Double> getAdjusted) {
		return bp.makeEIV(getAdjusted);
	}

	public static long requirement(long baseQtty, int runs, int bpME, double locMult) {
		return baseQtty == 1 ? runs : (long) Math.ceil(baseQtty * runs * locMult * (100 - bpME) / 100);
	}

}
