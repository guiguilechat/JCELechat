package fr.guiguilechat.jcelechat.model.sde.locations.route;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.locations.route.SecStatusRouter.DisTree;

public class SecStatusRouterTest {

	@Test
	public void testExploreJitaHekHS() {
		int JITAID = 30000142;
		int HEKID = 30002053;
		int UTTINDARID = 30002049;
		Map<Integer, DisTree> explored = SecStatusRouter.HS.explore(JITAID, HEKID);
		DisTree hekRes = explored.get(HEKID);
		Assert.assertEquals(hekRes.dist, 19);
		Assert.assertEquals(hekRes.parent, UTTINDARID);
		int[] route = SecStatusRouter.route(JITAID, HEKID, explored);
		Assert.assertEquals(route[18], HEKID);
		Assert.assertEquals(route[17], UTTINDARID);
	}

	@Test
	public void testExploreJitaHekLS() {
		int JITAID = 30000142;
		int HEKID = 30002053;
		int OTOUID = 30002723;
		Map<Integer, DisTree> explored = SecStatusRouter.LS.explore(JITAID, HEKID);
		DisTree hekRes = explored.get(HEKID);
		Assert.assertEquals(hekRes.dist, 9);
		Assert.assertEquals(hekRes.parent, OTOUID);
		int[] route = SecStatusRouter.route(JITAID, HEKID, explored);
		Assert.assertEquals(route[8], HEKID);
		Assert.assertEquals(route[7], OTOUID);
	}

}
