package fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmarketGroups;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
@ConfigurationProperties(prefix = "sde.items.marketgroup")
public class MarketGroupUpdater extends SdeEntityUpdater<MarketGroup, MarketGroupService, EmarketGroups> {

	public MarketGroupUpdater() {
		super(EmarketGroups.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EmarketGroups> sources) {
		var getGroup = service()
				.getter(sources.values().stream().map(p -> p.parentGroupID).filter(i -> i != null && i > 0));
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getGroup);
		}
		service().saveAll(storedEntities.values());
	}

}
