package fr.guiguilechat.eveonline.sde.items;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.items.MetaInf;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Frigate;

public class TestLoad {

	@Test
	public void testLoadAtron() {
		Frigate atron = Frigate.load().get("Atron");
		Assert.assertEquals(atron.CpuOutput, 147);
		Assert.assertEquals(atron.HiSlots, 4);
		Assert.assertEquals(atron.Agility, 2.799999952316284);
	}

	@Test
	public void testLoadMetainf() {
		MetaInf mi = MetaInf.load();
		Assert.assertEquals(mi.name2group.get("Atron"), "ship/Frigate");
	}

}
