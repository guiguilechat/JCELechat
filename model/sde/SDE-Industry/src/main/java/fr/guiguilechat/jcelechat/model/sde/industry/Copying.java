package fr.guiguilechat.jcelechat.model.sde.industry;

import java.util.Map;
import java.util.function.Function;

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

	public static double baseCost(Blueprint bpo, Function<Integer, Double> getAdjusted) {
		return 0.02 * bpo.makeEIV(getAdjusted);
	}
}
