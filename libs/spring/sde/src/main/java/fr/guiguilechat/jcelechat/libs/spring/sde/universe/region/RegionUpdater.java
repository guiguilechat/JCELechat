package fr.guiguilechat.jcelechat.libs.spring.sde.universe.region;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapRegions;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
public class RegionUpdater extends SdeEntityUpdater<Region, RegionService, EmapRegions> {

	public RegionUpdater() {
		super(EmapRegions.SDE_FILE_YAML, EmapRegions.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapRegions> sources) {
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		service().saveAll(storedEntities.values());
	}

}
