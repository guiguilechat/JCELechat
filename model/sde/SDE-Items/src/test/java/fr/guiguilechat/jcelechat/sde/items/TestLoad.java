package fr.guiguilechat.jcelechat.sde.items;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.items.MetaInf;
import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.items.types.ship.Frigate;

public class TestLoad {

	@Test
	public void testLoadAtron() {
		Frigate atron = Frigate.load().get("Atron");
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
	public void testLoadAtronFromShips() {
		Ship atron = Ship.loadCategory().get("Atron");
		Assert.assertEquals(atron.WarpSpeedMultiplier, 5.0);
	}

}
