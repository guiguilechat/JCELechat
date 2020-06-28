package fr.guiguilechat.jcelechat.model.sde.industry;

import java.util.Map;
import java.util.function.Function;

public class Invention {

	/**
	 * get the multiplication to invention time based on the skils
	 *
	 * @param skills
	 * @return
	 */
	public static double timeMult(Map<Integer, Integer> skills) {
		double ret = 1.0;
		// advanced industry reduces by 3% per level
		ret *= 1.0 - 0.03 * skills.getOrDefault(3388, 0);
		return ret;
	}

	public static double baseCost(Blueprint bpi, Function<Integer, Double> getAdjusted) {
		return 0.02 * bpi.makeEIV(getAdjusted);
	}

}
