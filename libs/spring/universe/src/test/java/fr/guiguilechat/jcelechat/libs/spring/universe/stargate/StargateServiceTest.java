package fr.guiguilechat.jcelechat.libs.spring.universe.stargate;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StargateServiceTest {

	@Test
	public void testWS() {
		Assert.assertEquals(StargateService.convertWarpTotime(StargateService.AU_IN_M
				* 4, 0, 4), 0.5 + 4 * Math.log(StargateService.AU_IN_M) / 4);
		Assert.assertEquals(StargateService.convertWarpTotime(StargateService.AU_IN_M
				* 4, 0, 2), 0.5 + 4 * Math.log(StargateService.AU_IN_M) / 2);
		Assert.assertEquals(StargateService.convertWarpTotime(StargateService.AU_IN_M
				* 4, 0, 6), 0.5 + 4 * Math.log(StargateService.AU_IN_M) / 6);
		Assert.assertEquals(StargateService.convertWarpTotime(StargateService.AU_IN_M
				* (4 + 6 * 5), 0, 6), 0.5 + 4 * Math.log(StargateService.AU_IN_M) / 6 + 5);

		// 6AU/s , 0s align :
	}

}
