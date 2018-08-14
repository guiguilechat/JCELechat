package fr.guiguilechat.jcelechat.sde.items;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.items.MetaInf;
import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.items.types.ship.Frigate;

public class TestLoad {

	@Test
	public void testLoadAtron() {
		Frigate atron = Frigate.METAGROUP.load().get("Atron");
		Assert.assertEquals(atron.CpuOutput, 147);
		Assert.assertEquals(atron.HiSlots, 4);
		Assert.assertEquals(atron.Agility, 2.799999952316284);
	}

	@Test
	public void testLoadAtronFromMetainf() {
		MetaInf mi = MetaInf.load();
		Assert.assertEquals(mi.name2group.get("Atron"), "ship/Frigate");
		Assert.assertEquals(MetaInf.getItem("Atron").name, "Atron");
	}

	@Test
	public void testLoadAtronFromFrigates() {
		Ship atron = Frigate.METAGROUP.load().get("Atron");
		Assert.assertEquals(atron.WarpSpeedMultiplier, 5.0);
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
		Ship atron = Ship.METACAT.load().get("Atron");
		Assert.assertEquals(atron.WarpSpeedMultiplier, 5.0);
	}

}
