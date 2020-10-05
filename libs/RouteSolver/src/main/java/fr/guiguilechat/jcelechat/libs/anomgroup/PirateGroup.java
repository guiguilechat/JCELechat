package fr.guiguilechat.jcelechat.libs.anomgroup;

import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class PirateGroup {

	public Set<SolarSystem> denHS = new HashSet<>(), refuge = new HashSet<>(), burrow = new HashSet<>(),
			hideway = new HashSet<>();

	public void accept(SolarSystem ss) {
		if (ss.truesec > 0.85) {
			burrow.add(ss);
		}
		if (ss.truesec > 0.55) {
			hideway.add(ss);
		}
		if (ss.truesec > 0.45 && ss.truesec < 0.95) {
			refuge.add(ss);
		}
		if (ss.truesec > 0.45 && ss.truesec < 0.65) {
			denHS.add(ss);
		}
	}

}
