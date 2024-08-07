package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface MarketLineRepository extends IFetchedListElementRepository<MarketRegion, MarketLine> {

	@Override
	@Modifying
	@Query("delete from EsiTradeMarketLine where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

	public List<MarketLine> findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(
	long locationId,
	int typeid,
			boolean isBuyOrder);

	public Stream<MarketLine> findByLocationIdAndTypeIdInAndIsBuyOrderTrueOrderByPriceDesc(
	long locationId,
	    Set<Integer>typeids);

	public Stream<MarketLine> findByLocationIdAndTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(
	long locationId,
	    Set<Integer> typeids);

	public List<MarketLine> findByFetchResourceIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(
	long regionId,
	int typeid,
			boolean isBuyOrder);

	public List<MarketLine> findByTypeIdAndIsBuyOrderOrderByPriceAsc(
	int typeid,
	boolean isBuyOrder);

	public Stream<MarketLine> findByTypeIdAndIsBuyOrderFalseOrderByPriceAsc(
	int typeid);

	public Stream<MarketLine> findByTypeIdAndIsBuyOrderTrueOrderByPriceDesc(
	int typeid);

	public List<MarketLine> findByFetchResourceIdAndTypeIdInOrderByPriceAsc(
	int regionId, List<Integer>typeids);

	public List<MarketLine> findByLocationIdAndTypeIdInOrderByPriceAsc(
	long locationId, List<Integer>typeids);

	public List<MarketLine> findByTypeIdInOrderByPriceAsc(
	List<Integer> typeids);

	/**
	 * @return lines grouped in format (regionId, location_id, min price)
	 */
	@Query("""
select
	line.fetchResource.id,
	line.locationId,
	min(line.price)
from
	EsiTradeMarketLine line
where
	line.type.id=:typeid
	and not line.isBuyOrder
group by
	line.fetchResource.id,
	line.locationId
order by
	min(line.price) asc
""")
	public List<Object[]> findSellOfferLocations(int typeid);

	/**
	 * @return lines grouped in format (regionId, location_id, min price)
	 */
	@Query("""
select
	line.fetchResource.id,
	line.locationId,
	min(line.price)
from
	EsiTradeMarketLine line
where
	line.type.id=:typeid
	and not line.isBuyOrder
	and line.duration=365
group by
	line.fetchResource.id,
	line.locationId
order by
	min(line.price) asc
""")
	public List<Object[]> findSeedOffers(int typeid);

	/**
	 * @return lines grouped in format (regionId, location_id, max price)
	 */
	@Query("""
select
	line.fetchResource.id,
	line.locationId,
	max(line.price)
from
	EsiTradeMarketLine line
where
	line.type.id=:typeid
	and line.isBuyOrder
group by
	line.fetchResource.id,
	line.locationId
order by
	max(line.price) asc
""")
	public List<Object[]> findBuyOfferLocations(int typeid);

	@Query(""" 
select
	fetchResource.id,
	type.id
from
	EsiTradeMarketLine
group by
	fetchResource.id,
	type.id
""")
	public List<Object[]> findAllRegionTypeCouple();

}
