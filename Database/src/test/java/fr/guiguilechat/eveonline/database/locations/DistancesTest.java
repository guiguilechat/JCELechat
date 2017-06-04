package fr.guiguilechat.eveonline.database.locations;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class DistancesTest {

	@Test
	public void testDistanceJump() {
		Distances d = new Distances(new YamlDatabase());
		Assert.assertEquals(d.distJumps("Jita", "Mahtista"), 4);
		Assert.assertEquals(d.distJumps("Ihilakken", "Ruomo"), 3);
		Assert.assertEquals(d.distJumps("Jita", "TheForge"), 0);
	}

	@Test
	public void testDistanceConstels() {
		Distances d = new Distances(new YamlDatabase());
		Assert.assertEquals(d.distConstels("Jita", "TheForge"), 0);
		Assert.assertEquals(d.distConstels("Jita", "Osmon"), 2);
	}

}
