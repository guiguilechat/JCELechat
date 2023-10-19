package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;

public interface MarketFetchLineRepository extends JpaRepository<MarketFetchLine, Long> {

	@Query("""
select line0, line1, line2
from MarketFetchLine line1
left join MarketFetchLine line0 on line0.id=(select max(a.id) from MarketFetchLine a where a.order.order_id=line1.order.order_id and a.id<line1.id)
left join MarketFetchLine line2 on line2.id=(select min(b.id) from MarketFetchLine b where b.order.order_id=line1.order.order_id and b.id>line1.id)
where line1.fetchResult= :fetchresult
""")
	List<Object[]> listOrderChanges(@Param("fetchresult") MarketFetchResult fetchResult);

	int countByFetchResult(MarketFetchResult fetchResult);

	@Query(nativeQuery = true, value="""
select
distinct result.region_id region,
date_trunc('hour', line.issued_date) issued_date,
count(case when line.price_chg then 1 else null end) price_chg,
count(case when line.volume_chg then 1 else null end) vol_chg,
count(case when line.created then 1 else null end) created,
count(case when line.last then 1 else null end) last
from
market_fetch_result result
join market_fetch_line line on result.id=line.fetch_result_id
group by
result.region_id,
date_trunc('hour', line.issued_date)
order by
result.region_id,
date_trunc('hour', line.issued_date)
""")
	List<Object[]> analyzeChanges();

	@Query("""
select line
from MarketFetchLine line
where line.issuedDate>:fromDate
and line.issuedDate<:toDate
and line.order.type_id=:typeId
and line.fetchResult.regionId=:regionId
and line.priceChg=true
""")
	List<MarketFetchLine> listPriceChanges(int regionId, int typeId, Instant fromDate, Instant toDate);

}
