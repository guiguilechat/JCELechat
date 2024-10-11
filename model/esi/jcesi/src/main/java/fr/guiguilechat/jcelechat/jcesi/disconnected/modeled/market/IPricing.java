package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;

/**
 * interface to get a pricing of items
 */
public interface IPricing {

	/**
	 * @param typeID    type to get price of.
	 * @param buyOrders if true, return values from buy order. Else from sell orders
	 * @return cached data
	 */
	public LocalTypeOrders getMarketOrders(int typeID, boolean buyOrders);

	public default DoubleHolder getValue(int typeID, long qtty, boolean buy) {
		LocalTypeOrders lto = getMarketOrders(typeID, buy);
		DoubleHolder ret = lto.getValue(qtty);
		return ret;
	}

	public default DoubleHolder getSOValue(int typeID, long qtty) {
		return getValue(typeID, qtty, false);
	}

	public default DoubleHolder getBOValue(int typeID, long qtty) {
		DoubleHolder ret = getValue(typeID, qtty, true);
		return ret;
	}

}
