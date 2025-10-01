package fr.guiguilechat.jcelechat.model.formula.space;

/**
 */
public class WarpTime {

	public static final double AU_IN_M = 149597870691L;

	/**
	 * @see https://wiki.eveuniversity.org/Warp_time_calculation
	 * @return time from entering warp to exiting warp
	 */
	public static int of(double distance_au, double warpspeed_aups, double subwarpspeed_mps) {
		// exit speed
		double s = Math.min(100, subwarpspeed_mps / 2);
		double daccel_au = 1.0f;
		double k = warpspeed_aups;
		double j = Math.min(warpspeed_aups / 3, 2.0f);
		double ddecel_au = warpspeed_aups / j;
		double dcruise_au = distance_au - daccel_au - ddecel_au;
		System.err.println("distances_au : accel=" + daccel_au + " cruise=" + dcruise_au + " decel=" + ddecel_au);
		if (dcruise_au < 0) {
			System.err.println("short jump");
			// short jump
			double vmax_aups = distance_au * k * j / (k + j);
			double accel_time = Math.log(AU_IN_M * vmax_aups / k) / k;
			double decel_time = Math.log(AU_IN_M * vmax_aups / s) / j;
			System.err.println("maxwarpspeed=" + vmax_aups + " accel_time=" + accel_time + " decel_time=" + decel_time);
			return (int) Math.ceil(
					accel_time+decel_time);
		} else {
			System.err.println("long jump");
			// long jump
			double accel_time = Math.log(AU_IN_M) / k;
			double cruise_time = dcruise_au / k;
			double decel_time=  Math.log(AU_IN_M *k / s) / j;
			System.err.println("times_s : accel=" + accel_time + "cruise=" + cruise_time + " decel=" + decel_time);
			return (int) Math.ceil(
					accel_time
					+cruise_time
					+decel_time);
		}
	}

	public static int ofMeters(double distance_m, double warpspeed_aups, double subwarpspeed_mps) {
		return of(distance_m / AU_IN_M, warpspeed_aups, subwarpspeed_mps);
	}

}
