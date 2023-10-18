package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchResult;

public interface MarketFetchResultRepository extends JpaRepository<MarketFetchResult, Long> {

	/**
	 * @return the list of last fetch result for each region already fetched
	 */
	@Query("""
select mfr
from MarketFetchResult mfr
where mfr.id = (select max(id) from MarketFetchResult mfr2 where mfr.regionId= mfr2.regionId and mfr2.failed or mfr2.responseCode=200)
order by mfr.id
""")
	List<MarketFetchResult> findLastResults();

	boolean existsByRegionId(int region_id);

	@Query("select distinct(mfr.regionId) from MarketFetchResult mfr")
	List<Integer> listRegionIds();

	/**
	 * list the fetch results that can be analyzed : are successful, not analyzed,
	 * and there exists
	 * a later successful fetch for the same region
	 */
	@Query("""
select mfr
from MarketFetchResult mfr
where mfr.analyzed = false and mfr.responseCode=200
and exists (select mfr2.id from MarketFetchResult mfr2 where mfr2.responseCode=200 and mfr2.id>mfr.id and mfr2.regionId=mfr.regionId)
""")
	List<MarketFetchResult> findAnalyzable();
}
