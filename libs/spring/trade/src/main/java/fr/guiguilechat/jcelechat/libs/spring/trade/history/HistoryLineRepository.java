package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface HistoryLineRepository extends IFetchedListElementRepository<HistoryReq, HistoryLine> {

	@Override
	@Modifying
	@Query("delete from EsiTradeHistoryLine where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

	@Query("""
select
	line
from
	EsiTradeHistoryLine line
where
	line.fetchResource.region.id = :regionId
	and line.fetchResource.type.id = :typeId
""")
	public List<HistoryLine> findByFetchResourceRegionIdAndFetchResourceTypeId(int regionId, int typeId);

	@Query("""
select
	line
from
	EsiTradeHistoryLine line
where
	line.fetchResource.type.id = :typeId
""")
	public List<HistoryLine> findByFetchResourceTypeId(int typeId);

	@Query("""
select
	date date,
	sum(volume) volume,
	sum(volume*average) totalValue,
	max(highest) highestPrice,
	min(lowest) lowestPrice,
	sum(orderCount) orderCount,
	count(*) regions
from
	EsiTradeHistoryLine line
where
	line.fetchResource.type.id = :typeId
	and line.date>=:from
group by date
""")
	public List<Object[]> aggregated(int typeId, Instant from);

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
""")
	public List<Object[]> findLastByReqIn(Iterable<HistoryReq> reqIds);

}
