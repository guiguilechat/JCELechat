package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;

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
				if (regionMarkets.get(regionID) == null) {
					rm = new RegionalMarket(esiConnection.cache, regionID);
					regionMarkets.put(regionID, rm);
				} else {
					rm = regionMarkets.get(regionID);
				}
			}
		}
		return rm;
	}

	//
	// prices : adjusted and average
	//

	private HashMap<Integer, Double> cachedAverage = null;
	private HashMap<Integer, Double> cachedAdjusted = null;

	private void dl() {
		if (cachedAdjusted == null || cachedAverage == null) {
			synchronized (Markets.class) {
				if (cachedAdjusted != null && cachedAverage != null) {
					return;
				}
				HashMap<Integer, Double> fcachedAverage = new HashMap<>();
				HashMap<Integer, Double> fcachedAdjusted = new HashMap<>();
				for (R_get_markets_prices p : esiConnection.get_markets_prices(null).getOK()) {
					int id = p.type_id;
					fcachedAverage.put(id, p.average_price);
					fcachedAdjusted.put(id, p.adjusted_price);
				}
				cachedAverage = fcachedAverage;
				cachedAdjusted = fcachedAdjusted;
			}
		}
	}

	public double getAdjusted(int itemId) {
		dl();
		return cachedAdjusted.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}

	public double getAverage(int itemId) {
		dl();
		return cachedAverage.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}

}
