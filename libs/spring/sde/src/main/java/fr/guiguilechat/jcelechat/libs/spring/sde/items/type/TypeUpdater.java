package fr.guiguilechat.jcelechat.libs.spring.sde.items.type;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup.MarketGroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.metagroup.MetaGroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.items.type")
public class TypeUpdater extends SdeEntityUpdater<Type, TypeService, Etypes> {

	public TypeUpdater() {
		super(Etypes.SDE_FILE_YAML, Etypes.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private GroupService groupService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private MarketGroupService marketGroupService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private MetaGroupService metaGroupService;

	@Override
	protected void processSource(LinkedHashMap<Integer, Etypes> sources) {
		// be sure all the required variations exist
		var getType = service()
				.getter(sources.values().stream().map(p -> p.variationParentTypeID).filter(i -> i != null && i != 0));
		var getGroup = groupService().getter(sources.values().stream().map(p -> p.groupID));
		var getMarketGroup = marketGroupService()
				.getter(sources.values().stream().map(p -> p.marketGroupID).filter(i -> i != null && i != 0));
		var getMetaGroup = metaGroupService()
				.getter(sources.values().stream().map(p -> p.metaGroupID).filter(i -> i != null && i != 0)) ;

		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getType, getGroup, getMarketGroup, getMetaGroup);
		}
		service().saveAll(storedEntities.values());
	}

}
