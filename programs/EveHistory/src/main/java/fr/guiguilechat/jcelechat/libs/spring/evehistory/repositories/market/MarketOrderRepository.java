package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketOrder;

public interface MarketOrderRepository extends JpaRepository<MarketOrder, Long> {


	/**
	 * create the orders not existing yet for lines added from a fetch
	 *
	 * @param fetchResultId id of the fetch result
	 */
	@Query(nativeQuery = true, value = """
insert into market_order(order_id, first_line_id, last_line_id)
select
	line.order_id,
	line.id,
	line.id
from
	market_fetch_line line
where
	line.fetch_result_id=:fetchResultId
	and not line.invalid
	and not exists (select order_id from market_order where order_id=line.order_id)
""")
	@Modifying
	@Transactional
	void createMissingOrders(Number fetchResultId);
}
