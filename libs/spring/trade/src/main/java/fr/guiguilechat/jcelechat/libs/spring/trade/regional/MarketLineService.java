package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketRegionService.MarketRegionListener;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.market.line")
public class MarketLineService implements MarketRegionListener {

	final private MarketLineRepository repo;

	public MarketLine save(MarketLine entity) {
		return repo.saveAndFlush(entity);
	}

	public List<MarketLine> saveAll(Iterable<MarketLine> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public void clearRegions(Iterable<MarketRegion> regions) {
		repo.removeForFetcher(regions);
	}

	// usage

	public List<Integer[]> listRegionIdTypeId() {
		return repo.findAllRegionTypeCouple().stream()
		    .map(arr -> new Integer[] { ((Number) arr[0]).intValue(), ((Number) arr[1]).intValue() }).toList();
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
	public Map<Integer, List<MarketLine>> locationBos(long locationId, Set<Integer> typeIds) {
		long start = System.currentTimeMillis();
		Map<Integer, List<MarketLine>> ret = repo
				.findByLocationIdAndTypeIdInAndIsBuyOrderTrueOrderByPriceDesc(locationId, typeIds)
		    .collect(Collectors.groupingBy(MarketLine::getTypeId));
		long fetched = System.currentTimeMillis();
		log.trace("listed {} locations of BO lines for {} type ids in {}s", ret.size(), typeIds.size(),
		    (fetched - start) / 1000);
		return ret;
	}

	/**
	 * @return all the sell orders of given types, grouped by type id, by price
	 *           ascending
	 */
// @Cacheable("marketLocationTypesSo")
	public Map<Integer, List<MarketLine>> locationSos(long locationId, Set<Integer> typeIds) {
		long start = System.currentTimeMillis();
		Map<Integer, List<MarketLine>> ret = repo
				.findByLocationIdAndTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(locationId, typeIds)
		    .collect(Collectors.groupingBy(MarketLine::getTypeId));
		long fetched = System.currentTimeMillis();
		log.trace("listed {} locations of SO lines for {} type ids in {}s", ret.size(), typeIds.size(),
		    (fetched - start) / 1000);
		return ret;
	}

	static List<MarketLine> reverseIf(List<MarketLine> lines, boolean reverse) {
		if (!reverse) {
			return lines;
		}
		List<MarketLine> ret = new ArrayList<>(lines);
		Collections.reverse(ret);
		return ret;
	}

	/**
	 * @return existing lines with given order.locationId , order.type_id , and
	 *           order.isbuyorder , ordered by price asc for SO and price desc for
	 *           BO
	 */
	@Cacheable("marketLocation")
	public List<MarketLine> forLocation(long locationId, int type_id, boolean isBuyOrder) {
		return reverseIf(repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, type_id, isBuyOrder),
				isBuyOrder);
	}

	/**
	 * @return existing lines with given region.regionId , order.type_id , and
	 *           order.isbuyorder , ordered by price asc for SO and price desc for
	 *           BO
	 */
	@Cacheable("marketRegion")
	public List<MarketLine> forRegion(int regionId, int type_id, boolean isBuyOrder) {
		return reverseIf(repo.findByFetchResourceIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, type_id, isBuyOrder),
				isBuyOrder);
	}

	/**
	 * @return existing lines with given order.type_id and order.isbuyorder ,
	 *           ordered by price asc for SO and price desc for BO
	 */
	@Cacheable("marketAll")
	public List<MarketLine> forAll(int type_id, boolean isBuyOrder) {
		return reverseIf(repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(type_id, isBuyOrder), isBuyOrder);
	}

	public Stream<MarketLine> streamBOs(Collection<Integer> typeIds) {
		return repo.findByTypeIdInAndIsBuyOrderTrueOrderByPriceDesc(typeIds);
	}

	public Stream<MarketLine> streamBOsAt(Collection<Integer> typeIds, long locationId) {
		if (typeIds == null || typeIds.isEmpty()) {
			return Stream.empty();
		}
		return repo.findByTypeIdInAndLocationIdAndIsBuyOrderTrueOrderByPriceDesc(typeIds, locationId);
	}

	public Stream<MarketLine> streamSOs(Collection<Integer> typeIds) {
		return repo.findByTypeIdInAndIsBuyOrderFalseOrderByPriceAsc(typeIds);
	}

	public Stream<MarketLine> streamSOsAt(Collection<Integer> typeIds, long locationId) {
		if (typeIds == null || typeIds.isEmpty()) {
			return Stream.empty();
		}
		return repo.findByTypeIdInAndLocationIdAndIsBuyOrderFalseOrderByPriceAsc(typeIds, locationId);
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
	double price(List<MarketLine> lines, long quantity, boolean cumulated, boolean infOnMissing) {
		long remain = quantity;
		double cumul = 0.0;
		for (MarketLine line : lines) {
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

	@Setter
	private float clipmult = 10;

	List<OfferStat> sellGain(List<MarketLine> sos, List<MarketLine> bos) {
		ArrayList<OfferStat> ret = new ArrayList<>();
		long cumulQtty = 0;
		double cumulValue = 0.0;
		Double lowestSOPrice = null;
		for (MarketLine rl : sos) {
			if (lowestSOPrice == null) {
				lowestSOPrice = rl.getPrice();
			} else if (rl.getPrice() > lowestSOPrice * clipmult) {
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
		for (MarketLine rl : bos) {
			if (highestBOPrice == null) {
				highestBOPrice = rl.getPrice();
			} else if (rl.getPrice() < highestBOPrice / clipmult) {
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
		List<MarketLine> sos = repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, typeId, false);
		List<MarketLine> bos = new ArrayList<>(
				repo.findByLocationIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(locationId, typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}

	public List<OfferStat> offerStatsRegion(int regionId, int typeId) {
		List<MarketLine> sos = repo.findByFetchResourceIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, typeId, false);
		List<MarketLine> bos = new ArrayList<>(
		    repo.findByFetchResourceIdAndTypeIdAndIsBuyOrderOrderByPriceAsc(regionId, typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}

	public List<OfferStat> offerStatsAll(int typeId) {
		List<MarketLine> sos = repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(typeId, false);
		List<MarketLine> bos = new ArrayList<>(repo.findByTypeIdAndIsBuyOrderOrderByPriceAsc(typeId, true));
		Collections.reverse(bos);
		return sellGain(sos, bos);
	}


	@Override
	public List<String> getCacheList() {
		return List.of(
			"marketAll",
			"marketLocation",
			"marketRegion",
			"marketBoValueLocation",
			"marketSoValueLocation",
			"marketLocationTypesBo",
			"marketLocationTypesSo");
	}


}
