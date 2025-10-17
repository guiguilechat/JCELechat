package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.ConstellationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
public class SolarSystemUpdater extends SdeEntityUpdater<SolarSystem, SolarSystemService, EmapSolarSystems> {

	public SolarSystemUpdater() {
		super(EmapSolarSystems.SDE_FILE_YAML, EmapSolarSystems.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ConstellationService constellationService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapSolarSystems> sources) {
		Map<Integer, Constellation> constellations = new HashMap<>(constellationService.allById());
		Function<Integer, Constellation> constellationGetter = i -> constellations.computeIfAbsent(i,
				constellationService::create);
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), constellationGetter);
		}
		constellationService.saveAll(constellations.values());
		service().saveAll(storedEntities.values());
	}

}
