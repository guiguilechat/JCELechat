package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface TypeEffectRepository extends DeducedEntityRepository<TypeEffect> {

	List<TypeEffect> findAllByEffectId(int effectId);

	List<TypeEffect> findAllByTypeId(int typeId);

}
