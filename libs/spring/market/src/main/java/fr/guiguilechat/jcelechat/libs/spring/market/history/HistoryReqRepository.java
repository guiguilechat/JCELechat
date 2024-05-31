package fr.guiguilechat.jcelechat.libs.spring.market.history;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryReqRepository extends JpaRepository<HistoryReq, Long> {

	@Query("""
select
	distinct(line.typeId)
from
	EsiMarketRegionLine line
where
	line.region.regionId=:regionId
	and line.typeId not in (select typeId from EsiMarketHistoryReq where regionId=:regionId)
""")
	public List<Integer> findMissingTypesForRegion(int regionId);

	public List<HistoryReq> findTop100ByNextFetchLessThanOrderByNextFetchAscRegionIdAscTypeIdAsc(Instant maxNextFetch);

	public List<HistoryReq> findTop310ByNextFetchLessThanOrderByNextFetchAscRegionIdAscTypeIdAsc(Instant maxNextFetch);

	public List<HistoryReq> findTop1000ByNextFetchLessThanOrderByNextFetchAscRegionIdAscTypeIdAsc(Instant maxNextFetch);

	public long countByNextFetchLessThan(Instant maxNextFetch);

}
