package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderUpdateRepository extends JpaRepository<OrderUpdate, Long> {

	/**
	 * deduce the updated orders by comparing the temp table and the existing orders
	 */
	@Modifying
	@Query("""
insert into #{#entityName} (
	orderId,
	newPrice,
	date
)
from
	EsiTradeMarketLineTemp young
	join EsiTradeMarketLine old on old.id=young.id
where
	old.price <> young.price
select
	young.id,
	young.price,
	young.issued
""")
	int addFromTempTable();

	@Query("""
from
	EsiTradeMarketLineTemp young
	join EsiTradeMarketLine old on old.id=young.id
where
	old.price <> young.price
select
	young.id orderId,
	young.price price,
	young.issued date
""")
	List<Map<String, Object>> debugFromTempTable();

}
