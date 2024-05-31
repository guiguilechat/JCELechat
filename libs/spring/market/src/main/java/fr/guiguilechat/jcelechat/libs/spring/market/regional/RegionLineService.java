package fr.guiguilechat.jcelechat.libs.spring.market.regional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.order.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.market.regional.RegionMarketUpdateService.MarketUpdateListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionLineService implements MarketUpdateListener {

	final private RegionLineRepository repo;

	public RegionLine save(RegionLine entity) {
		return repo.save(entity);
	}

	public List<RegionLine> saveAll(Iterable<RegionLine> entities) {
		return repo.saveAll(entities);
	}

	public void clearRegion(ObservedRegion region) {
		repo.deleteByRegion(region);
	}

	//
	// tools
	//

	/** common value to get to get Jita specific orders */
	public static final long JITAIV_ID = 60003760;

	//
	// retrieve actual orders
	//

	/**
	 * @return all the buy orders of given types, grouped by type id, by price
	 *           descending
	 */
// @Cacheable("marketLocationTypesBo")
	public Map<Integer, List<RegionLine>> locationBos(long locationId, Set<Integer> typeIds) {
		long start = System.currentTimeMillis();
		Map<Integer, List<RegionLine>> ret = repo
				.findByLocationIdAndTypeIdInAndIsBuyOrderTrueOrderByPriceDesc(locationId, typeIds)
				.collect(Collectors.groupingBy(RegionLine::getTypeId));
		long fetched = System.currentTimeMillis();
		log.debug(
				"listed BO lines for " + typeIds.size() + " ids , fetch=" + (fetched - start) + "ms, ids=" + typeIds);
		return ret;
	}

	/**
	 * @return all the sell orders of given types, grouped by type id, by price
	 *           ascending
	 */
// @Cacheable("marketLocationTypesSo")
	public Map<Integer, List<RegionLine>> locationSos(long locationId, Set<Integer> typeIds) {
		long start = System.currentTimeMillis();
		Map<Integer, List<RegionLine>> ret = repo
				.findByLocationIdAndTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(locationId, typeIds)
				.collect(Collectors.groupingBy(RegionLine::getTypeId));
		long fetched = System.currentTimeMillis();
		log.debug(
				"listed SO lines for " + typeIds.size() + " ids, fetch=" + (fetched - start) + "ms, ids=" + typeIds);
		return ret;
	}

	static List<RegionLine> reverseIf(List<RegionLine> lines, boolean reverse) {
		if (!reverse) {
			return lines;
		}
		List<RegionLine> ret = new ArrayList<>(lines);
		Collections.reverse(ret);
		return ret;
	}

	/**
	 * @return existing lines with given order.locationId , order.type_id , and
	 *           order.isbuyorder , ordered by price asc for SO and price desc for
	 *           BO
	 */
	@Cacheable("marketLocation")
	public List<RegionLine> forLocation(long locationId, int type_id, boolean isBuyOrder) {
		return reverseIf(repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, type_id, isBuyOrder),
				isBuyOrder);
	}

	/**
	 * @return existing lines with given region.regionId , order.type_id , and
	 *           order.isbuyorder , ordered by price asc for SO and price desc for
	 *           BO
	 */
	@Cacheable("marketRegion")
	public List<RegionLine> forRegion(int regionId, int type_id, boolean isBuyOrder) {
		return reverseIf(repo.findByRegionRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, type_id, isBuyOrder),
				isBuyOrder);
	}

	/**
	 * @return existing lines with given order.type_id and order.isbuyorder ,
	 *           ordered by price asc for SO and price desc for BO
	 */
	@Cacheable("marketAll")
	public List<RegionLine> forAll(int type_id, boolean isBuyOrder) {
		return reverseIf(repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(type_id, isBuyOrder), isBuyOrder);
	}

	public Stream<MarketOrder> streamBOs(int typeId) {
		return repo.findByTypeIdAndIsBuyOrderTrueOrderByPriceDesc(typeId).map(MarketOrder::of);
	}

	public Stream<MarketOrder> streamSOs(int typeId) {
		return repo.findByTypeIdAndIsBuyOrderFalseOrderByPriceAsc(typeId).map(MarketOrder::of);
	}

	//
	// prices
	//

	/**
	 * @param lines        actual lines
	 * @param quantity     number of volume to value
	 * @param cumulated    if true add the prices of the order to cumulated
	 *                     quantity.
	 *                     if false use the price of the order at cumulated quantity
	 * @param infOnMissing if true return +inf when not enough volume. else use 0;
	 * @return cumulated or exact price for given quantity
	 */
	double price(List<RegionLine> lines, long quantity, boolean cumulated, boolean infOnMissing) {
		long remain = quantity;
		double cumul = 0.0;
		for (RegionLine line : lines) {
			long taken = Math.min(remain, line.getVolumeRemain());
			remain -= taken;
			cumul += line.getPrice() * taken;
			if (remain <= 0) {
				return cumulated ? cumul : line.getPrice() * quantity;
			}
		}
		if (infOnMissing) {
			return Double.POSITIVE_INFINITY;
		}
		return cumulated ? cumul : 0.0;
	}

	/**
	 * @return basically the value you get from selling your stuff here
	 */
	@Cacheable("marketBoValueLocation")
	public Double boValueLocation(long locationId, int typeId, long quantity, boolean dump) {
		return price(forLocation(locationId, typeId, true), quantity, !dump, false);
	}

	/**
	 * @return the value to purchase from direct orders here.
	 */
	@Cacheable("marketSoValueLocation")
	public Double soValueLocation(long locationId, int typeId, long quantity, boolean dump) {
		return price(forLocation(locationId, typeId, false), quantity, !dump, true);
	}

	//
	// places to buy/sell
	//

	/**
	 * the best offer for a given type at a location. Also integrates region id for
	 * easiness
	 */
	public static record LocatedBestOffer(int regionId, long locationId, int typeId, double bestPrice)
			implements Serializable {
	}

	/**
	 * @param typeId id of the type we want the price of
	 * @return the lowest sell price, at each location, for given type
	 */
	public List<LocatedBestOffer> sellLocations(int typeId) {
		return repo.findSellOfferLocations(typeId).stream()
				.map(arr -> new LocatedBestOffer((int) arr[0], (long) arr[1], typeId, (double) arr[2])).toList();
	}

	/**
	 * @param typeId id of the type we want the price of
	 * @return the highest buy price, at each location, for given type
	 */
	public List<LocatedBestOffer> buyLocations(int typeId) {
		return repo.findBuyOfferLocations(typeId).stream()
				.map(arr -> new LocatedBestOffer((int) arr[0], (long) arr[1], typeId, (double) arr[2])).toList();
	}

	/**
	 * @param typeId id of the type we want the seed locations of
	 * @return the list of best price for each location where the type is seeded.
	 */
	public List<LocatedBestOffer> seedLocations(int typeId) {
		return repo.findSeedOffers(typeId).stream()
				.map(arr -> new LocatedBestOffer((int) arr[0], (long) arr[1], typeId, (double) arr[2])).toList();
	}

	//
	// stats
	//

	/**
	 * cumulated value
	 */
	public static record OfferStat(long qtty, double price, long cumulQtty, double cumulValue) implements Serializable {
	}

	@Value("${market.sellstats.clipmult:10}")
	private float priceGainClipMult;

	List<OfferStat> sellGain(List<RegionLine> sos, List<RegionLine> bos) {
		ArrayList<OfferStat> ret = new ArrayList<>();
		long cumulQtty = 0;
		double cumulValue = 0.0;
		Double lowestSOPrice = null;
		for (RegionLine rl : sos) {
			if (lowestSOPrice == null) {
				lowestSOPrice = rl.getPrice();
			} else if (rl.getPrice() > lowestSOPrice * priceGainClipMult) {
				break;
			}
			cumulQtty += rl.getVolumeRemain();
			cumulValue += rl.getVolumeRemain() * rl.getPrice();
			OfferStat add = new OfferStat(-rl.getVolumeRemain(), -rl.getPrice(), -cumulQtty, cumulValue);
			ret.add(add);
		}
		cumulQtty = 0;
		cumulValue = 0.0;
		Double highestBOPrice = null;
		for (RegionLine rl : bos) {
			if (highestBOPrice == null) {
				highestBOPrice = rl.getPrice();
			} else if (rl.getPrice() < highestBOPrice / priceGainClipMult) {
				break;
			}
			cumulQtty += rl.getVolumeRemain();
			cumulValue += rl.getVolumeRemain() * rl.getPrice();
			OfferStat add = new OfferStat(rl.getVolumeRemain(), rl.getPrice(), cumulQtty, cumulValue);
			ret.add(add);
		}
		return ret;
	}

	public List<OfferStat> offerStatsLocation(long locationId, int typeId) {
		List<RegionLine> sos = repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, typeId, false);
		List<RegionLine> bos = new ArrayList<>(
				repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}

	public List<OfferStat> offerStatsRegion(int regionId, int typeId) {
		List<RegionLine> sos = repo.findByRegionRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, typeId, false);
		List<RegionLine> bos = new ArrayList<>(
				repo.findByRegionRegionIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}

	public List<OfferStat> offerStatsAll(int typeId) {
		List<RegionLine> sos = repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(typeId, false);
		List<RegionLine> bos = new ArrayList<>(repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}


	/** list of caches to invalidate */
	static final List<String> MARKET_ORDERS_CACHES = List.of(
			"marketAll",
			"marketLocation",
			"marketRegion",
			"marketBoValueLocation",
			"marketSoValueLocation",
			"marketLocationTypesBo",
			"marketLocationTypesSo");

	@Override
	public List<String> listMarketCaches(int regionId) {
		return MARKET_ORDERS_CACHES;
	}

}
