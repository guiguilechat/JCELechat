package fr.guiguilechat.jcelechat.libs.spring.sde.items.metagroup;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmetaGroups;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
@ConfigurationProperties(prefix = "sde.items.metagroup")
public class MetaGroupUpdater extends SdeEntityUpdater<MetaGroup, MetaGroupService, EmetaGroups> {

	public MetaGroupUpdater() {
		super(EmetaGroups.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EmetaGroups> sources) {
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		service().saveAll(storedEntities.values());
	}

}
