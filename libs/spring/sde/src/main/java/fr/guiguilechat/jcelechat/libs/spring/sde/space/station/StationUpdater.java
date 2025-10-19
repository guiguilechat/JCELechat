package fr.guiguilechat.jcelechat.libs.spring.sde.space.station;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.moon.MoonService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.planet.PlanetService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.star.StarService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.operation.StationOperationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
public class StationUpdater extends SdeEntityUpdater<Station, StationService, EnpcStations> {

	public StationUpdater() {
		super(EnpcStations.SDE_FILE_YAML, EnpcStations.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private MoonService moonService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private NpcCorporationService npcCorporationService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private PlanetService planetService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private StationOperationService stationOperationService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private SolarSystemService solarSystemService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private StarService starService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EnpcStations> sources) {
		var getCorporation = npcCorporationService().getter(sources.values().stream().map(s -> s.ownerID));
		var getMoon = moonService()
				.getter(sources.values().stream().filter(EnpcStations::orbitsMoon).map(s -> s.orbitID));
		var getPlanet = planetService()
				.getter(sources.values().stream().filter(EnpcStations::orbitsPlanet).map(s -> s.orbitID));
		var getOperation = stationOperationService().getterAll();
		var getStar = starService()
				.getter(sources.values().stream().filter(EnpcStations::orbitsStar).map(s -> s.orbitID));
		var getSystem = solarSystemService().getterAll();
		var getType = typeService().getter(sources.values().stream().map(p -> p.typeID));
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(),
					getType,
					getSystem,
					getMoon,
					getCorporation,
					getPlanet,
					getStar,
					getOperation);
		}
		service().saveAll(storedEntities.values());
	}

}
