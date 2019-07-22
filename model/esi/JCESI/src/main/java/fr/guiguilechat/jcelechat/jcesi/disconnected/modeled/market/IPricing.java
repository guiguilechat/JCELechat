package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;

public interface IPricing {

	public RegionTypeOrders getMarketOrders(int typeID);

	public default ObsDoubleHolder getPrice(int typeID, long qtty, boolean buy) {
		return getMarketOrders(typeID).getPrice(buy, qtty);
	}

	public default ObsDoubleHolder getSO(int typeID, long qtty) {
		return getPrice(typeID, qtty, false);
	}

	public default ObsDoubleHolder getBO(int typeID, long qtty) {
		return getPrice(typeID, qtty, true);
	}

	public RegionTypeHistory getHistory(int typeID);

}
