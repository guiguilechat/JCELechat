package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapConstellations;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
public class ConstellationUpdater extends SdeEntityUpdater<Constellation, ConstellationService, EmapConstellations> {

	public ConstellationUpdater() {
		super(EmapConstellations.SDE_FILE_YAML, EmapConstellations.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private RegionService regionService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapConstellations> sources) {
		Map<Integer, Region> regions = new HashMap<>(regionService.allById());
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			update(stored, e.getValue(), regions);
		}
		regionService.saveAll(regions.values());
		service().saveAll(storedEntities.values());
	}

	protected void update(Constellation entity,
			EmapConstellations entry,
			Map<Integer, Region> regions) {
		entity.receivedSource();
		entity.setName(entry.enName());
		entity.setPosX(entry.position.x);
		entity.setPosY(entry.position.y);
		entity.setPosZ(entry.position.z);
		entity.setRegion(regions.computeIfAbsent(entry.regionID, regionService::create));
	}

}
