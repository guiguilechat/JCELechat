package fr.guiguilechat.eveonline.model.database.locations;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.database.locations.Distances;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;

public class DistancesTest {
	Distances d = new Distances(new YamlDatabase());
	@Test
	public void testDistanceJump() {
		Assert.assertEquals(d.distJumps("Jita", "Mahtista"), 4);
		Assert.assertEquals(d.distJumps("Ihilakken", "Ruomo"), 3);
		Assert.assertEquals(d.distJumps("Jita", "TheForge"), 0);
	}

	@Test
	public void testDistanceConstels() {
		Assert.assertEquals(d.distConstels("Jita", "TheForge"), 0);
		Assert.assertEquals(d.distConstels("Jita", "Osmon"), 2);
	}

	@Test
	void testsystemDist() {
		Assert.assertEquals(d.systemsAtDistance("Irgrus", 0).size(), 1, "found " + d.systemsAtDistance("Irgrus", 0));
		Assert.assertEquals(d.systemsAtDistance("Irgrus", 1).size(), 2, "found " + d.systemsAtDistance("Irgrus", 1));
		Assert.assertEquals(d.systemsAtDistance("Irgrus", 2).size(), 1, "found " + d.systemsAtDistance("Irgrus", 2));
		Assert.assertEquals(d.systemsAtDistance("Irgrus", 3).size(), 1, "found " + d.systemsAtDistance("Irgrus", 3));
	}

}
