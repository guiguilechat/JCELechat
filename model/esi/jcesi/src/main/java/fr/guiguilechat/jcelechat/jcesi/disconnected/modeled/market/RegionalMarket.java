package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.holders.interfaces.collections.SetHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RegionalMarket implements IPricing {

	private final CacheStatic cache;
	private final int regionId;

	@Getter(lazy = true)
	private final ListHolder<R_get_markets_region_id_orders> allOrders = cache.markets.orders(order_type.all, regionId,
			null);

	@Getter(lazy = true)
	private final ListHolder<R_get_markets_region_id_orders> buyOrders = new HalfPages(getAllOrders(), regionId, true)
			.getForced();

	@Getter(lazy = true)
	private final MapHolder<Integer, List<R_get_markets_region_id_orders>> buyOrdersByTypeId = getBuyOrders()
			.mapMap(l -> l.stream().collect(Collectors.groupingBy(order -> order.type_id)));

	@Getter(lazy = true)
	private final ListHolder<R_get_markets_region_id_orders> sellOrders = new HalfPages(getAllOrders(), regionId, false)
			.getForced();

	@Getter(lazy = true)
	private final MapHolder<Integer, List<R_get_markets_region_id_orders>> sellOrdersByTypeId = getSellOrders()
			.mapMap(l -> l.stream().collect(Collectors.groupingBy(order -> order.type_id)));

	/** ids of types that are seeded in that region */
	@Getter(lazy = true)
	private final SetHolder<Integer> seeded = getSellOrders()
	    .filter(order -> order.duration == 365)
	    .mapItems(order -> order.type_id)
	    .distinct();

	// orders by isbuy and typeid

	// typeid-> cached buy orders
	private final Map<Integer, LocalTypeOrders> cachedBuyOrders = new HashMap<>();

	// typeid-> cached sell orders
	private final Map<Integer, LocalTypeOrders> cachedSellOrders = new HashMap<>();

	@Override
	public LocalTypeOrders getMarketOrders(int typeID, boolean buyOrders) {
		Map<Integer, LocalTypeOrders> cachedOrders = buyOrders ? cachedBuyOrders : cachedSellOrders;
		LocalTypeOrders ret = cachedOrders.get(typeID);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(cachedOrders, () -> {
				LocalTypeOrders ret2 = cachedOrders.get(typeID);
				if (ret2 == null) {
					ret2 = new LocalTypeOrders(
							(buyOrders ? getBuyOrdersByTypeId() : getSellOrdersByTypeId())
									.at(typeID, Collections.emptyList()).mapList(l -> l),
							buyOrders);
					cachedOrders.put(typeID, ret2);
				}
				return ret2;
			});
		}
		return ret;
	}

	//
	// filtering
	//

	private static class OrderFilterParams {

		public final int centerId;
		public final int distance;
		public final boolean onlyHS;

		public OrderFilterParams(int centerId, int distance, boolean onlyHS) {
			this.centerId = centerId;
			this.distance = distance;
			this.onlyHS = onlyHS;
		}

		@Override
		public int hashCode() {
			return centerId + distance + (onlyHS ? 30 : 0);
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || o.getClass() != OrderFilterParams.class) {
				return false;
			}
			OrderFilterParams other = (OrderFilterParams) o;
			return other.centerId == centerId && other.distance == distance && onlyHS == other.onlyHS;
		}

		@Override
		public String toString() {
			return "center=" + centerId + " range=" + distance + " hsonly=" + onlyHS;
		}
	}

	private HashMap<OrderFilterParams, ProxyRegionalMarket> filtered = new HashMap<>();

	/**
	 * make a filtered (cached) pricing over a range, and a system/station in that
	 * region.
	 *
	 * @param centerId
	 *                 id of the system of station to limit. if set to a station,
	 *                 will
	 *                 only get the orders in that specific station.
	 * @param distance
	 *                 max distance if it's a system. 0 means in that system only.
	 * @param onlyHS
	 *                 limit to systems only in HS if center is system.
	 * @return cached filtered pricing.
	 */
	public IPricing filter(int centerId, int distance, boolean onlyHS) {
		if (!onlyHS && (centerId == 0 || distance < 0 || distance > 40)) {
			return this;
		}
		OrderFilterParams key = new OrderFilterParams(centerId, distance, onlyHS);
		ProxyRegionalMarket ret = filtered.get(key);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(filtered, () -> {
				ProxyRegionalMarket ret2 = filtered.get(key);
				if (ret2 == null) {
					boolean isStation = centerId >= 60000000 && centerId <= 61000000;
					if (isStation) {
						ret2 = new ProxyRegionalMarket(getAllOrders().filter(order -> centerId == order.location_id));
					} else {
						// generate all the systems in range first
						List<Integer> systemsInRange = new ArrayList<>();
						systemsInRange.add(centerId);
						if (distance > 0) {
							R_get_universe_regions_region_id region = ESIRawPublic.INSTANCE.cache().universe.regions(regionId).get();
							IntStream.of(region.constellations).parallel()
									.flatMap(ci -> IntStream.of(ESIRawPublic.INSTANCE.cache().universe.constellations(ci).get().systems))
									.filter(
											si -> si != centerId
													&& ESIRawPublic.INSTANCE.cache().route.get(null, null, centerId, flag.shortest, si)
															.get().size() <= distance)
									.forEach(systemsInRange::add);
						}
						log.debug("allowed systems in region " + regionId + " filter " + key + " are " + systemsInRange);
						// then get all the stations in those systems
						Set<Long> stationIds = systemsInRange.parallelStream()
								.map(si -> ESIRawPublic.INSTANCE.cache().universe.systems(si).get())
								.filter(sys -> !onlyHS || sys.security_status >= 0.45)
								.flatMapToLong(sys -> IntStream.of(sys.stations).asLongStream())
								.mapToObj(i -> (Long) i).collect(Collectors.toSet());
						log.debug(" corresponding stations are " + stationIds);
						ret2 = new ProxyRegionalMarket(getAllOrders().filter(order -> stationIds.contains(order.location_id)));
					}
					filtered.put(key, ret2);
				}
				return ret2;
			});
		}
		return ret;
	}

	@Override
	public String toString() {
		return "regionalMarket(" + regionId + ")";
	}

}
