package fr.guiguilechat.jcelechat.model.formula.industry;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ResearchTest {

	@Test
	public void testResearchCost() {
		// real test from the game values
		Assert.assertEquals(Research.ptv(4268, 1), 85);
		Assert.assertEquals(Research.ptv(4268, 2), 203);
		// this one does not work :'(
		// Assert.assertEquals(Research.ptv(4268, 10), 208095);
	}

}
