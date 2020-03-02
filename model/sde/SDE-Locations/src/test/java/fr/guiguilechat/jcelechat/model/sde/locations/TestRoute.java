package fr.guiguilechat.jcelechat.model.sde.locations;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRoute {

	@Test
	public void testJitaPerimeter() {
		int JITAID = 30000142;
		int PERIMETERID = 30000144;
		int[] route = Route.INSTANCE.getRoute(JITAID, PERIMETERID);
		Assert.assertEquals(route[0], JITAID);
		Assert.assertEquals(route[1], PERIMETERID);
	}

	@Test
	public void testJitaKisogo() {
		int JITAID = 30000142;
		int PERIMETERID = 30000144;
		int URLENID = 30000139;
		int KISOGOID = 30000141;
		int[] route = Route.INSTANCE.getRoute(JITAID, KISOGOID);
		Assert.assertEquals(route[0], JITAID);
		Assert.assertEquals(route[1], PERIMETERID);
		Assert.assertEquals(route[2], URLENID);
		Assert.assertEquals(route[3], KISOGOID);
	}

	@Test
	public void testUkkalenOtomainen() {
		int fromID = 30000177;
		int toID = 30000172;
		Assert.assertEquals(Route.INSTANCE.getRoute(fromID, toID).length, 8);
		Assert.assertEquals(Route.INSTANCE.getRoute(toID, fromID).length, 8);
	}

}
