package fr.guiguilechat.jcelechat.libs.spring.sde.space.region;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapRegions;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
@ConfigurationProperties(prefix = "sde.space.region")
public class RegionUpdater extends SdeEntityUpdater<Region, RegionRepository, RegionService, EmapRegions> {

	public RegionUpdater() {
		super(EmapRegions.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapRegions> sources) {
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
