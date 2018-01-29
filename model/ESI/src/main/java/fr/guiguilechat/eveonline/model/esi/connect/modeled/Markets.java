package fr.guiguilechat.eveonline.model.esi.connect.modeled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.javafx.collections.ObservableListWrapper;

import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;

@SuppressWarnings("restriction")
public class Markets {

	public static class MarketOffer {

	}

	public class CachedOrdersList extends ObservableListWrapper<MarketOffer> {

		public CachedOrdersList() {
			super(new ArrayList<>());
		}

		protected void fetch() {
			// esiConnection.raw.get_markets_region_id_orders(order_type, region_id)
		}

	}

	protected final ESIConnection esiConnection;

	public Markets(ESIConnection esiConnection) {
		this.esiConnection = esiConnection;
	}

	HashMap<Integer, Map<Integer, CachedOrdersList>> cachedBuyOders = new HashMap<>();
	HashMap<Integer, Map<Integer, CachedOrdersList>> cachedSellOders = new HashMap<>();

	public CachedOrdersList getOrders(boolean buy, int regionID, int typeID) {
		HashMap<Integer, Map<Integer, CachedOrdersList>> map = buy ? cachedBuyOders : cachedSellOders;
		Map<Integer, CachedOrdersList> map2 = map.get(regionID);
		if (map2 == null) {
			map2 = new HashMap<>();
			map.put(regionID, map2);
		}
		return null;
	}

}
