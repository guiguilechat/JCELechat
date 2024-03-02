package fr.guiguilechat.jcelechat.libs.spring.market.repositories;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;

public interface RegionLineRepository extends JpaRepository<RegionLine, Long> {

	public int deleteByRegion(ObservedRegion region);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketRegionLine line
where
	line.order.location_id=:locationId
	and line.order.type_id=:typeId
	and line.order.is_buy_order=:isBuyOrder
order by
	line.order.price
""")
	public List<RegionLine> findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(
			@Param("locationId") long locationId,
			@Param("typeId") int typeId,
			@Param("isBuyOrder") boolean isBuyOrder);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select
	line
from
	EsiMarketRegionLine line
where
	line.order.location_id=:locationId
	and line.order.type_id in :typeIds
	and line.order.is_buy_order
order by
	line.order.price desc
""")
	public Stream<RegionLine> findByLocationIdAndTypeIdInAndIsBuyOrderTrueOrderByPriceDesc(
			@Param("locationId") long locationId,
			@Param("typeIds") Set<Integer> typeIds);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select
	line
from
	EsiMarketRegionLine line
where
	line.order.location_id=:locationId
	and line.order.type_id in :typeIds
	and not line.order.is_buy_order
order by
	line.order.price
""")
	public Stream<RegionLine> findByLocationIdAndTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(
			@Param("locationId") long locationId,
			@Param("typeIds") Set<Integer> typeIds);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketRegionLine line
where
	line.region.regionId=:regionId
	and line.order.type_id=:typeId
	and line.order.is_buy_order=:isBuyOrder
order by
	line.order.price
""")
	public List<RegionLine> findByRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(
			@Param("regionId") long regionId,
			@Param("typeId") int typeId,
			@Param("isBuyOrder") boolean isBuyOrder);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketRegionLine line
where
	line.order.type_id=:typeId
	and line.order.is_buy_order=:isBuyOrder
order by
	line.order.price
""")
	public List<RegionLine> findByTypeIdAndIsBuyOrderOrderByPriceAsc(
			@Param("typeId") int typeId,
			@Param("isBuyOrder") boolean isBuyOrder);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketRegionLine line
where
	line.region.regionId=:regionId
	and line.order.type_id in :typeIds
order by
	line.order.price
""")
	public List<RegionLine> findByRegionIdAndTypeIdInOrderByPriceAsc(int regionId, List<Integer> typeIds);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketRegionLine line
where
	line.order.location_id=:locationId
	and line.order.type_id in :typeIds
order by
	line.order.price
""")
	public List<RegionLine> findByLocationIdAndTypeIdInOrderByPriceAsc(long locationId, List<Integer> typeIds);

	// need to query since fields with _ that can't be used in jpa
	@Query("""
select line
from
	EsiMarketRegionLine line
where
	line.order.type_id in :typeIds
order by
	line.order.price
""")
	public List<RegionLine> findByTypeIdInOrderByPriceAsc(List<Integer> typeIds);

	/**
	 * @return lines grouped in format (regionId, location_id, min price)
	 */
	@Query("""
select
	line.region.regionId,
	line.order.location_id,
	min(line.order.price)
from
	EsiMarketRegionLine line
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
	 * @return lines grouped in format (regionId, location_id, min price)
	 */
	@Query("""
select
	line.region.regionId,
	line.order.location_id,
	min(line.order.price)
from
	EsiMarketRegionLine line
where
	line.order.type_id=:typeId
	and not line.order.is_buy_order
	and line.order.duration=365
group by
	line.region.regionId,
	line.order.location_id
order by
	min(line.order.price) asc
""")
	public List<Object[]> findSeedOffers(@Param("typeId") int typeId);

	/**
	 * @return lines grouped in format (regionId, location_id, max price)
	 */
	@Query("""
select
	line.region.regionId,
	line.order.location_id,
	max(line.order.price)
from
	EsiMarketRegionLine line
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
