package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDeletionRepository extends JpaRepository<OrderDeletion, Long> {

	@Modifying
	@Query("""
delete from
	#{#entityName} already
where
	exists(select 1 from EsiTradeMarketLine line where already.orderId=line.id)
""")
	int deleteReAdded();

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
	and not exists (select 1 from  #{#entityName} already where already.orderId=deleted.id)
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
	@Transactional
	int addFromTempTable(int regionId, Instant previousLastModified, Instant lastModified);

	@Query("""
from
	EsiTradeMarketLine deleted
where
	deleted.regionId=:regionId
	and not exists (select 1 from EsiTradeMarketLineTemp newer where newer.id=deleted.id)
select
	deleted.id orderId,
	deleted.typeId typeId,
	deleted.isBuyOrder isBuyOrder,
	deleted.price price,
	deleted.volumeRemain volumeRemain,
	deleted.locationId locationId,
	deleted.solarSystemId solarSystemId,
	deleted.regionId regionId,
	:lastModified dateMax,
	:previousLastModified dateMin,
	dateadd(day, deleted.duration, deleted.issued) between :previousLastModified and :lastModified canExpire
""")
	List<Map<String, Object>> debugInsert(int regionId, Instant previousLastModified, Instant lastModified);

}
