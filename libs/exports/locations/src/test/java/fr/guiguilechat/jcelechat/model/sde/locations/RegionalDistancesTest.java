package fr.guiguilechat.jcelechat.model.sde.locations;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.libs.exports.locations.Region;
import fr.guiguilechat.jcelechat.libs.exports.locations.RegionalDistances;
import fr.guiguilechat.jcelechat.libs.exports.locations.SolarSystem;

public class RegionalDistancesTest {

	@Test
	public void testJitaPerimeter() {
		SolarSystem Jita = SolarSystem.getSystem("Jita");
		SolarSystem Perimeter = SolarSystem.getSystem("Perimeter");
		SolarSystem Itamo = SolarSystem.getSystem("Itamo");
		SolarSystem Mahtista = SolarSystem.getSystem("Mahtista");
		Region TheForge = Region.getRegion("The Forge");
		RegionalDistances test = new RegionalDistances(TheForge.id);
		Assert.assertEquals(test.distance(Jita, Perimeter), 1);
		Assert.assertEquals(test.distance(Jita, Itamo), 2);
		Assert.assertEquals(test.distance(Jita, Mahtista), 4);
	}

}
