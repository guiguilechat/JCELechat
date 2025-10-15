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
 * several entity maps to load the elements from
 */
public interface DataSource {

	EntityMap<AsteroidBelt> asteroidBelts();

	EntityNameMap<Category> categories();

	EntityNameMap<Constellation> constellations();

	EntityNameMap<Group> groups();

	EntityMap<MarketGroup> marketGroups();

	EntityMap<Moon> moons();

	EntityMap<Planet> planets();

	EntityNameMap<Region> regions();

	EntityNameMap<SolarSystem> solarSystems();

	EntityMap<Star> stars();

	EntityMap<Stargate> stargates();

	EntityNameMap<Station> stations();

	EntityNameMap<Type> types();

}
