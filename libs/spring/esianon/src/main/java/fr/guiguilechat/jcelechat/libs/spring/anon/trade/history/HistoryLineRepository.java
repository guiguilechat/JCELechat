package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.IFetchedListElementRepositoryAutoId;

public interface HistoryLineRepository extends IFetchedListElementRepositoryAutoId<HistoryReq, HistoryLine> {

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
