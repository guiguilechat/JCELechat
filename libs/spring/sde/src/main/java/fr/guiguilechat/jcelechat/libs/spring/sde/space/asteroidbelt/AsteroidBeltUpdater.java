package fr.guiguilechat.jcelechat.libs.spring.sde.space.asteroidbelt;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapAsteroidBelts;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.planet.PlanetService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.space.asteroidbelt")
public class AsteroidBeltUpdater
		extends SdeEntityUpdater<AsteroidBelt, AsteroidBeltRepository, AsteroidBeltService, EmapAsteroidBelts> {

	public AsteroidBeltUpdater() {
		super(EmapAsteroidBelts.LOADER);
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
	protected void processSource(LinkedHashMap<Integer, EmapAsteroidBelts> sources) {
		var getType = typeService.getter(sources.values().stream().map(p -> p.typeID));
		var getSystem = solarSystemService.getterAll();
		var getPlanet = planetService.getter(sources.values().stream().map(p -> p.orbitID));
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(),
					getType,
					getSystem,
					getPlanet);
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
