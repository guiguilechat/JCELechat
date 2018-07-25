package fr.guiguilechat.eveonline.model.esi;

import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.esi.compiled.Swagger.order_type;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class CacheTester {

	public static void main(String[] args) {
		ESIConnection con = new ESIConnection(null, null);


		// noparam-> container
		System.out.println("noparam->container started");
		Property<R_get_status> status = con.cache.status.status();
		synchronized (status) {
			if (status.getValue() != null) {
				System.out.println("got status players" + status.getValue().players);
			}
			status.addListener((ChangeListener<R_get_status>) (ob, old, now) -> System.out
					.println("noparam->container new number of players " + now.players));
		}

		// noparam-> list
		System.out.println("noparam->list started");
		ObservableList<Integer> attributes = con.cache.dogma.attributes();
		synchronized (attributes) {
			for (int i : attributes) {
				System.out.println("got attribute " + i);
			}
			attributes.addListener((ListChangeListener<Integer>) li -> System.out
					.println("noparam->list " + li.next() + " added " + li.getAddedSubList() + " ; removed " + li.getRemoved()
					+ " hasnext" + li.next()));
		}

		// noparam-> map
		// can't find any

		// params->list
		System.out.println("params->list started");
		// vni sell orders in sinq laison
		ObservableList<R_get_markets_region_id_orders> orders = con.cache.markets.orders(order_type.sell, 10000032, 17843);
		synchronized (orders) {
			for (R_get_markets_region_id_orders i : orders) {
				System.out.println("got order price " + i.price);
			}
			orders.addListener((ListChangeListener<R_get_markets_region_id_orders>) li -> System.out.println("params->list "
					+ li.next() + " added " + li.getAddedSubList().stream().map(order -> order.price).collect(Collectors.toList())
					+ " ; removed " + li.getRemoved() + " hasnext" + li.next()));
		}

		while (true) {
			Thread.yield();
		}
	}
}
