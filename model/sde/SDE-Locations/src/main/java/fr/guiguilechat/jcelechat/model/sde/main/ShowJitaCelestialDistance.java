package fr.guiguilechat.jcelechat.model.sde.main;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Universe;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Moon;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Planet;

public class ShowJitaCelestialDistance {

	public static void main(String[] args) {
		Region TheForge = Universe.load().eve.get("TheForge");
		Constellation Kimotoro = TheForge.constellations.get("Kimotoro");
		SolarSystem Jita = Kimotoro.systems.get("Jita");
		System.out.println("system Jita has highest distance : " + Jita.furthestCelestialAU());
		for (Planet p : Jita.planets.values()) {
			System.out.println("" + p.celestialIndex + "\t" + p.position.distanceFromOriginInAU() + "\t");
			for (Moon m : p.moons.values()) {
				System.out.println("\t\t" + m.position.distanceFromOriginInAU());
			}
		}
	}

}
