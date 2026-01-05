package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface MutaMultiplierRepository extends DeducedEntityRepository<MutaMultiplier> {

	List<MutaMultiplier> findByMutaplasmidIdIn(Iterable<Integer> mutaplasmidIds);

}
