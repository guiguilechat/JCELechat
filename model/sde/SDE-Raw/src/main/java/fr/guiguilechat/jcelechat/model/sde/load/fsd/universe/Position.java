package fr.guiguilechat.jcelechat.model.sde.load.fsd.universe;

public record Position(double x, double y, double z) {

	public static double AU_IN_M = 1.496e11;

	public Position(double[] xyz) {
		this(xyz[0], xyz[1], xyz[2]);
	}

	public double distanceFromOrigin() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double distanceFromOriginInAU() {
		return distanceFromOrigin() / AU_IN_M;
	}

}
