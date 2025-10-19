package fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
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
		var getConstel = constellationService.getterAll();
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getConstel);
		}
		service().saveAll(storedEntities.values());
	}

}
