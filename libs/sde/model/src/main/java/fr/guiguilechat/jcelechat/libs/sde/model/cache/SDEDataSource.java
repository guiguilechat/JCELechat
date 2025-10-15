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

public interface SDEDataSource {

	EntityMap<AsteroidBelt> asteroidBelts();

	EntityNameMap<Category> categories();

	EntityMap<Constellation> constellations();

	EntityNameMap<Group> groups();

	EntityMap<MarketGroup> marketGroups();

	EntityMap<Moon> moons();

	EntityMap<Planet> planets();

	EntityMap<Region> regions();

	EntityMap<SolarSystem> solarSystems();

	EntityMap<Star> stars();

	EntityMap<Stargate> stargates();

	EntityMap<Station> stations();

	EntityNameMap<Type> types();

}
