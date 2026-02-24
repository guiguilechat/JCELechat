package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderCreationRepository extends JpaRepository<OrderCreation, Long> {

	/**
	 * deduce the new orders by comparing the temp table and the existing creations.
	 * <p>
	 * Previous last-modified is used for datemin of new creation, and not the
	 * order's issued, because orders can be fetched way after their creation
	 * </p>
	 *
	 * @param regionId             id of the region to process.
	 * @param previousLastModified time at which that region's orders were cached
	 *                             the previous time. It is used to set the dateMin
	 *                             or new order creations
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

	/**
	 * debuging
	 */
	@Query("""
from
	EsiTradeMarketLineTemp order
	left join JcelechatTradeOrderCreation creation on creation.orderId=order.id
where
	order.regionId=:regionId
	and creation.orderId is null
select
	order.id orderId,
	order.typeId typeId,
	order.isBuyOrder isBuyOrder,
	order.price price,
	order.volumeTotal volume,
	order.duration duration,
	order.locationId locationId,
	order.solarSystemId solarSystemId,
	order.regionId regionId,
	order.issued dateMax,
	:previousLastModified dateMin
""")
	List<Map<String, Object>> debugFromTempTable(int regionId, Instant previousLastModified);

}
