package fr.guiguilechat.jcelechat.libs.sde.model.cache;

import fr.guiguilechat.jcelechat.libs.sde.model.industry.BluePrint;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Attribute;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Category;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Compression;
import fr.guiguilechat.jcelechat.libs.sde.model.items.DynamicItem;
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
 * {@link DataSource} that resolves the elements using a local file cache
 */
public class DataSourceLocalCache implements DataSource {

	public static final DataSourceLocalCache INSTANCE = new DataSourceLocalCache();

	@Override
	public EntityNameMap<AgentType> agentTypes() {
		return AgentType.CACHE;
	}

	@Override
	public EntityMap<AsteroidBelt> asteroidBelts() {
		return AsteroidBelt.CACHE;
	}

	@Override
	public EntityMap<Attribute> attributes() {
		return Attribute.CACHE;
	}

	@Override
	public EntityMap<BluePrint> blueprints() {
		return BluePrint.CACHE;
	}

	@Override
	public EntityNameMap<Category> categories() {
		return Category.CACHE;
	}

	@Override
	public EntityMap<Compression> compressions() {
		return Compression.CACHE;
	}

	@Override
	public EntityNameMap<Constellation> constellations() {
		return Constellation.CACHE;
	}

	@Override
	public EntityMap<DynamicItem> dynamicItems() {
		return DynamicItem.CACHE;
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
	public EntityNameMap<NPCCharacter> npcCharacters() {
		return NPCCharacter.CACHE;
	}

	@Override
	public EntityMap<NPCCorporation> npcCorporations() {
		return NPCCorporation.CACHE;
	}

	@Override
	public EntityMap<NPCCorporationDivision> npcCorporationDivisions() {
		return NPCCorporationDivision.CACHE;
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
	public EntityMap<StationOperation> stationOperations() {
		return StationOperation.CACHE;
	}

	@Override
	public EntityMap<StationService> stationServices() {
		return StationService.CACHE;
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
