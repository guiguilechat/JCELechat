package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.IFetchedListElementRepositoryAutoId;

public interface MarketLineRepository extends IFetchedListElementRepositoryAutoId<MarketRegion, MarketLine> {

	// calculate prices

	/**
	 * get the (typeid, min price) for SOs at a given location and for given types
	 */
	@Query("""
select
	typeId,
	min(price)
from
	#{#entityName} e
where
	not isBuyOrder
	and locationId in :locationIds
	and typeId in :typeIds
group by typeId
""")
	Stream<Object[]> lowestSOAt(Iterable<Long> locationIds, Iterable<Integer> typeIds);

	@Query("""
select
	min(price)
from
	#{#entityName} e
where
	not isBuyOrder
	and typeId = :typeId
""")
	Double lowestSO(int typeId);

	/**
	 * get the (typeid, min price) for SOs at a given location and for given types,
	 * past a given total value
	 */
	@Query(value = """
select
	"type_id",
	coalesce ( min("price") filter(where vol_before>=:discardValue)
		, max("price") )
from (select
	*,
	sum("volume_remain"*"price") over(partition by "type_id" order by "price" asc) vol_before
from
	"esi_trade_market_line"
where
	"location_id" in :locationIds
	and "type_id" in :typeIds
	and not "is_buy_order"
) foo
group by "type_id"
""", nativeQuery = true)
	Stream<Object[]> lowestSOAt(Iterable<Long> locationIds, double discardValue, Iterable<Integer> typeIds);

	List<MarketLine> findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(
			long locationId,
			int typeid,
			boolean isBuyOrder);

	Stream<MarketLine> findByLocationIdAndTypeIdInAndIsBuyOrderTrueOrderByPriceDesc(
			long locationId,
			Set<Integer> typeids);

	Stream<MarketLine> findByLocationIdAndTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(
			long locationId,
			Set<Integer> typeids);

	List<MarketLine> findByFetchResourceIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(
			long regionId,
			int typeid,
			boolean isBuyOrder);

	List<MarketLine> findByTypeIdAndIsBuyOrderOrderByPriceAsc(
			int typeid,
			boolean isBuyOrder);

	Stream<MarketLine> findByTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(
			Iterable<Integer> typeids);

	Stream<MarketLine> findByTypeIdInAndLocationIdAndIsBuyOrderFalseOrderByPriceAsc(
			Iterable<Integer> typeids, long locationId);

	Stream<MarketLine> findByTypeIdAndIsBuyOrderTrueOrderByPriceDesc(int typeId);

	Stream<MarketLine> findByTypeIdInAndLocationIdAndIsBuyOrderTrueOrderByPriceDesc(
			Iterable<Integer> typeids, long locationId);

	List<MarketLine> findByFetchResourceIdAndTypeIdInOrderByPriceAsc(
			int regionId, List<Integer> typeids);

	List<MarketLine> findByLocationIdAndTypeIdInOrderByPriceAsc(
			long locationId, List<Integer> typeids);

	List<MarketLine> findByTypeIdInOrderByPriceAsc(
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
	line.typeId=:typeid
	and not line.isBuyOrder
group by
	line.fetchResource.id,
	line.locationId
order by
	min(line.price) asc
""")
	List<Object[]> findSellOfferLocations(int typeid);

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
	line.typeId=:typeid
	and not line.isBuyOrder
	and line.duration=365
group by
	line.fetchResource.id,
	line.locationId
order by
	min(line.price) asc
""")
	List<Object[]> findSeedOffers(int typeid);

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
	line.typeId=:typeid
	and line.isBuyOrder
group by
	line.fetchResource.id,
	line.locationId
order by
	max(line.price) asc
""")
	List<Object[]> findBuyOfferLocations(int typeid);

	@Query(value = """
SELECT NEXTVAL('esi_trade_market_line_seq') FROM generate_series(1,:nb)
""", nativeQuery = true)
	List<Long> reservePGIds(int nb);

}
