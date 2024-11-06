package fr.guiguilechat.jcelechat.model.formula.industry;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

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

	/**
	 * using the best reduction in mat cost : 1% from structure and 2.4% times 2.1
	 * in NS rig
	 */
	public static final double LOWER_MATCOST_MULT = (100.0 - 1) / 100 * (100.0 - 2.4 * 2.1) / 100;
	public static final double LOWER_TAX_MULT = 0.25;
	public static final double LOWER_COST_INDEX = 2.0;

	/**
	 * compute the minimum construction cost for 1 run
	 *
	 * @param eiv
	 * @param ME
	 *                        the Me of the BP. Should be 10 for BPO and 5 for
	 *                        invented BP (with
	 *                        process)
	 * @param runs
	 *                        batch run, helps reduce the requirement for rounding.
	 *                        5 should be
	 *                        enough
	 * @param materials
	 *                        the manufacturing materials id to quantity
	 * @param typeQttyPrice
	 *                        (type id, qtty)-&gt; price : function to get the price
	 *                        of
	 *                        acquiring a type with a quantity.
	 * @return the sum of installation cost and material costs
	 */
	public static double minConstructionCost(double eiv, int ME, int runs, Map<Integer, Long> materials,
			BiFunction<Integer, Integer, Double> typeQttyPrice) {
		double installationCost = installationCost(eiv, 1, LOWER_COST_INDEX / 100, 1.0, LOWER_TAX_MULT / 100, false);
		double matMult = (100.0 - ME) / 100 * LOWER_MATCOST_MULT;
		double matCost = 0.0;
		for (Entry<Integer, Long> mr : materials.entrySet()) {
			int qtty = mr.getValue() == 1 ? runs : (int) Math.ceil(matMult * mr.getValue() * runs);
			double cost = typeQttyPrice.apply(mr.getKey(), qtty) / runs;
			matCost += cost;
		}
		return matCost + installationCost;
	}

}
