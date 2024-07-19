package fr.guiguilechat.jcelechat.model.sde.main;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Universe;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.EConstellation;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ERegion;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem;

public class ShowKimotoroMaxBM {

	public static void main(String[] args) {
		ERegion region = Universe.load().eve.get("TheForge");
		for (Entry<String, EConstellation> ec : region.constellations.entrySet()) {
			String constName = ec.getKey();
			EConstellation constellation = ec.getValue();
			for (Entry<String, ESolarSystem> es : constellation.systems.entrySet()) {
				String ssName = es.getKey();
				ESolarSystem ss = es.getValue();
				System.out.println(constName + " \t" + ssName + "\t" + ss.security + "\t" + ss.furthestCelestialAU());
			}
		}
	}

}
