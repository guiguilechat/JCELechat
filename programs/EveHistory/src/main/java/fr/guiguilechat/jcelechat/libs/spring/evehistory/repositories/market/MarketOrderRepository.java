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
insert into market_order(id, order_id, region_id, first_line_id)
select
	nextval('seq_market_order_id'),
	line.order_id,
	res.region_id,
	line.id
from
	market_fetch_result res
	join market_fetch_line line on res.id=line.fetch_result_id
where
	res.id=:fetchResultId
	and not line.invalid
	and not exists (select id from market_order where order_id=line.order_id and region_id=res.region_id)
""")
	@Modifying
	@Transactional
	void createMissingOrders(Number fetchResultId);

	/**
	 * set the
	 *
	 * @param fetchResultId
	 */
	@Query(nativeQuery = true, value = """
update
	market_order ord
set
	last_line_id=line.id
from
	market_fetch_result res
	join market_fetch_line line on res.id=line.fetch_result_id
where
	res.id=:fetchResultId
	and not line.invalid
	and ord.order_id=line.order_id
	and ord.region_id=res.region_id
""")
	@Modifying
	void updateLastLine(Number fetchResultId);

}