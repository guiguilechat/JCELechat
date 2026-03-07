package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

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
	regionId,
	typeId,
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
	young.regionId,
	young.typeId,
	young.price,
	young.issued
""")
	int addFromTempTable();

}
