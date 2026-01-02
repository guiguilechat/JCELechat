package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.unit;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaUnits;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
public class UnitUpdater extends SdeEntityUpdater<Unit, UnitRepository, UnitService, EdogmaUnits> {

	public UnitUpdater() {
		super(EdogmaUnits.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EdogmaUnits> sources) {
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
