package fr.guiguilechat.jcelechat.libs.spring.sde.items.unit;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaUnits;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
public class UnitUpdater extends SdeEntityUpdater<Unit, UnitService, EdogmaUnits> {

	public UnitUpdater() {
		super(EdogmaUnits.SDE_FILE_YAML, EdogmaUnits.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EdogmaUnits> sources) {
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		service().saveAll(storedEntities.values());
	}

}
