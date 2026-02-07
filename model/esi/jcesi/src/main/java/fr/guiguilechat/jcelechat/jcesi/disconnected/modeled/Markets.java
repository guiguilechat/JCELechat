package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.GroupedPrices;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.IPricing;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location.LOCTYPE;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.impl.collections.SetHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.holders.interfaces.collections.SetHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class Markets {

	public static final Logger logger = LoggerFactory.getLogger(Markets.class);

	public final ESIRawPublic esiConnection;

	public Markets(ESIRawPublic esiConnection) {
		this.esiConnection = esiConnection;
	}

	// regionid-> regional market
	private HashMap<Integer, RegionalMarket> regionMarkets = new HashMap<>();

	/**
	 * get the regional market for given region ID. The returned market is already
	 * scheduled to retrieve the orders.
	 *
	 * @param regionID
	 * @return
	 */
	public RegionalMarket getMarket(int regionID) {
		RegionalMarket rm = regionMarkets.get(regionID);
		if (rm == null) {
			// first check that the requested region id indeed exists
			if (!esiConnection.cache().universe.regions().get().contains(regionID)) {
				logger.error("requested inexisting region id " + regionID, new NullPointerException());
				return null;
			}
			synchronized (regionMarkets) {
				rm = regionMarkets.get(regionID);
				if (rm == null) {
					rm = new RegionalMarket(esiConnection.cache(), regionID);
					rm.getAllOrders();
					regionMarkets.put(regionID, rm);
				}
			}
		}
		return rm;
	}

	// regional shortcuts

	public RegionalMarket domain() {
		return getMarket(10000043);
	}

	public RegionalMarket heimatar() {
		return getMarket(10000030);
	}

	public RegionalMarket metropolis() {
		return getMarket(10000042);
	}

	public RegionalMarket sinqLaison() {
		return getMarket(10000032);
	}

	public RegionalMarket theForge() {
		return getMarket(10000002);
	}

	// station shortcuts

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IPricing amarr_hub = getLocalMarket(60008494);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IPricing dodixie_hub = getLocalMarket(60011866);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IPricing hek_hub = getLocalMarket(60005686);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IPricing jita_hub = getLocalMarket(60003760);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IPricing rens_hub = getLocalMarket(60004588);

	@Getter(lazy = true)
	private final IPricing[] empireHubs = { jita_hub(), amarr_hub(), dodixie_hub(), hek_hub(), rens_hub() };

	//

	private Map<Integer, IPricing> stationsMarket = Collections.synchronizedMap(new HashMap<>());

	public IPricing getStationMarket(int stationId, int regionId) {
		return stationsMarket.computeIfAbsent(stationId, _ -> getMarket(regionId).filter(stationId, 0, false));
	}

	private HashMap<Long, IPricing> localMarket = new HashMap<>();

	/**
	 * get a pricing for a specific location id. If the locationd id is a region,
	 * will get the market for that region. If a system, will get the market for
	 * that system. If a structure or station, will get the market for that one.
	 *
	 * @param locationId
	 * @return
	 */
	public IPricing getLocalMarket(long locationId) {
		// if a region : get the regional market.
		if (locationId >= 10000000L && locationId < 12000000L) {
			return getMarket((int) locationId);
		}
		IPricing ret = localMarket.get(locationId);
		if (ret == null) {
			synchronized (localMarket) {
				ret = localMarket.get(locationId);
				if (ret == null) {
					Location loc = Location.resolve(null, locationId);
					if (loc.type == LOCTYPE.STATION) {
						ret = getStationMarket((int) loc.id, loc.region().region_id);
					} else {
						RegionalMarket rm = getMarket(loc.region().region_id);
						ret = rm.filter((int) locationId, 0, false);
					}
					localMarket.put(locationId, ret);
				}
			}
		}
		return ret;
	}

	//

	/**
	 * remove up to 10M from the first orders
	 */
	private static final double BO_VAL_REMOVE = 10000000;

	/**
	 * remove the first orders unless we have removed {@link Markets.BO_VAL_REMOVE}
	 * total
	 * value
	 *
	 * @param source
	 * @return source but with removed first orders unless total removed is enough
	 */
	protected List<R_get_markets_region_id_orders> removeLowerOrders(List<R_get_markets_region_id_orders> source) {
		List<R_get_markets_region_id_orders> ret = new ArrayList<>(source);
		double removed = 0.0;
		for (R_get_markets_region_id_orders order : source) {
			removed += order.price * order.volume_remain;
			if (removed < BO_VAL_REMOVE) {
				ret.remove(0);
			} else {
				break;
			}
		}
		return ret;
	}

	/**
	 * weighted average of the empire orders. The first one is The Forge and
	 * weighted 3, the second one Domain and weighted 2, the three others Sinq
	 * Laison, Metropolis, Heimatar each weighted 1 . if The Forge or Domain is
	 * absent, their weight is allocated to the next one instead, so eg if The Forge
	 * is absent, Domain weight is 3, Sinq Laison is 2.
	 *
	 * @param regional list of orders for the empires, with the important one in
	 *                 first position.
	 * @return weighted average, after removal of best and worst values.
	 */
	protected Double average(List<? extends List<R_get_markets_region_id_orders>> regional) {
		double min = Double.POSITIVE_INFINITY,
				max = 0.0,
				total = 0.0;
		int count = 0;
		int nextWeight = 3;
		for (List<R_get_markets_region_id_orders> l : regional) {
			if (l.size() > 0) {
				double price = l.get(0).price;
				if (price < min) {
					min = price;
				}
				if (price > max) {
					max = price;
				}
				total += price * nextWeight;
				count += nextWeight;
				nextWeight = nextWeight < 2 ? 1 : nextWeight - 1;
			}
		}
		if (count > 0) {
			return (total - min - max) / (count - 2);
		}
		return 0.0;
	}

	/**
	 * @param ordersLists list of orders list
	 * @return minimum value of the provided list at index 0
	 */
	protected Double min(List<? extends List<R_get_markets_region_id_orders>> ordersLists) {
		double min = Double.POSITIVE_INFINITY;
		for (List<R_get_markets_region_id_orders> l : ordersLists) {
			if (l.size() > 0) {
				double price = l.get(0).price;
				if (price < min) {
					min = price;
				}
			}
		}
		return min;
	}

	/**
	 * get empire average BO price after removing the highest and lowest values.
	 */
	@Getter(lazy = true)
	private final GroupedPrices empireAvgBoPrice = new GroupedPrices(
			new IPricing[] { theForge(), domain(), sinqLaison(), metropolis(), heimatar() },
			(market, typeId) -> market.listBuyOrders(typeId),
			this::average);

	private static final double DISCARD_SO_MIN = 200000000;

	/**
	 * lowest empire hub price, discarding {@value #DISCARD_SO_MIN}
	 */
	@Getter(lazy = true)
	private final GroupedPrices empireHubsLowestSoPrice = new GroupedPrices(getEmpireHubs(),
			(market, typeId) -> market.listSellOrdersDiscarding(typeId, DISCARD_SO_MIN),
			this::min);

	/**
	 * list of type ids that are seeded (sold by NPC) in empire
	 */
	@Getter(lazy = true)
	private final SetHolder<Integer> empireSeeded = SetHolderImpl.union(
			domain().getSeeded(),
			heimatar().getSeeded(),
			metropolis().getSeeded(),
			sinqLaison().getSeeded(),
			theForge().getSeeded());

	//
	// prices : adjusted and average
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ListHolder<R_get_markets_prices> marketPrices = esiConnection.cache().markets.prices();

	@Getter(lazy = true)
	private final MapHolder<Integer, Double> adjusteds = marketPrices().toMap(p -> p.type_id,
			p -> p.adjusted_price);

	public double getAdjusted(int itemId) {
		return getAdjusteds().get().getOrDefault(itemId, 0.0);
	}

	@Getter(lazy = true)
	private final MapHolder<Integer, Double> averages = marketPrices().toMap(p -> p.type_id, p -> p.average_price);

	public double getAverage(int itemId) {
		return getAverages().get().getOrDefault(itemId, 0.0);
	}
}
