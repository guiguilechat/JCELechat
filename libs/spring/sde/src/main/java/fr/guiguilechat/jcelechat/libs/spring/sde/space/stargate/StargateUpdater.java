package fr.guiguilechat.jcelechat.libs.spring.sde.space.stargate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapStargates;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.space.stargate")
public class StargateUpdater extends SdeEntityUpdater<Stargate, StargateService, EmapStargates> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private SolarSystemService solarSystemService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	public StargateUpdater() {
		super(EmapStargates.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EmapStargates> sources) {
		var getSystem = solarSystemService().getterAll();
		var getType = typeService().getter(sources.values().stream().map(p -> p.typeID));
		var storedEntities = new HashMap<>(service().allById());
		Function<Integer, Stargate> getStargate = stargateId -> storedEntities.computeIfAbsent(stargateId, service()::create);
		for (var e : sources.entrySet()) {
			var stored = getStargate.apply(e.getKey());
			stored.update(e.getValue(),
					getType,
					getSystem,
					getStargate);
		}
		service().saveAll(storedEntities.values());
	}

}
