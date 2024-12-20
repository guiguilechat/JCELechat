package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;

public interface ContractInfoRepository
    extends IRemoteEntityRepository<ContractInfo, Integer>, JpaSpecificationExecutor<ContractInfo> {

	public List<ContractInfo> findByRegionIdAndRemovedFalseAndFetchedTrue(int regionId);

	public List<ContractInfo> findByRegionInAndRemovedFalse(Iterable<ContractRegion> regions);

	public List<ContractInfo> findByFetchActiveTrueAndRemovedFalseAndExpiresLessThanOrderByExpiresAsc(Instant now,
	    Limit limit);

	@EntityGraph(attributePaths = { "items", "items.type.group.category" })
	public Stream<ContractInfo> findByTypeAndFetchedTrueAndRemovedFalseAndOffersNonBpcTrueAndRequestsItemFalse(
	    get_contracts_public_region_id_type contractType);

	@EntityGraph(attributePaths = { "items", "items.type.group.category" })
	public Stream<ContractInfo> findByTypeAndFetchedTrueAndRemovedFalseAndRequestsItemTrueAndOffersItemFalse(
	    get_contracts_public_region_id_type contractType);

	public List<ContractInfo> findByCompletedTrueAndOffersOneTypeForIskTrueAndOfferedTypeId(int typeId);

	public List<ContractInfo> findByCompletedTrueAndOffersOneTypeForIskTrueAndOfferedTypeIdAndOfferedCopyAndOfferedMeAndOfferedTe(
	    int typeId, boolean copy, int me, int te);

	public List<ContractInfo> findByRemovedFalseAndOffersOneTypeForIskTrueAndOfferedTypeId(int typeId);

	public List<ContractInfo> findByRemovedFalseAndOffersOneTypeForIskTrueAndOfferedTypeIdAndOfferedCopyAndOfferedMeAndOfferedTe(
	    int typeId, boolean copy, int me, int te);

	@Query("""  
select
	id
from
	EsiTradeContractInfo
where
	id>:lastId
	and fetched
order by id
limit :limit 
""")
	public List<Integer> listIdsByFetchedTrue(long lastId, int limit);

	/** to analyze again */
	@EntityGraph(attributePaths = { "items", "items.type.id" })
	public List<ContractInfo> findByIdIn(List<Integer> ids);

}
