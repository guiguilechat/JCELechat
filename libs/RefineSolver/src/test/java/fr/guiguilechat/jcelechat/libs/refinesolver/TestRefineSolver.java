package fr.guiguilechat.jcelechat.libs.refinesolver;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.MockPricing;

public class TestRefineSolver {

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
		Commands result = new RefineSolver().solve(params);
		Assert.assertEquals(result.commands.size(), 1);
		Assert.assertEquals(result.cost, 10.0);
		Assert.assertEquals(result.commands.get(0).toEveBuy(), "1 Tritanium\n");
	}

	@Test
	public void testSimpleOneVeldspar() {
		MockPricing mp = new MockPricing();
		// 1 veldspar is sold at 10 isks
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
		Commands result = new RefineSolver().solve(params);
		Assert.assertEquals(result.commands.size(), 1);
		Assert.assertEquals(result.cost, 10.0);
		Assert.assertEquals(result.commands.get(0).toEveBuy(), "1 Veldspar\n");
	}

	@Test
	public void testSeveralOrders() {
		MockPricing mp = new MockPricing();
		// 1000 tritanium are sold at 10 isks
		mp.orders(34).withSell(40, 10).withSell(40, 15).withSell(100, 20);
		var params = new Params()
				// want 100 tritanium
				.withQuantity(34, 100)
				//
				.withMarket(mp)
				//
				// .withDebug(true)
				//
				.withRefineRate(0.5)
				//
				;
		Commands result = new RefineSolver().solve(params);
		Assert.assertEquals(result.commands.size(), 1);
		Assert.assertEquals(result.cost, 100 * 20.0);
		Assert.assertEquals(result.commands.get(0).toEveBuy(), "100 Tritanium\n");
	}

	@Test
	public void testSeveralOrdersSeveralCommands() {
		MockPricing mp = new MockPricing();
		// 1000 tritanium are sold at 10 isks
		mp.orders(34).withSell(40, 10).withSell(40, 15).withSell(100, 20);
		var params = new Params()
				// want 100 tritanium
				.withQuantity(34, 100)
				//
				.withMarket(mp)
				//
				.withMaxCommands(3)
				//
				// .withDebug(true)
				//
				.withRefineRate(0.5)
				//
				;
		Commands result = new RefineSolver().solve(params);
		Assert.assertEquals(result.commands.size(), 3);
		Assert.assertEquals(result.cost, 40 * 10.0 + 40 * 15.0 + 20 * 20.0);
		Assert.assertEquals(result.commands.get(0).toEveBuy(), "40 Tritanium\n");
	}

}
