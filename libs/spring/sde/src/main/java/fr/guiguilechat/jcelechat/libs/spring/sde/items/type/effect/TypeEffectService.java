package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class TypeEffectService extends DeducedEntityService<TypeEffect, TypeEffectRepository> {

	public List<TypeEffect> byEffectId(int effectId) {
		return repo().findAllByEffectId(effectId);
	}

	public List<TypeEffect> bytTypeId(int typeId) {
		return repo().findAllByTypeId(typeId);
	}

}
