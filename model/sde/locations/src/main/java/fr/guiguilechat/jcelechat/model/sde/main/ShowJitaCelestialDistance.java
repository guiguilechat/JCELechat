package fr.guiguilechat.jcelechat.model.sde.main;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Universe;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.EConstellation;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ERegion;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem.Moon;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem.Planet;

public class ShowJitaCelestialDistance {

	public static void main(String[] args) {
		ERegion TheForge = Universe.load().eve.get("TheForge");
		EConstellation Kimotoro = TheForge.constellations.get("Kimotoro");
		ESolarSystem Jita = Kimotoro.systems.get("Jita");
		System.out.println("system Jita has highest distance : " + Jita.furthestCelestialAU());
		for (Planet p : Jita.planets.values()) {
			System.out.println("" + p.celestialIndex + "\t" + p.position.distanceFromOriginInAU() + "\t");
			for (Moon m : p.moons.values()) {
				System.out.println("\t\t" + m.position.distanceFromOriginInAU());
			}
		}
	}

}
