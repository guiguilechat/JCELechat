package fr.guiguilechat.jcelechat.libs.spring.universe.moon;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapMoons;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.PlanetService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
public class MoonUpdater extends SdeEntityUpdater<Moon, MoonService, EmapMoons> {

	public MoonUpdater() {
		super(EmapMoons.SDE_FILE_YAML, EmapMoons.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private PlanetService planetService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private SolarSystemService solarSystemService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapMoons> sources) {
		// load only the referenced planets
		Map<Integer, Planet> planets = planetService()
				.createIfAbsent(sources.values().stream().map(p -> p.orbitID).distinct().toList());
		// load all the solar systems because most will be needed
		Map<Integer, SolarSystem> solarSystems = new HashMap<>(solarSystemService.allById());
		Function<Integer, SolarSystem> solarSystemGet = i -> solarSystems.computeIfAbsent(i,
				solarSystemService::create);
		// load only the referenced types
		Map<Integer, Type> types = typeService()
				.createIfAbsent(sources.values().stream().map(p -> p.typeID).distinct().toList());
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), types::get, solarSystemGet, planets::get);
		}
		solarSystemService.saveAll(solarSystems.values());
		service().saveAll(storedEntities.values());
	}

}
