package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.IPricing;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Markets {

	public static final Logger logger = LoggerFactory.getLogger(Markets.class);

	public final ESIStatic esiConnection;

	public Markets(ESIStatic esiConnection) {
		this.esiConnection = esiConnection;
	}

	// regionid-> regional market
	private HashMap<Integer, RegionalMarket> regionMarkets = new HashMap<>();

	/**
	 * get the regional market for given region ID.
	 *
	 * @param regionID
	 * @return
	 */
	public RegionalMarket getMarket(int regionID) {
		RegionalMarket rm = regionMarkets.get(regionID);
		if (rm == null) {
			if (!esiConnection.cache.universe.regions().get().contains(regionID)) {
				logger.warn("requested inexisting region id " + regionID, new NullPointerException());
				return null;
			}
			synchronized (regionMarkets) {
				rm = regionMarkets.get(regionID);
				if (rm == null) {
					rm = new RegionalMarket(esiConnection.cache, regionID);
					regionMarkets.put(regionID, rm);
				} else {
					rm = regionMarkets.get(regionID);
				}
			}
		}
		return rm;
	}

	private HashMap<Long, RegionalMarket> localMarket = new HashMap<>();

	/**
	 * get a pricing for a specific location id. If the locationd id is a region,
	 * will get the market for that region. If a system, will get the market for
	 * that system. If a structure or station, will get the market for that one.
	 *
	 * @param locationId
	 * @return
	 */
	public IPricing getLocalMarket(long locationId) {
		if (locationId >= 10000000l && locationId < 12000000l) {
			return getMarket((int) locationId);
		}
		RegionalMarket lm = localMarket.get(locationId);
		if (lm == null) {
			synchronized (localMarket) {
				lm = localMarket.get(locationId);
				if (lm == null) {
				}
			}
		}
		return lm;
	}

	//
	// prices : adjusted and average
	//

	public ObsListHolder<R_get_markets_prices> marketPrices() {
		return esiConnection.cache.markets.prices();
	}

	private ObsMapHolder<Integer, Double> adjusteds = null;

	public ObsMapHolder<Integer, Double> getAdjusteds() {
		if (adjusteds == null) {
			ObsListHolder<R_get_markets_prices> prices = marketPrices();
			LockWatchDog.BARKER.syncExecute(prices, () -> {
				if (adjusteds == null) {
					adjusteds = prices.toMap(p -> p.type_id, p -> p.adjusted_price);
				}
			});
		}
		return adjusteds;
	}

	public double getAdjusted(int itemId) {
		return getAdjusteds().getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}

	private ObsMapHolder<Integer, Double> averages = null;

	public ObsMapHolder<Integer, Double> getAverages() {
		if (averages == null) {
			ObsListHolder<R_get_markets_prices> prices = marketPrices();
			LockWatchDog.BARKER.syncExecute(prices, () -> {
				if (averages == null) {
					averages = prices.toMap(p -> p.type_id, p -> p.average_price);
				}
			});
		}
		return averages;
	}

	public double getAverage(int itemId) {
		return getAverages().getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}
}
