package fr.guiguilechat.jcelechat.libs.refinesolver;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.MockPricing;

public class TestRefine1 {

	@Test
	public void testSimpleOneTrit() {
		MockPricing mp = new MockPricing();
		// only one trit is sold at 10 isks
		mp.orders(34).withSell(1, 10);
		var params = new Params()
				// want one tritanium
				.withQuantity(34, 1)
				//
				.withMarket(mp);
		Result result = new RefineSolver().solve(params);
		Assert.assertEquals(result.commands.size(), 1);
		Assert.assertEquals(result.cost, 10.0);
		Assert.assertEquals(result.commands.get(0).toEveBuy(), "1 Tritanium\n");
	}

	@Test
	public void testSimpleOneVeldspar() {
		MockPricing mp = new MockPricing();
		// 1000 veldspar are sold at 10 isks
		mp.orders(1230).withSell(1, 10);
		var params = new Params()
				// want one tritanium
				.withQuantity(34, 1)
				//
				.withMarket(mp)
				//
				// .withDebug(true)
				//
				.withRefineRate(0.5)
				//
				;
		Result result = new RefineSolver().solve(params);
		Assert.assertEquals(result.commands.size(), 1);
		Assert.assertEquals(result.cost, 10.0);
		Assert.assertEquals(result.commands.get(0).toEveBuy(), "1 Veldspar\n");
	}

}
