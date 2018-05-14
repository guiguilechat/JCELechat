package fr.guiguilechat.eveonline.programs.guimutaplasmids;

public enum Mutaplasmid {

	DECAYED(0.9, 1.05, 0.95, 1.25, 0.97, 1.035, 0.85, 1.2),
	GRAVID(0.9, 1.05, 0.95, 1.25, 0.97, 1.035, 0.85,1.2),
	UNSTABLE(0.9, 1.05, 0.95, 1.25, 0.97, 1.035, 0.85, 1.2);

	public final double MWD_SIG_MIN;
	public final double MWD_SIG_MAX;
	public final double FIT_MIN;
	public final double FIT_MAX;
	public final double VELOCITY_MIN;
	public final double VELOCITY_MAX;
	public final double PROP_ACTIVATION_MIN;
	public final double PROP_ACTIVATION_MAX;

	private Mutaplasmid(double mwdsigmin, double mwdsigmax, double fitmin, double fitmax, double velocitymin,
			double velocitymax, double propactivationmin, double propactivationmax) {
		MWD_SIG_MIN = mwdsigmin;
		MWD_SIG_MAX = mwdsigmax;
		FIT_MIN = fitmin;
		FIT_MAX = fitmax;
		VELOCITY_MIN = velocitymin;
		VELOCITY_MAX = velocitymax;
		PROP_ACTIVATION_MIN = propactivationmin;
		PROP_ACTIVATION_MAX = propactivationmax;
	}


}
