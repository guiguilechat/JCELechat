package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;

public interface IPricing {

	public LocalTypeOrders getMarketOrders(int typeID);

	public default DoubleHolder getPrice(int typeID, long qtty, boolean buy) {
		return getMarketOrders(typeID).getPrice(buy, qtty);
	}

	public default DoubleHolder getSO(int typeID, long qtty) {
		return getPrice(typeID, qtty, false);
	}

	public default DoubleHolder getBO(int typeID, long qtty) {
		return getPrice(typeID, qtty, true);
	}

	public RegionTypeHistory getHistory(int typeID);

}
