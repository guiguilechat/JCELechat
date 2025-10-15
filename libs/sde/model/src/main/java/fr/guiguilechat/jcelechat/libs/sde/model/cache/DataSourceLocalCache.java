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

/**
 * datasource that resolves the elements using a local file cache
 */
public class DataSourceLocalCache implements DataSource {

	public static final DataSourceLocalCache INSTANCE = new DataSourceLocalCache();

	@Override
	public EntityMap<AsteroidBelt> asteroidBelts() {
		return AsteroidBelt.CACHE;
	}

	@Override
	public EntityNameMap<Category> categories() {
		return Category.CACHE;
	}

	@Override
	public EntityNameMap<Constellation> constellations() {
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
	public EntityNameMap<Region> regions() {
		return Region.CACHE;
	}

	@Override
	public EntityNameMap<SolarSystem> solarSystems() {
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
	public EntityNameMap<Station> stations() {
		return Station.CACHE;
	}

	@Override
	public EntityNameMap<Type> types() {
		return Type.CACHE;
	}

}
