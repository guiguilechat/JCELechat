package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporations;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

public class NpcCorporationUpdater extends SdeEntityUpdater<NpcCorporation, NpcCorporationService, EnpcCorporations> {

	public NpcCorporationUpdater() {
		super(EnpcCorporations.SDE_FILE_YAML, EnpcCorporations.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private SolarSystemService solarSystemService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private StationService stationService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EnpcCorporations> sources) {
		var getSystem = solarSystemService()
				.getter(sources.values().stream().map(p -> p.solarSystemID).filter(i -> i != 0));
		var getStation = stationService().getter(sources.values().stream().map(p -> p.stationID).filter(i -> i != 0));
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getSystem, getStation);
		}
		service().saveAll(storedEntities.values());
	}

}
