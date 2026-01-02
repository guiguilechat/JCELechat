package fr.guiguilechat.jcelechat.libs.spring.sde.space.station.operation;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EstationOperations;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
@ConfigurationProperties(prefix = "sde.space.station.operation")
public class StationOperationUpdater
		extends
		SdeEntityUpdater<StationOperation, StationOperationRepository, StationOperationService, EstationOperations> {

	public StationOperationUpdater() {
		super(EstationOperations.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EstationOperations> sources) {
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
