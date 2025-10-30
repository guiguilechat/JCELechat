package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaEffects;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaEffects.ModifierInfo;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.modifier.Modifier;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.modifier.ModifierService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.items.dogmaeffect")
public class EffectUpdater extends SdeEntityUpdater<Effect, EffectService, EdogmaEffects> {

	public EffectUpdater() {
		super(EdogmaEffects.SDE_FILE_YAML, EdogmaEffects.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private AttributeService attributeService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private GroupService groupService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ModifierService modifierService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	@Override
	public void beforeSdeUpdate() {
		super.beforeSdeUpdate();
		if (!skip) {
			modifierService.deleteAll();
		}
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EdogmaEffects> sources) {
		var getAttribute = attributeService.getterAll();
		var getGroup = groupService.getterAll();
		var getType = typeService.getterAll();

		BiConsumer<Effect, ModifierInfo> saveEffect = (e, mi) -> modifierService
				.save(Modifier.of(mi, e, getGroup, getAttribute, getType));

		var storedEntities = new HashMap<>(service().allById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getAttribute, saveEffect);
		}
		service().saveAll(storedEntities.values());
	}
}
