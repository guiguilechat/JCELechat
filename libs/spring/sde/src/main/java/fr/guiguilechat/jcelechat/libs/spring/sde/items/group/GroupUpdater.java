package fr.guiguilechat.jcelechat.libs.spring.sde.items.group;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Egroups;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.CategoryService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.items.group")
public class GroupUpdater extends SdeEntityUpdater<Group, GroupService, Egroups> {

	public GroupUpdater() {
		super(Egroups.SDE_FILE_YAML, Egroups.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private CategoryService categoryService;

	@Override
	protected void processSource(LinkedHashMap<Integer, Egroups> sources) {
		var getCategory = categoryService.getterAll();
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(),
					getCategory);
		}
		service().saveAll(storedEntities.values());
	}

}
