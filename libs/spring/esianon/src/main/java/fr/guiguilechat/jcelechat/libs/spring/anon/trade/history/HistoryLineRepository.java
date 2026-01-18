package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade.AggregatedTypeHistory;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.IFetchedListElementRepositoryAutoId;

public interface HistoryLineRepository extends IFetchedListElementRepositoryAutoId<HistoryReq, HistoryLine> {

	@Query("""
select
	line
from
	EsiTradeHistoryLine line
where
	line.fetchResource.region.id = :regionId
	and line.fetchResource.type.id = :typeId
""") List<HistoryLine> findByFetchResourceRegionIdAndFetchResourceTypeId(int regionId, int typeId);

	@Query("""
select
	line
from
	EsiTradeHistoryLine line
where
	line.fetchResource.type.id = :typeId
""") List<HistoryLine> findByFetchResourceTypeId(int typeId);

	@Query("""
select
	date date,
	sum(volume) volume,
	sum(volume*average) totalValue,
	max(highest) highestPrice,
	min(lowest) lowestPrice,
	count(*) regions
from
	EsiTradeHistoryLine line
where
	line.fetchResource.type.id = :typeId
	and line.date>=:from
group by date
""") List<Object[]> aggregated(int typeId, Instant from);

	@Query("""
select
	line.fetchResource,
	max(line.date)
from
	EsiTradeHistoryLine line
where
	line.fetchResource in :reqIds
group by
	line.fetchResource
""") List<Object[]> findLastByReqIn(Iterable<HistoryReq> reqIds);

	/**
	 * @param minInstant minimimum date, included
	 * @param maxInstant maximum date, included
	 * @return aggregated totalValue, totalQuantity, typeId, typeName for each type
	 *           with at
	 *           least an history line, over the included range of dates
	 */
	@Query("""
select
	line.fetchResource.type.id typeId,
	line.fetchResource.type.name typeName,
	sum(average*volume) totalvalue,
	sum(volume) totalQuantity
from
	EsiTradeHistoryLine line
where
	line.date <= :maxInstant
	and line.date >= :minInstant
group by
	line.fetchResource.type.id,
	line.fetchResource.type.name
order by
 	sum(average*volume) desc
limit :limit
""")
	List<AggregatedTypeHistory> sortSalesByTotalValue(Instant minInstant, Instant maxInstant, int limit);

}
