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

	List<MarketFetchResult> findByStatus(STATUS status);

	/**
	 * @return a new list of the fetch results with given status, and either no
	 *           previous or a previous whose status in the given set.
	 */
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

	/**
	 * @return the list of fetchresult, nextresult for which the former is in
	 *           requested resultStatus and the later among the requested nextStatus
	 */
	@Query("""
	select mfr, nr
	from MarketFetchResult mfr
		left join mfr.previousResult pr
		join mfr.nextResult nr
	where
		mfr.status=:resultStatus
		and (pr is null or pr.status in :previousStatus)
		and nr.status in :nextStatus
	order by mfr.id
""")
	List<Object[]> listWithStatusAndPreviousNullOrStatusInAndNextStatusIn(STATUS resultStatus,
			Collection<STATUS> previousStatus,
			Collection<STATUS> nextStatus);

	@Query("""
	select line, ordr
	from MarketFetchLine line
		left join MarketOrder ordr on line.order.order_id=ordr.orderId
	where
		line.fetchResult = :result
""")
	List<Object[]> listLinesOrders(MarketFetchResult result);

}
