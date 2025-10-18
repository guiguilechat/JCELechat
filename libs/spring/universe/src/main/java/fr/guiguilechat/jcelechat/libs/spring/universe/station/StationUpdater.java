package fr.guiguilechat.jcelechat.libs.spring.universe.station;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.operation.StationOperation;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.operation.StationOperationService;
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
	private StationOperationService stationOperationService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private SolarSystemService solarSystemService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EnpcStations> sources) {

		// load all the operations because most will be needed
		Map<Integer, StationOperation> operations = new HashMap<>(stationOperationService.allById());
		Function<Integer, StationOperation> operationGet = i -> operations.computeIfAbsent(i,
				stationOperationService::create);

		// load all the solar systems because most will be needed
		Map<Integer, SolarSystem> solarSystems = new HashMap<>(solarSystemService.allById());
		Function<Integer, SolarSystem> solarSystemGet = i -> solarSystems.computeIfAbsent(i,
				solarSystemService::create);
		// load only the types needed
		Map<Integer, Type> types = typeService()
				.createIfAbsent(sources.values().stream().map(p -> p.typeID).distinct().toList());
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), types::get, solarSystemGet, operations::get);
		}
		stationOperationService.saveAll(operations.values());
		solarSystemService.saveAll(solarSystems.values());
		service().saveAll(storedEntities.values());
	}

}
