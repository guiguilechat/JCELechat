package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MockPricingTest {

	@Test(timeOut = 500)
	public void testMock() {
		MockPricing mp = new MockPricing();
		mp.orders(34).withSell(10, 20.0);
		Assert.assertEquals((int) mp.getMarketOrders(34).listSellOrders().size().get(), 1);
	}

}
