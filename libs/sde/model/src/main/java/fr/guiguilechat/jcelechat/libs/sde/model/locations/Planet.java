package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.Collection;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapPlanets;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.LocationName;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AStarOrbit;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Planet extends AStarOrbit<EmapPlanets> {

	public static final Mapper<EmapPlanets, Planet> CACHE = new Mapper<>(EmapPlanets.LOADER, Planet::new);

	protected Planet(DataSource datasource,int id, EmapPlanets source) {
		super(datasource, id, source);
	}

	protected Planet(int id, EmapPlanets source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return LocationName.of(source());
	}

	@Override
	protected Position makePosition() {
		return source().position.add(solarSystem().position());
	}

	@Getter(lazy = true)
	private final Collection<Moon> moons = datasource().moons().of(source().moonIDs);

	@Getter(lazy = true)
	private final Collection<AsteroidBelt> asteroidBelts = datasource().asteroidBelts().of(source().asteroidBeltIDs);

	@Getter(lazy = true)
	private final Collection<Station> stations = datasource().stations().of(source().npcStationIDs);

}
