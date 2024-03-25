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

	public List<RegionLine> findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(long locationId, int typeId,
			boolean isBuyOrder);

	public Stream<RegionLine> findByLocationIdAndTypeIdInAndIsBuyOrderTrueOrderByPriceDesc(long locationId,
			Set<Integer> typeIds);

	public Stream<RegionLine> findByLocationIdAndTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(long locationId,
			Set<Integer> typeIds);

	public List<RegionLine> findByRegionRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(long regionId, int typeId,
			boolean isBuyOrder);

	public List<RegionLine> findByTypeIdAndIsBuyOrderOrderByPriceAsc(int typeId, boolean isBuyOrder);

	public List<RegionLine> findByRegionRegionIdAndTypeIdInOrderByPriceAsc(int regionId, List<Integer> typeIds);

	public List<RegionLine> findByLocationIdAndTypeIdInOrderByPriceAsc(long locationId, List<Integer> typeIds);

	public List<RegionLine> findByTypeIdInOrderByPriceAsc(List<Integer> typeIds);

	/**
	 * @return lines grouped in format (regionId, location_id, min price)
	 */
	@Query("""
select
	line.region.regionId,
	line.locationId,
	min(line.price)
from
	EsiMarketRegionLine line
where
	line.typeId=:typeId
	and not line.isBuyOrder
group by
	line.region.regionId,
	line.locationId
order by
	min(line.price) asc
""")
	public List<Object[]> findSellOfferLocations(@Param("typeId") int typeId);

	/**
	 * @return lines grouped in format (regionId, location_id, min price)
	 */
	@Query("""
select
	line.region.regionId,
	line.locationId,
	min(line.price)
from
	EsiMarketRegionLine line
where
	line.typeId=:typeId
	and not line.isBuyOrder
	and line.duration=365
group by
	line.region.regionId,
	line.locationId
order by
	min(line.price) asc
""")
	public List<Object[]> findSeedOffers(@Param("typeId") int typeId);

	/**
	 * @return lines grouped in format (regionId, location_id, max price)
	 */
	@Query("""
select
	line.region.regionId,
	line.locationId,
	max(line.price)
from
	EsiMarketRegionLine line
where
	line.typeId=:typeId
	and line.isBuyOrder
group by
	line.region.regionId,
	line.locationId
order by
	max(line.price) asc
""")
	public List<Object[]> findBuyOfferLocations(@Param("typeId") int typeId);

}
