package fr.guiguilechat.jcelechat.model.formula.space;

/**
 * https://wiki.eveuniversity.org/Warp_time_calculation
 */
public class WarpTime {

	public static final double AU_IN_M = 149597870691L;

	/**
	 * @return time from entering warp to exiting warp
	 */
	public static int of(float distance_au, float subwarpspeed_mps, float warpspeed_aups) {
		// exit speed
		float s = Math.min(100, subwarpspeed_mps / 2);
		float daccel_au = 1.0f;
		float k = warpspeed_aups;
		float j = Math.min(warpspeed_aups / 3, 2.0f);
		float ddecel_au = warpspeed_aups / j;
		float dcruise_au = distance_au - daccel_au - ddecel_au;
		if (dcruise_au < 0) {
			// short jump
			float vmax = distance_au * k * j / (k + j);
			return (int) Math.ceil(
					// time accel
					Math.log(vmax / k) / k
							// time decel
							+ Math.log(vmax / s) / j);
		} else {
			// long jump
			return (int) Math.ceil(
					// accel
					Math.log(AU_IN_M) / k
							// cruise
							+ dcruise_au / k
							// decel
							+ Math.log(k / s) / j);
		}
	}

}
