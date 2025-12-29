package fr.guiguilechat.jcelechat.libs.exports.industry.activities;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.formula.industry.Manufacture;

public class ManufactureTest {

	@Test
	public void testSimpleCost() {
		Assert.assertEquals(Manufacture.installationCost(1000000, 1, 4.0 / 100, 1.0, 0.25 / 100, false), 82500);
	}



}
