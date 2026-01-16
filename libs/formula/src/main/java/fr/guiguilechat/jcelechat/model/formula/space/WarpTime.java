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
		double exit_speed_mps = Math.min(100, subwarpspeed_mps / 2);
		double daccel_au = 1.0f;
		double decel_speed_aups = Math.min(warpspeed_aups / 3, 2.0f);
		double ddecel_au = warpspeed_aups / decel_speed_aups;
		double dcruise_au = distance_au - daccel_au - ddecel_au;
		if (dcruise_au < 0) {
			// short jump
			double vmax_aups = distance_au * warpspeed_aups * decel_speed_aups / (warpspeed_aups + decel_speed_aups);
			double accel_time = Math.log(AU_IN_M * vmax_aups / warpspeed_aups) / warpspeed_aups;
			double decel_time = Math.log(AU_IN_M * vmax_aups / exit_speed_mps) / decel_speed_aups;
			return (int) Math.ceil(
					accel_time+decel_time);
		} else {
			// long jump
			double accel_time = Math.log(AU_IN_M) / warpspeed_aups;
			double cruise_time = dcruise_au / warpspeed_aups;
			double decel_time = Math.log(AU_IN_M * warpspeed_aups / exit_speed_mps) / decel_speed_aups;
			return (int) Math.ceil(
					accel_time
					+cruise_time
					+decel_time);
		}
	}

	public static int ofMeters(double distance_m, double warpspeed_aups, double subwarpspeed_mps) {
		if (distance_m < 100000) {
			throw new RuntimeException("distance "+distance_m+" is too low");
		}
		return of(distance_m / AU_IN_M, warpspeed_aups, subwarpspeed_mps);
	}

}
