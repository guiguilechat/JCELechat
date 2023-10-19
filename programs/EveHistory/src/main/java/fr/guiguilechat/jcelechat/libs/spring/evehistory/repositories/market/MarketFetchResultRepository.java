package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;

public interface MarketFetchResultRepository extends JpaRepository<MarketFetchResult, Long> {

	/**
	 * @return the list of last fetch result for each region already fetched
	 */
	@Query("""
select mfr
from MarketFetchResult mfr
where mfr.id = (select max(id) from MarketFetchResult mfr2 where mfr.regionId= mfr2.regionId and not mfr2.cached)
order by mfr.id asc
""")
	List<MarketFetchResult> findLastResults();

	/**
	 * list the fetch results that can be analyzed : are successful, not analyzed,
	 * and there exists
	 * a later successful fetch for the same region
	 */
	@Query("""
select mfr
from MarketFetchResult mfr
where mfr.analyzed = false and mfr.linesFetched>0
and exists (select mfr2.id from MarketFetchResult mfr2 where mfr2.linesFetched>0 and mfr2.id>mfr.id and mfr2.regionId=mfr.regionId)
order by id asc
""")
	List<MarketFetchResult> findAnalyzable();

	List<MarketFetchResult> findByCreatedDateLessThanAndFailedTrue(Instant maxDate);

	List<MarketFetchResult> findByCreatedDateLessThanAndCachedTrue(Instant maxDate);

	List<MarketFetchResult> findByCreatedDateLessThanAndEtagNotNull(Instant maxDate);
}
