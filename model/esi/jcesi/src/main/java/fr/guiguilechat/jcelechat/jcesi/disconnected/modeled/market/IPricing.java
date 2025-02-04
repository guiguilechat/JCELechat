package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
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
	LocalTypeOrders getMarketOrders(int typeID, boolean buyOrders);

	default DoubleHolder getValue(int typeID, long qtty, boolean buy) {
		LocalTypeOrders lto = getMarketOrders(typeID, buy);
		DoubleHolder ret = lto.getValue(qtty);
		return ret;
	}

	default DoubleHolder getSOValue(int typeID, long qtty) {
		return getValue(typeID, qtty, false);
	}

	default DoubleHolder getBOValue(int typeID, long qtty) {
		DoubleHolder ret = getValue(typeID, qtty, true);
		return ret;
	}

	default Instant lastTypeIssued(int typeId) {
		Optional<String> lastTimeStamp = Stream.concat(
				getMarketOrders(typeId, true).getFilteredOrders().get().stream(),
				getMarketOrders(typeId, false).getFilteredOrders().get().stream()
			).map(o -> o.issued)
			.sorted().reduce((a, b) -> b);
		if (lastTimeStamp.isEmpty()) {
			return null;
		}
		return ESITools.fieldInstant(lastTimeStamp.get());
	}

}
