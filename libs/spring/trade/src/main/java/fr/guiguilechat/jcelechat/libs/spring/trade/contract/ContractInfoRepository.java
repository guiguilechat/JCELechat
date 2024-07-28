package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface ContractInfoRepository extends IRemoteEntityRepository<ContractInfo, Integer> {

	public List<ContractInfo> findByRegionIdAndRemovedFalseAndFetchedTrue(int regionId);

	public List<ContractInfo> findByRegionInAndRemovedFalse(Iterable<ContractRegion> regions);

	public List<ContractInfo> findByFetchActiveTrueAndRemovedFalseAndExpiresLessThanOrderByExpiresAsc(Instant now,
	    Limit limit);

}
