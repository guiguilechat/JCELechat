package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.IPricing;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class Markets {

	public static final Logger logger = LoggerFactory.getLogger(Markets.class);

	public final ESIStatic esiConnection;

	public Markets(ESIStatic esiConnection) {
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
			if (!esiConnection.cache().universe.regions().get().contains(regionID)) {
				logger.warn("requested inexisting region id " + regionID, new NullPointerException());
				return null;
			}
			synchronized (regionMarkets) {
				rm = regionMarkets.get(regionID);
				if (rm == null) {
					rm = new RegionalMarket(esiConnection.cache(), regionID);
					regionMarkets.put(regionID, rm);
				} else {
					rm = regionMarkets.get(regionID);
				}
			}
		}
		return rm;
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
		if (locationId >= 10000000l && locationId < 12000000l) {
			return getMarket((int) locationId);
		}
		IPricing lm = localMarket.get(locationId);
		if (lm == null) {
			synchronized (localMarket) {
				lm = localMarket.get(locationId);
				if (lm == null) {
					Location loc = Location.resolve(null, locationId);
					RegionalMarket rm = getMarket(loc.region().region_id);
					lm = rm.filter((int) locationId, 0, false);
					localMarket.put(locationId, lm);
				}
			}
		}
		return lm;
	}

	//
	// prices : adjusted and average
	//


	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsListHolder<R_get_markets_prices> marketPrices = esiConnection.cache().markets.prices();

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, Double> adjusteds = marketPrices().toMap(p -> p.type_id,
			p -> p.adjusted_price);

	public double getAdjusted(int itemId) {
		return getAdjusteds().getOrDefault(itemId, 0.0);
	}

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, Double> averages = marketPrices().toMap(p -> p.type_id, p -> p.average_price);


	public double getAverage(int itemId) {
		return getAverages().getOrDefault(itemId, 0.0);
	}
}
