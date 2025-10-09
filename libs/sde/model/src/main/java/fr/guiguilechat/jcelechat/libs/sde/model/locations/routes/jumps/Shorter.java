package fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.jumps;

import fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem;

public class Shorter extends Safer {

	public Shorter(SolarSystem start) {
		super(start);
	}

	@Override
	protected double distance(SolarSystem start, SolarSystem end) {
		return 1.0;
	}

}
