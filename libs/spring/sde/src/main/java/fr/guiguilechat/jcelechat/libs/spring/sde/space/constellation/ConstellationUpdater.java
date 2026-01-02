package fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapConstellations;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.space.constellation")
public class ConstellationUpdater
		extends SdeEntityUpdater<Constellation, ConstellationRepository, ConstellationService, EmapConstellations> {

	public ConstellationUpdater() {
		super(EmapConstellations.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private RegionService regionService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapConstellations> sources) {
		var getRegion = regionService.getterAll();
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(),
					getRegion);
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
