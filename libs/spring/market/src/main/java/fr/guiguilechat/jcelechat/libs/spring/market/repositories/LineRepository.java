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

}
