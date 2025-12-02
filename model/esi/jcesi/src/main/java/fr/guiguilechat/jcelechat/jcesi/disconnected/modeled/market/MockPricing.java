package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.ArrayList;
import java.util.HashMap;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;

/**
 *
 * pricing that requires to be given a list of the orders for each item.
 *
 */
public class MockPricing implements IPricing {

	public static class MockPricingType {

		public LocalTypeOrders sellOrders;
		public LocalTypeOrders buyOrders;
		public ListHolderImpl<R_get_markets_region_id_orders> orders = new ListHolderImpl<>();
		public final int type_id;

		public MockPricingType(int typeID) {
			type_id = typeID;
			buyOrders = new LocalTypeOrders(orders.filter(or -> or.type_id == type_id), true);
			sellOrders = new LocalTypeOrders(orders.filter(or -> or.type_id == type_id), false);
			orders.set(null);
		}

		public MockPricingType withSell(int qtty, double price) {
			R_get_markets_region_id_orders add = new R_get_markets_region_id_orders();
			add.type_id = type_id;
			add.volume_remain = qtty;
			add.price = price;
			add.min_volume = 1;
			add.is_buy_order = false;
			ArrayList<R_get_markets_region_id_orders> newlist = new ArrayList<>(orders.get());
			newlist.add(add);
			orders.set(newlist);
			return this;
		}

		public MockPricingType withBuy(int qtty, double price) {
			R_get_markets_region_id_orders add = new R_get_markets_region_id_orders();
			add.type_id = type_id;
			add.volume_remain = qtty;
			add.price = price;
			add.min_volume = 1;
			add.is_buy_order = true;
			ArrayList<R_get_markets_region_id_orders> newlist = new ArrayList<>(orders.get());
			newlist.add(add);
			orders.set(newlist);
			return this;
		}
	}

	private HashMap<Integer, MockPricingType> knownPricing = new HashMap<>();

	@Override
	public LocalTypeOrders getMarketOrders(int typeID, boolean buy) {
		return buy ? orders(typeID).buyOrders : orders(typeID).sellOrders;
	}

	public MockPricingType orders(int typeID) {
		synchronized (knownPricing) {
			return knownPricing.computeIfAbsent(typeID, MockPricingType::new);
		}
	}

	@Override
	public void onUpdate(Runnable r) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delOnUpdate(Runnable r) {
		throw new UnsupportedOperationException();
	}

}
