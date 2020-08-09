package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.Router;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.Router.DisTree;

public class TestRoute {

	@Test
	public void testJitaPerimeter() {
		int JITAID = 30000142;
		int PERIMETERID = 30000144;
		int[] route = Router.INSTANCE.getRoute(JITAID, PERIMETERID);
		Assert.assertEquals(route[0], JITAID);
		Assert.assertEquals(route[1], PERIMETERID);
	}

	@Test
	public void testJitaKisogo() {
		int JITAID = 30000142;
		int PERIMETERID = 30000144;
		int URLENID = 30000139;
		int KISOGOID = 30000141;
		int[] route = Router.INSTANCE.getRoute(JITAID, KISOGOID);
		Assert.assertEquals(route[0], JITAID);
		Assert.assertEquals(route[1], PERIMETERID);
		Assert.assertEquals(route[2], URLENID);
		Assert.assertEquals(route[3], KISOGOID);
	}

	@Test
	public void testUkkalenOtomainen() {
		int fromID = 30000177;
		int toID = 30000172;
		Assert.assertEquals(Router.INSTANCE.getRoute(fromID, toID).length, 8);
		Assert.assertEquals(Router.INSTANCE.getRoute(toID, fromID).length, 8);
	}

	@Test
	public void testExploreJitaHekHS() {
		int JITAID = 30000142;
		int HEKID = 30002053;
		int UTTINDARID = 30002049;
		Map<Integer, DisTree> explored = Router.explore(JITAID, HEKID);
		DisTree hekRes = explored.get(HEKID);
		Assert.assertEquals(hekRes.dist, 19);
		Assert.assertEquals(hekRes.parent, UTTINDARID);
		int[] route = Router.route(JITAID, HEKID, explored);
		Assert.assertEquals(route[18], HEKID);
		Assert.assertEquals(route[17], UTTINDARID);
	}

	@Test
	public void testExploreJitaHekLS() {
		int JITAID = 30000142;
		int HEKID = 30002053;
		int OTOUID = 30002723;
		Map<Integer, DisTree> explored = Router.explore(JITAID, HEKID, SECSTATUS.LS);
		DisTree hekRes = explored.get(HEKID);
		Assert.assertEquals(hekRes.dist, 9);
		Assert.assertEquals(hekRes.parent, OTOUID);
		int[] route = Router.route(JITAID, HEKID, explored);
		Assert.assertEquals(route[8], HEKID);
		Assert.assertEquals(route[7], OTOUID);
	}

}
