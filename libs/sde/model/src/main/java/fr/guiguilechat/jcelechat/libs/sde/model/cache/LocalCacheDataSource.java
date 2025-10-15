package fr.guiguilechat.jcelechat.libs.sde.model.cache;

import fr.guiguilechat.jcelechat.libs.sde.model.items.Category;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Group;
import fr.guiguilechat.jcelechat.libs.sde.model.items.MarketGroup;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Type;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.AsteroidBelt;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Constellation;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Moon;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Planet;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Region;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Star;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Stargate;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Station;

public class LocalCacheDataSource implements SDEDataSource {

	public static final LocalCacheDataSource INSTANCE = new LocalCacheDataSource();

	@Override
	public EntityMap<AsteroidBelt> asteroidBelts() {
		return AsteroidBelt.CACHE;
	}

	@Override
	public EntityNameMap<Category> categories() {
		return Category.CACHE;
	}

	@Override
	public EntityMap<Constellation> constellations() {
		return Constellation.CACHE;
	}

	@Override
	public EntityNameMap<Group> groups() {
		return Group.CACHE;
	}

	@Override
	public EntityMap<MarketGroup> marketGroups() {
		return MarketGroup.CACHE;
	}

	@Override
	public EntityMap<Moon> moons() {
		return Moon.CACHE;
	}

	@Override
	public EntityMap<Planet> planets() {
		return Planet.CACHE;
	}

	@Override
	public EntityMap<Region> regions() {
		return Region.CACHE;
	}

	@Override
	public EntityMap<SolarSystem> solarSystems() {
		return SolarSystem.CACHE;
	}

	@Override
	public EntityMap<Star> stars() {
		return Star.CACHE;
	}

	@Override
	public EntityMap<Stargate> stargates() {
		return Stargate.CACHE;
	}

	@Override
	public EntityMap<Station> stations() {
		return Station.CACHE;
	}

	@Override
	public EntityNameMap<Type> types() {
		return Type.CACHE;
	}

}
