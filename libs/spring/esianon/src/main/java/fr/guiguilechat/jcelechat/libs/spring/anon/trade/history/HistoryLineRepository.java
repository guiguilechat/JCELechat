package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade.AggregatedTypeHistory;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.IFetchedListElementRepositoryAutoId;

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
	date_trunc('day', date),
	sum(volume),
	sum(volume*average),
	max(highest),
	min(lowest),
	count(*)
from
	EsiTradeHistoryLine line
where
	line.fetchResource.type.id = :typeId
	and (cast(:from as timestamp) is null or line.date>=:from)
group by
	date_trunc('day', date)
""")
	List<AggregatedHL> aggregated(int typeId, Instant from);

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
	sum(volume) totalQuantity,
	false,
	0,
	0
from
	EsiTradeHistoryLine line
where
	(cast(:maxInstant as timestamp) is null or line.date <= :maxInstant)
	and (cast(:minInstant as timestamp)is null or line.date >= :minInstant)
group by
	line.fetchResource.type.id,
	line.fetchResource.type.name
order by
 	sum(average*volume) desc
limit :limit
""")
	List<AggregatedTypeHistory> sortSalesByTotalValue(Instant minInstant, Instant maxInstant, int limit);

	@Query("""
from
	(
	from #{#entityName} l
	where
		l.fetchResource.type.id=:typeId
	select
		1 as n
	)
select
	count(*)>0
""")

	boolean existsByFetchResourceTypeId(int typeId);

	// for everef updates

	@Query("""
from
	#{#entityName} l
where
	l.extsource=:extsource
select
	max(date_trunc('day', date))
""")
	CompletableFuture<Instant> maxDateSaved(String extsource);

	@Query("""
from
	#{#entityName} l
where
	l.extsource is null
select
	min(date_trunc('day', date))
""")
	CompletableFuture<Instant> minEsiDateSaved();

}
