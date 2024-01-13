package fr.guiguilechat.jcelechat.libs.spring.market.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;

public interface HistoryReqRepository extends JpaRepository<HistoryReq, Long> {

	@Query("""
select
	distinct(line.order.type_id)
from
	EsiMarketLine line
where
	line.region.regionId=:regionId
	and line.order.type_id not in (select typeId from EsiMarketHistoryReq where regionId=:regionId)
""")
	public List<Integer> findMissingTypesForRegion(int regionId);

	public List<HistoryReq> findTop200ByNextFetchLessThanOrderByNextFetch(Instant maxNextFetch);

	public long countByNextFetchLessThan(Instant maxNextFetch);

}
