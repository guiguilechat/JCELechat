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
import fr.guiguilechat.jcelechat.libs.sde.model.locations.station.StationOperation;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.station.StationService;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.AgentType;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.NPCCharacter;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.NPCCorporation;
import fr.guiguilechat.jcelechat.libs.sde.model.npcs.NPCCorporationDivision;

/**
 * several entity maps to load the elements from
 */
public interface DataSource {

	EntityNameMap<AgentType> agentTypes();

	EntityMap<AsteroidBelt> asteroidBelts();

	EntityNameMap<Category> categories();

	EntityNameMap<Constellation> constellations();

	EntityNameMap<Group> groups();

	EntityMap<MarketGroup> marketGroups();

	EntityMap<Moon> moons();

	EntityNameMap<NPCCharacter> npcCharacters();

	EntityMap<NPCCorporation> npcCorporations();

	EntityMap<NPCCorporationDivision> npcCorporationDivisions();

	EntityMap<Planet> planets();

	EntityNameMap<Region> regions();

	EntityNameMap<SolarSystem> solarSystems();

	EntityMap<Star> stars();

	EntityMap<Stargate> stargates();

	EntityMap<StationOperation> stationOperations();

	EntityMap<StationService> stationServices();

	EntityNameMap<Station> stations();

	EntityNameMap<Type> types();

}
