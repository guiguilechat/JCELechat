package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.category;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaAttributeCategories;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
@ConfigurationProperties(prefix = "sde.items.attribute.category")
public class AttributeCategoryUpdater
		extends
		SdeEntityUpdater<AttributeCategory, AttributeCategoryRepository, AttributeCategoryService, EdogmaAttributeCategories> {

	public AttributeCategoryUpdater() {
		super(EdogmaAttributeCategories.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EdogmaAttributeCategories> sources) {
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
