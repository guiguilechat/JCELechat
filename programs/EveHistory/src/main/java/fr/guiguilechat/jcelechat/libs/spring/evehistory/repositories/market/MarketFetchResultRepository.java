package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult.STATUS;

public interface MarketFetchResultRepository extends JpaRepository<MarketFetchResult, Long> {

// /**
// * list the fetch results that can be analyzed : are successful, not analyzed,
// * and there exists a later successful fetch for the same region
// */
// @Query("""
// select mfr, mfr2
// from MarketFetchResult mfr
// join MarketFetchResult mfr2 on mfr2.id=
// (select min(mfr3.id)
// from MarketFetchResult mfr3
// where mfr3.regionId=mfr.regionId and mfr3.id>mfr.id and mfr3.linesFetched>0)
// where mfr.analyzed = false and mfr.linesFetched>0
// order by mfr.id asc
// """)
// List<Object[]> findToCreateOrders();

	@Query("""
	select mfr
	from MarketFetchResult mfr
		left join mfr.previousResult pr
	where
		mfr.status=:resultStatus
		and (pr is null or pr.status in :previousStatus)
	order by mfr.id
""")
	List<MarketFetchResult> listWithStatusAndPreviousNullOrStatusIn(STATUS resultStatus,
			Collection<STATUS> previousStatus);

	List<MarketFetchResult> findByStatus(STATUS status);

}
