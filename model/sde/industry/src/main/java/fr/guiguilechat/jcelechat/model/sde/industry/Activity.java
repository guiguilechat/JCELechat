package fr.guiguilechat.jcelechat.model.sde.industry;

public class Activity {

	public static final double SCC_SURCHARGE_PC = 4.0;
	public static final double ALPHA_SURCHARGE_PC=0.25;

/**
 *
 * @param bpEIV EIV of the item. That is, the adjusted price of the mats required to build a 0/0 run.
 * @param nbRuns number of runs we start.
 * @param costIndexMult system cost index, base 1
 * @param locationManufCostMult multiplier of cost on the location, base 1
 * @param taxMult tax applied from the location, base 1
 * @param alphaclone true if the player is alpha
 * @param activityMult multiplier of EIV based on activity type.
 * @return
 */
	public static double installationCost(double bpEIV, int nbRuns, double costIndexMult, double locationManufCostMult,
			double taxMult, boolean alphaclone, double activityMult) {
		double raw= Math.ceil( nbRuns * bpEIV * activityMult );
		double indexBase=Math.ceil( raw*costIndexMult * locationManufCostMult);
		double taxBase=Math.ceil( raw*taxMult);
		double surchargeBase=Math.ceil(  raw*SCC_SURCHARGE_PC / 100);
		double alphaBase =Math.ceil(  raw * (alphaclone ? ALPHA_SURCHARGE_PC / 100 : 0));
//		System.err.println("raw="+raw+ "index="+indexBase+ "tax="+taxBase+" surcharge="+surchargeBase+" alpha="+alphaBase);
		return indexBase+taxBase+surchargeBase+alphaBase;
	}

}
