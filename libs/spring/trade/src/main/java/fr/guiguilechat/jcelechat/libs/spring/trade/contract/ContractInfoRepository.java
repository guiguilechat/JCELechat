package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface ContractInfoRepository extends IRemoteEntityRepository<ContractInfo, Integer> {

	public List<ContractInfo> findByRegionIdAndRemovedFalseAndFetchedTrue(int regionId);

	public List<ContractInfo> findByRegionInAndRemovedFalse(Iterable<ContractRegion> regions);

	public List<ContractInfo> findByFetchActiveTrueAndRemovedFalseAndExpiresLessThanOrderByExpiresAsc(Instant now,
	    Limit limit);


	@Query("""
select
	distinct(fetchResource)
from
	EsiMarketContractItem
where
	type in :types
	and included = :included
	and fetchResource.fetched
	and not fetchResource.removed
""")
	@EntityGraph(attributePaths = { "items" })
	public Stream<ContractInfo> findWithTypeIncluded(Iterable<Type> types, boolean included);

}
