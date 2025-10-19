package fr.guiguilechat.jcelechat.libs.spring.sde.universe.star;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStars;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
public class StarUpdater extends SdeEntityUpdater<Star, StarService, EmapStars> {

	public StarUpdater() {
		super(EmapStars.SDE_FILE_YAML, EmapStars.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private SolarSystemService solarSystemService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapStars> sources) {
		var getSystem = solarSystemService().getterAll();
		var getType = typeService().getter(sources.values().stream().map(p -> p.typeID));
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getType, getSystem);
		}
		service().saveAll(storedEntities.values());
	}

}
