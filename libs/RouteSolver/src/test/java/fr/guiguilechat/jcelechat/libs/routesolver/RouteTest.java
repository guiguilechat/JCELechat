package fr.guiguilechat.jcelechat.libs.routesolver;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class RouteTest {

	@Test
	public void testPrintJitaHek() {
		Route r = new Route();
		r.add(SolarSystem.getSystem("Jita"));
		r.add(SolarSystem.getSystem("Hek"));

		String print1 = r.print(false);
		Assert.assertEquals(print1, "10\tJita\n20\tHek\n");

		String print2 = r.print(true);
		Assert.assertEquals(print2, "10\tJita\tKimotoro\n20\tHek\tBarvigrard\n");
	}

	@Test
	public void testPrintJitaMaurasiItamo() {
		Route r = new Route();
		r.add(SolarSystem.getSystem("Jita"));
		r.add(SolarSystem.getSystem("Maurasi"));
		r.add(SolarSystem.getSystem("Itamo"));

		String print1 = r.print(false);
		Assert.assertEquals(print1, "10\tJita\n11\tMaurasi\n20\tItamo\n");

		String print2 = r.print(true);
		Assert.assertEquals(print2, "10\tJita\tKimotoro\n11\tMaurasi\tKimotoro\n20\tItamo\tRuomo\n");
	}

}
