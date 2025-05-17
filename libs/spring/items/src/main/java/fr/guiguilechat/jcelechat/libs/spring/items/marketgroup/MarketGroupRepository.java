package fr.guiguilechat.jcelechat.libs.spring.items.marketgroup;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface MarketGroupRepository extends IRemoteEntityRepository<MarketGroup, Integer> {

	public List<MarketGroup> findByParentNull();

}
