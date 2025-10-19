package fr.guiguilechat.jcelechat.libs.spring.sde.space.station;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
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
		var getOperation = stationOperationService().getterAll();
		var getSystem = solarSystemService().getterAll();
		var getType = typeService().getter(sources.values().stream().map(p -> p.typeID));
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getType, getSystem, getOperation);
		}
		service().saveAll(storedEntities.values());
	}

}
