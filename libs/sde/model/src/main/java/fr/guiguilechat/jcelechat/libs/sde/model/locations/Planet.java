package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapPlanets;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AOrbitingCelestial;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Planet extends AOrbitingCelestial<EmapPlanets> {

	public static final Mapper<EmapPlanets, Planet> CACHE = new Mapper<>(EmapPlanets.LOADER, Planet::new);

	protected Planet(int id, EmapPlanets source) {
		super(id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Getter(lazy = true)
	private final List<Moon> moons = Moon.CACHE.of(source().moonIDs);

	@Getter(lazy = true)
	private final List<AsteroidBelt> asteroidBelts = AsteroidBelt.CACHE.of(source().asteroidBeltIDs);

	@Getter(lazy = true)
	private final List<Station> stations = Station.CACHE.of(source().npcStationIDs);

}
