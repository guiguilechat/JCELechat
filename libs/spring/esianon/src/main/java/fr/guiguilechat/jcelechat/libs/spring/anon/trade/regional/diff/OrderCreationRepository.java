package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderCreationRepository extends JpaRepository<OrderCreation, Long> {

	/**
	 * deduce the new orders by comparing the temp table and the existing orders
	 */
	@Modifying
	@Query("""
insert into #{#entityName} (
	orderId,
	typeId,
	isBuyOrder,
	price,
	volume,
	duration,
	locationId,
	solarSystemId,
	regionId,
	dateMax,
	dateMin
)
from
	EsiTradeMarketLineTemp order
	left join JcelechatTradeOrderCreation creation on creation.orderId=order.id
where
	order.regionId=:regionId
	and creation.orderId is null
select
	order.id,
	order.typeId,
	order.isBuyOrder,
	order.price,
	order.volumeTotal,
	order.duration,
	order.locationId,
	order.solarSystemId,
	order.regionId,
	order.issued,
	:previousLastModified
""")
	int addFromTempTable(int regionId, Instant previousLastModified);

}
