package fr.guiguilechat.jcelechat.model.sde.industry;

public enum StructureIndustryRig {

	NONE(0, 0, 0), T1(2, 20, 10), T2(2.4, 24, 12);

	private final double mepct;
	private final double tepct;
	private final double costpct;

	private StructureIndustryRig(double mepct, double tepct, double costpct) {
		this.mepct = mepct;
		this.tepct = tepct;
		this.costpct = costpct;
	}

	public static double rigMult(double truesec) {
		return truesec > 0.45 ? 1 : truesec <= 0 ? 2.1 : 1.9;
	}

	/**
	 * get the time requirement multiplier for a system
	 *
	 * @param truesec
	 *          the truesec of the system to operate the rig in
	 * @return the multiplier to reduce the time of manufacturing/research
	 */
	public double timeMult(double truesec) {
		return (100 - rigMult(truesec) * tepct) / 100;
	}


	/**
	 * get the material requirement multiplier for a system
	 *
	 * @param truesec
	 *          the truesec of the system to operate the rig in
	 * @return the multiplier to reduce the number of items when this number is >1
	 */
	public double matMult(double truesec) {
		return (100 - rigMult(truesec) * mepct) / 100;
	}

	public double costMult(double truesec) {
		return (100 - rigMult(truesec) * costpct) / 100;
	}

}
