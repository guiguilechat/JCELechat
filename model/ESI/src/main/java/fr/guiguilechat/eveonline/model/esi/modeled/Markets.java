package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.HashMap;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.modeled.market.RegionalMarket;
import javafx.collections.ObservableList;

public class Markets {

	public final ESIAccount esiConnection;

	public Markets(ESIAccount esiConnection) {
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
			synchronized (regionMarkets) {
				if (regionMarkets.get(regionID) == null) {
					rm = new RegionalMarket(this, regionID);
					regionMarkets.put(regionID, rm);
				} else {
					rm = regionMarkets.get(regionID);
				}
			}
		}
		return rm;
	}

	public ObservableList<R_get_markets_region_id_orders> getOrders(boolean buy, int regionID, int typeID) {
		return buy ? getMarket(regionID).getMarketOrders(typeID).listBuyOrders()
				: getMarket(regionID).getMarketOrders(typeID).listSellOrders();
	}

	//
	// prices : adjusted and average
	//

	private static HashMap<Integer, Double> cachedAverage = null;
	private static HashMap<Integer, Double> cachedAdjusted = null;

	private static void dl() {
		if (cachedAdjusted == null || cachedAverage == null) {
			synchronized (Markets.class) {
				if (cachedAdjusted != null && cachedAverage != null) {
					return;
				}
				HashMap<Integer, Double> fcachedAverage = new HashMap<>();
				HashMap<Integer, Double> fcachedAdjusted = new HashMap<>();
				for (R_get_markets_prices p : ESIAccount.DISCONNECTED.raw.get_markets_prices(null)) {
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
