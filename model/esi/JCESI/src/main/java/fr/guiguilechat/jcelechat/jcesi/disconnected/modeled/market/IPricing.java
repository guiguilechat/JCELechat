package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import javafx.beans.value.ObservableDoubleValue;

public interface IPricing {

	public RegionTypeOrders getMarketOrders(int typeID);

	public default ObsDoubleHolder getPrice(int typeID, long qtty, boolean buy) {
		return getMarketOrders(typeID).getPrice(buy, qtty);
	}

	public default ObservableDoubleValue getSO(int typeID, long qtty) {
		ObsDoubleHolder var = getMarketOrders(typeID).getPrice(false, qtty);
		var.get();
		return var.asObservableNumber();
	}

	public default ObservableDoubleValue getBO(int typeID, long qtty) {
		ObsDoubleHolder var = getMarketOrders(typeID).getPrice(true, qtty);
		var.get();
		return var.asObservableNumber();
	}

	public RegionTypeHistory getHistory(int typeID);

}
