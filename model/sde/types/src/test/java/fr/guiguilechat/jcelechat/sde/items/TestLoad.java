package fr.guiguilechat.jcelechat.sde.items;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Frigate;

public class TestLoad {

	@Test
	public void testLoadAtron() {
		Frigate atron = Frigate.METAGROUP.load().get(608);
		Assert.assertEquals(atron.cpuoutput, 147);
		Assert.assertEquals(atron.hislots, 4);
		Assert.assertEquals(atron.agility, 2.8);
	}

	@Test
	public void testLoadAtronFromMetainf() {
		TypeIndex mi = TypeIndex.load();
		Assert.assertEquals(mi.id2group.get(608), "ship/Frigate");
		Assert.assertEquals(TypeIndex.getType(608).name, "Atron");
	}

	@Test
	public void testLoadAtronFromFrigates() {
		Ship atron = Frigate.METAGROUP.load().get(608);
		Assert.assertEquals(atron.warpspeedmultiplier, 5.0);
	}

	@Test
	public void testShipsNoNull() {
		Ship.METACAT.groups().forEach(img -> {
			Assert.assertNotNull(img);
			Assert.assertFalse(img.load().containsKey(null), img.getName() + " has null key");
			Assert.assertFalse(img.load().containsValue(null), img.getName() + " has null value");
		});
	}

	@Test(dependsOnMethods = "testLoadAtronFromFrigates")
	public void testLoadAtronFromShips() {
		Ship atron = Ship.METACAT.load().get(608);
		Assert.assertEquals(atron.warpspeedmultiplier, 5.0);
	}

}
