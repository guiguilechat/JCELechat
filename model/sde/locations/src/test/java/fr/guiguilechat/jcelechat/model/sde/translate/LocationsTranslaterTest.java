package fr.guiguilechat.jcelechat.model.sde.translate;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LocationsTranslaterTest {

	@Test
	public void testUnfuck() {
		Assert.assertEquals(LocationsTranslater.unFuckLocationName("TheForge"), "The Forge");
		Assert.assertEquals(LocationsTranslater.unFuckLocationName("ValeoftheSilent"), "Vale of the Silent");
	}

}
