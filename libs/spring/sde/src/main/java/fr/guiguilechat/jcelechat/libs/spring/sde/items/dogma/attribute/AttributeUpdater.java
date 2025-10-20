package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaAttributes;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.category.AttributeCategoryService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.unit.UnitService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.items.attribute")
public class AttributeUpdater extends SdeEntityUpdater<Attribute, AttributeService, EdogmaAttributes> {

	public AttributeUpdater() {
		super(EdogmaAttributes.SDE_FILE_YAML, EdogmaAttributes.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private AttributeCategoryService attributeCategoryService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private UnitService unitService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EdogmaAttributes> sources) {
		var getAttribute = service().getter(sources.values().stream()
				.flatMap(a -> Stream.of(a.chargeRechargeTimeID, a.maxAttributeID, a.minAttributeID))
				.filter(i -> i != null && i != 0));
		var getAttributeCategory = attributeCategoryService
				.getter(sources.values().stream()
						.map(a -> a.attributeCategoryID)
						.filter(i -> i != null && i != 0));
		var getUnit = unitService.getterAll();
		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getAttribute, getAttributeCategory, getUnit);
		}
		service().saveAll(storedEntities.values());
	}

}
