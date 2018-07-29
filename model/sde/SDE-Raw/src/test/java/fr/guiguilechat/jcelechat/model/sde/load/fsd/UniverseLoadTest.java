package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Universe;

public class UniverseLoadTest {

	@Test
	public void loadUniverse() {
		Assert.assertEquals(Universe.load().eve.get("TheForge").constellations.get("Kimotoro").systems.get("Jita").hub,
				true);
	}

}
