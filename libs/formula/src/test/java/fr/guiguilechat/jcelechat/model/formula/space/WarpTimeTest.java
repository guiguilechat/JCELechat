package fr.guiguilechat.jcelechat.model.formula.space;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WarpTimeTest {

	/**
	 * simple warp speed with 4 AU/s warp speed, 400 m/s subwarp speed, over 4
	 * AU<br />
	 * <ul>
	 * <li>acceleration time = ln(AU_in_m)/warpseed = 25.73/4 = 6.43s</li>
	 * <li>cruise time = none</li>
	 * <li>deceleration time = ln(AU_in_m*warpseed/s)/j =  ln(149597870691*4/100)/1.33 = 16.93</li>
	 * <li>taol = ceil(6.43+16.96) = ceil(23.35)=24</li>	 *
	 * </ul>
	 */
	@Test
	public void testMinimumLongWarp() {
		Assert.assertEquals(
				WarpTime.of(4, 4, 400),
				24);

	}

	/**
	 * test based on https://wiki.eveuniversity.org/Warp_time_calculation#Output
	 * 20 AU warp, 6 AU/s 200m/s : 19s
	 */
	@Test
	public void test20AUWarp() {
		Assert.assertEquals(
				WarpTime.of(20, 6, 200),
				19);

	}

	/**
	 * test based on https://wiki.eveuniversity.org/Warp_time_calculation#Output
	 * 1 AU warp, 3 AU/s 200m/s : 29s
	 */
	@Test
	public void testShortWarp() {
		Assert.assertEquals(
				WarpTime.of(1, 3, 200),
				29);
	}

}
