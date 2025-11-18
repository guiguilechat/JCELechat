package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.division;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporationDivisions;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
public class DivisionUpdater extends SdeEntityUpdater<Division, DivisionService, EnpcCorporationDivisions> {

	public DivisionUpdater() {
		super(EnpcCorporationDivisions.LOADER);
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
