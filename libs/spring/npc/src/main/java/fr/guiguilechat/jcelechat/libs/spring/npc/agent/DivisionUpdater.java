package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporationDivisions;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DivisionUpdater extends SdeEntityUpdater<Division, DivisionService, EnpcCorporationDivisions> {

	public DivisionUpdater() {
		super(EnpcCorporationDivisions.SDE_FILE_YAML, EnpcCorporationDivisions.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EnpcCorporationDivisions> sources) {
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		service().saveAll(storedEntities.values());
	}

}
