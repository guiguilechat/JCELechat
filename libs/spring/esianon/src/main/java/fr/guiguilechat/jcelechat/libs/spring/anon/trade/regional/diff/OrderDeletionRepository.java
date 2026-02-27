package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderDeletionRepository extends JpaRepository<OrderDeletion, Long> {
	/**
	 * deduce the removed orders by comparing the temp table and the existing orders
	 * <p>
	 * the previouslastmodified is required because it's not stored in the orders if
	 * we retrieve a 304.
	 * </p>
	 */
	@Modifying
	@Query("""
insert into #{#entityName} (
	orderId,
	typeId,
	isBuyOrder,
	price,
	volume,
	locationId,
	solarSystemId,
	regionId,
	dateMax,
	dateMin,
	canExpire
)
from
	EsiTradeMarketLine deleted
where
	deleted.regionId=:regionId
	and not exists (select 1 from EsiTradeMarketLineTemp newer where newer.id=deleted.id)
select
	deleted.id,
	deleted.typeId,
	deleted.isBuyOrder,
	deleted.price,
	deleted.volumeRemain,
	deleted.locationId,
	deleted.solarSystemId,
	deleted.regionId,
	:lastModified,
	:previousLastModified,
	dateadd(day, deleted.duration, deleted.issued) between :previousLastModified and :lastModified
""")
	int addFromTempTable(int regionId, Instant previousLastModified, Instant lastModified);

}
