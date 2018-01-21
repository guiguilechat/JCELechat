package fr.guiguilechat.eveonline.sde.items;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.items.types.ship.Frigate;

public class TestLoad {

	@Test
	public void testLoadAtron() {
		Assert.assertEquals(Frigate
				.load()
				.get("Atron")
				.CpuOutput
				, 147.0);
	}

}
