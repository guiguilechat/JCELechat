package fr.guiguilechat.jcelechat.libs.spring.universe.station.operation;

import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EstationOperations;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

public class StationOperationUpdater
		extends SdeEntityUpdater<StationOperation, StationOperationService, EstationOperations> {

	public StationOperationUpdater() {
		super(EstationOperations.SDE_FILE_YAML, EstationOperations.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EstationOperations> sources) {
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		service().saveAll(storedEntities.values());
	}

}
