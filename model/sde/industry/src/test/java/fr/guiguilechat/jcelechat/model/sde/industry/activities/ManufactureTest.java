package fr.guiguilechat.jcelechat.model.sde.industry.activities;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ManufactureTest {

	@Test
	public void testSimpleCost() {
		Assert.assertEquals(Manufacture.installationCost(1000000, 1, 4/100, 1.0, 0.25/100, false), 17500);
	}
	
	
	
}
