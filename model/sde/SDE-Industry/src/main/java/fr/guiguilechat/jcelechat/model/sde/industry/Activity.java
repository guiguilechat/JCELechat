package fr.guiguilechat.jcelechat.model.sde.industry;

public class Activity {

	public static double installationCost(double bpEIV, int nbRuns, double costIndexMult, double locationManufCostMult,
			double taxMult, boolean alphaclone, double activityMult) {
		return Math.ceil(nbRuns * bpEIV * activityMult * (costIndexMult * locationManufCostMult
				+ taxMult
				+ 0.25 / 100
				+ (alphaclone ? 0.25 / 100 : 0)));
	}

}
