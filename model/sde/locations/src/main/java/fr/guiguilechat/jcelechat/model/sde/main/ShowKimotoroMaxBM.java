package fr.guiguilechat.jcelechat.model.sde.main;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Universe;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem;

public class ShowKimotoroMaxBM {

	public static void main(String[] args) {
		Region region = Universe.load().eve.get("TheForge");
		for (Entry<String, Constellation> ec : region.constellations.entrySet()) {
			String constName = ec.getKey();
			Constellation constellation = ec.getValue();
			for (Entry<String, SolarSystem> es : constellation.systems.entrySet()) {
				String ssName = es.getKey();
				SolarSystem ss = es.getValue();
				System.out.println(constName + " \t" + ssName + "\t" + ss.security + "\t" + ss.furthestCelestialAU());
			}
		}
	}

}
