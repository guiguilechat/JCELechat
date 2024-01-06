package fr.guiguilechat.jcelechat.libs.geocenter;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class CenterHSMain {

	public static void main(String[] args) {
		SolarSystem ss = SolarSystem.getSystem("Tar");
		GeoCenter.print(GeoCenter.evaluate(ss, SolarSystem::isHS), null);
	}

}
