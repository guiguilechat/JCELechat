package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchResult;

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

}
