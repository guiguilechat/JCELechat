package fr.guiguilechat.jcelechat.libs.geocenter;

import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.locations.Invasions;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class CenterHSEdencomMain {

	public static void main(String[] args) {
		SolarSystem ss = SolarSystem.getSystem("Tar");
		Set<SolarSystem> invaded = Invasions.INSTANCE.getDangerousHSSystems(false, true);
		GeoCenter.print(GeoCenter.evaluate(ss, s -> s.isHS() && !invaded.contains(s)), null);
	}

}
