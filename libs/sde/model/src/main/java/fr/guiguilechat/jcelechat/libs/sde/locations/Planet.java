package fr.guiguilechat.jcelechat.libs.sde.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapPlanets;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.locations.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.locations.generic.AOrbitingCelestial;
import lombok.Getter;

@Getter
public class Planet extends AOrbitingCelestial<EmapPlanets> {

	public static final Mapper<EmapPlanets, Planet> CACHE = new Mapper<>(EmapPlanets.LOADER, Planet::new);

	protected Planet(int id, EmapPlanets source) {
		super(id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	// TODO asteroidbelts, moons, npcstations

}
