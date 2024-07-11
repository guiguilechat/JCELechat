package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface ContractInfoRepository extends IRemoteResourceRepository<ContractInfo, Integer> {

	public List<ContractInfo> findByRegionAndRemovedFalse(ContractRegion region);


}
