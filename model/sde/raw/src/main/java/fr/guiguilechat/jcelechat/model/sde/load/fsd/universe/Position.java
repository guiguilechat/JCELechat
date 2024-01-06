package fr.guiguilechat.jcelechat.model.sde.load.fsd.universe;

public record Position(double x, double y, double z) {

	public static double AU_IN_M = 1.496e11;

	public static final Position ORIGIN = new Position(0, 0, 0);

	public Position(double[] xyz) {
		this(xyz[0], xyz[1], xyz[2]);
	}

	//
	// distance 1 (abs)
	//

	public double distance1(Position other) {
		other = other == null ? ORIGIN : other;
		double smAbs = 0.0;
		smAbs += Math.abs(x() - other.x());
		smAbs += Math.abs(y() - other.y());
		smAbs += Math.abs(z() - other.z());
		return smAbs;
	}

	public double distance1InAU(Position other) {
		return distance1(other) / AU_IN_M;
	}

	//
	// distance inf (max)
	//

	public double distanceInf(Position other) {
		other = other == null ? ORIGIN : other;
		double max = 0.0;
		max = Math.max(Math.abs(x() - other.x()), max);
		max = Math.max(Math.abs(y() - other.y()), max);
		max = Math.max(Math.abs(z() - other.z()), max);
		return max;
	}

	public double distanceInfInAU(Position other) {
		return distanceInf(other) / AU_IN_M;
	}

	//
	// distance 2 (usual one)
	//

	public double distance2(Position other) {
		other = other == null ? ORIGIN : other;
		double smSq = 0.0;
		smSq += Math.pow(x() - other.x(), 2);
		smSq += Math.pow(y() - other.y(), 2);
		smSq += Math.pow(z() - other.z(), 2);
		return Math.sqrt(smSq);
	}

	public double distance2InAU(Position other) {
		return distance2(other) / AU_IN_M;
	}

	public double distanceFromOrigin() {
		return distance2(null);
	}

	public double distanceFromOriginInAU() {
		return distanceFromOrigin() / AU_IN_M;
	}

}
