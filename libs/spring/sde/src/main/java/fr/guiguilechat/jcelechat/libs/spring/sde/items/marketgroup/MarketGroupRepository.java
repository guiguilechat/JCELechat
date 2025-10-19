package fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface MarketGroupRepository extends SdeEntityRepository<MarketGroup, Integer> {

	List<MarketGroup> findByParentNull();

}
