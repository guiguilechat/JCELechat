package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderSaleRepository extends JpaRepository<OrderSale, Long> {

	/**
	 * deduce the sales by comparing the temp table and the existing orders
	 */
	@Modifying
	@Query("""
insert into #{#entityName} (
	orderId,
	typeId,
	dateMax,
	dateMin,
	volume,
	priceEnd
)
from
	EsiTradeMarketLineTemp young
	join EsiTradeMarketLine old on old.id=young.id
where
	old.volumeRemain <> young.volumeRemain
select
	young.id,
	young.typeId,
	young.lastModified,
	old.lastModified,
	old.volumeRemain-young.volumeRemain,
	young.price
""")
	int addFromTempTable();

}
