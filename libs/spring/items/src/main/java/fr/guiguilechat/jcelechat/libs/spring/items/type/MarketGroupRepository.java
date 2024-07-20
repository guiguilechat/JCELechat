package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface MarketGroupRepository extends IRemoteResourceRepository<MarketGroup, Integer> {

	public List<MarketGroup> findByParentNull();

}
