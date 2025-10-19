package fr.guiguilechat.jcelechat.libs.spring.universe.stargate;

import org.testng.Assert;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.stargate.StargateService;
import fr.guiguilechat.jcelechat.model.formula.space.WarpTime;

public class StargateServiceTest {

//	@Test
	public void testWS() {
		Assert.assertEquals(StargateService.convertWarpTotime(WarpTime.AU_IN_M
				* 4, 0, 4), 0.5 + 4 * Math.log(WarpTime.AU_IN_M) / 4);
		Assert.assertEquals(StargateService.convertWarpTotime(WarpTime.AU_IN_M
				* 4, 0, 2), 0.5 + 4 * Math.log(WarpTime.AU_IN_M) / 2);
		Assert.assertEquals(StargateService.convertWarpTotime(WarpTime.AU_IN_M
				* 4, 0, 6), 0.5 + 4 * Math.log(WarpTime.AU_IN_M) / 6);
		Assert.assertEquals(StargateService.convertWarpTotime(WarpTime.AU_IN_M
				* (4 + 6 * 5), 0, 6), 0.5 + 4 * Math.log(WarpTime.AU_IN_M) / 6 + 5);

		// 6AU/s , 0s align :
	}

}
