package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;
import java.util.List;
import java.util.Map;

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
	dateMax,
	dateMin
)
from
	EsiTradeMarketLine deleted
where
	deleted.regionId=:regionId
	and not exists (select 1 from EsiTradeMarketLineTemp newer where newer.id=deleted.id)
select
	deleted.id,
	:lastModified,
	:previousLastModified
""")
	int addFromTempTable(int regionId, Instant previousLastModified, Instant lastModified);

	/**
	 * debuging
	 */
	@Query("""
from
	EsiTradeMarketLine deleted
where
	deleted.regionId=:regionId
select
	deleted.id orderId,
	exists (select 1 from EsiTradeMarketLineTemp newer where newer.id=deleted.id) kept
""")
	List<Map<String, Object>> debugFromTempTable(int regionId, Instant previousLastModified, Instant lastModified);

}
