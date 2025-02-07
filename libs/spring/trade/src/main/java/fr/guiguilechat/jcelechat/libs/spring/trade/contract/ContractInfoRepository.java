package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.util.Collection;
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

	/** completed contracts providing only given types and iscopy, me, te */
	public List<ContractInfo> findByCompletedTrueAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTeOrderByRemovedBeforeDesc(
	    Collection<Integer> typeIds, boolean copy, int me, int te, Limit limit);

	/** open contracts providing only given type and iscopy, me, te */
	public List<ContractInfo> findByRemovedFalseAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeAndOfferedTe(
	    Collection<Integer> typeIds, boolean copy, int me, int te);

	/**
	 * open contracts providing only given type and iscopy, with minimal me and te
	 */
	public List<ContractInfo> findByRemovedFalseAndOffersItemTrueAndRequestsItemFalseAndOfferedTypeIdInAndOfferedCopyAndOfferedMeGreaterThanEqualAndOfferedTeGreaterThanEqual(
	    Collection<Integer> typeIds, boolean copy, int me, int te);

	/** open contracts requesting only given types */
	public List<ContractInfo> findByRemovedFalseAndAsksOneTypeForIskTrueAndAskedTypeIdIn(Collection<Integer> typeIds);

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

	/**
	 * list the variants of the type offered in contract, except the 0/0/noncopy one
	 * 
	 * @param typeId a type
	 * @return distinct (me, te, copy) of contracts offering given typeId
	 */
	@Query("""
select
	offeredMe, offeredTe, offeredCopy
from
	EsiTradeContractInfo
where
	offeredTypeId=:typeId
	and (offeredMe>0 or offeredTe>0 or offeredCopy)
group by offeredMe, offeredTe, offeredCopy
""")
	public List<Object[]> listTypeVariants(int typeId);

	@Query("""
select
	offeredTypeId typeId,
	sum(price) totalValue,
	sum(offeredQuantity) totalQuantity
from
	EsiTradeContractInfo
where
	completed
	and offersItem
	and not requestsItem
	and offeredMe=0
	and offeredTe=0
	and not offeredCopy
	and removedBefore>= :minDate
	and removedBefore<= :maxDate
group by offeredTypeId
order by sum(price) desc
limit :limit
""")
	public List<Object[]> aggregateUnresearchedHighestSales(Instant minDate, Instant maxDate, int limit);

	@Query("""
select
	offeredTypeId typeId,
	offeredMe,
	offeredTe,	
	sum(price) totalValue,
	sum(offeredQuantity) totalQuantity
from
	EsiTradeContractInfo
where
	completed
	and offersItem
	and not requestsItem
	and (offeredMe>0 or offeredTe>0)
	and not offeredCopy
	and removedBefore>= :minDate
	and removedBefore<= :maxDate
group by
	offeredTypeId,
	offeredMe,
	offeredTe
order by sum(price) desc
limit :limit
""")
	public List<Object[]> aggregateResearchedHighestSales(Instant minDate, Instant maxDate, int limit);

	@Query("""
select
	offeredTypeId typeId,
	offeredMe,
	offeredTe,	
	sum(price) totalValue,
	sum(offeredRuns) totalRuns
from
	EsiTradeContractInfo
where
	completed
	and offersItem
	and not requestsItem
	and offeredCopy
	and removedBefore>= :minDate
	and removedBefore<= :maxDate
group by
	offeredTypeId,
	offeredMe,
	offeredTe
order by sum(price) desc
limit :limit
""")
	public List<Object[]> aggregateBpcHighestSales(Instant minDate, Instant maxDate, int limit);

}
