package fr.guiguilechat.jcelechat.libs.spring.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.guiguilechat.jcelechat.libs.spring.market.model.Line;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;

public interface LineRepository extends JpaRepository<Line, Long> {

	public int deleteByRegion(ObservedRegion region);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketLine line
where
	line.order.location_id=:locationId
	and line.order.type_id=:typeId
	and line.order.is_buy_order=:isBuyOrder
order by
	line.order.price
""")
	public List<Line> findByLocationIdAndTypeIdAndIsBuyOrder(
			@Param("locationId") long locationId,
			@Param("typeId") int typeId,
			@Param("isBuyOrder") boolean isBuyOrder);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketLine line
where
	line.region.regionId=:regionId
	and line.order.type_id=:typeId
	and line.order.is_buy_order=:isBuyOrder
order by
	line.order.price
""")
	public List<Line> findByRegionIdAndTypeIdAndIsBuyOrder(
			@Param("regionId") long regionId,
			@Param("typeId") int typeId,
			@Param("isBuyOrder") boolean isBuyOrder);

	/**
	 * @return lines grouped in format (regionId, location_id, min price)
	 */
	@Query("""
select
	line.region.regionId,
	line.order.location_id,
	min(line.order.price)
from
	EsiMarketLine line
where
	line.order.type_id=:typeId
	and not line.order.is_buy_order
group by
	line.region.regionId,
	line.order.location_id
order by
	min(line.order.price) asc
""")
	public List<Object[]> findSellOfferLocations(@Param("typeId") int typeId);

	/**
	 * @return lines grouped in format (regionId, location_id, max price)
	 */
	@Query("""
select
	line.region.regionId,
	line.order.location_id,
	max(line.order.price)
from
	EsiMarketLine line
where
	line.order.type_id=:typeId
	and line.order.is_buy_order
group by
	line.region.regionId,
	line.order.location_id
order by
	max(line.order.price) asc
""")
	public List<Object[]> findBuyOfferLocations(@Param("typeId") int typeId);

}
