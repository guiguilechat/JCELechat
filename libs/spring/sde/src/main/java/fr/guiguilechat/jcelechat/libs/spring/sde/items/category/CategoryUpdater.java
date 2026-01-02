package fr.guiguilechat.jcelechat.libs.spring.sde.items.category;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Ecategories;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
@ConfigurationProperties(prefix = "sde.items.category")
public class CategoryUpdater extends SdeEntityUpdater<Category, CategoryRepository, CategoryService, Ecategories> {

	public CategoryUpdater() {
		super(Ecategories.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, Ecategories> sources) {
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
