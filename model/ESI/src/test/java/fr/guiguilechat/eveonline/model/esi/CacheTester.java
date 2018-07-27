package fr.guiguilechat.eveonline.model.esi;

import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.esi.ConnectedImpl;
import fr.guiguilechat.eveonline.esi.disconnected.CacheStatic;
import fr.guiguilechat.eveonline.esi.disconnected.ESIStatic;
import fr.guiguilechat.eveonline.model.esi.compiled.G_ITransfer;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class CacheTester {

	public static void main(String[] args) {
		CacheStatic con = ESIStatic.INSTANCE.cache;


		// noparam-> container
		System.out.println("noparam->container started");
		Property<R_get_status> status = con.status.status();
		ConnectedImpl.listen(status, (ChangeListener<R_get_status>) (ob, old, now) -> System.out
				.println("noparam->container new number of players " + now.players));

		// noparam-> list
		System.out.println("noparam->list started");
		ObservableList<Integer> attributes = con.dogma.attributes();

		ConnectedImpl.listen(attributes, li -> System.out
				.println("noparam->list " + li.next() + " added " + li.getAddedSubList() + " ; removed " + li.getRemoved()
				+ " hasnext" + li.next()));
		ConnectedImpl.wait(attributes);

		// noparam-> map
		// can't find any

		// params->list
		System.out.println("params->list started");
		// vni sell orders in sinq laison
		ObservableList<R_get_markets_region_id_orders> orders = con.markets.orders(G_ITransfer.order_type.sell, 10000032,
				17843);

		ConnectedImpl.listen(orders,
				(ListChangeListener<R_get_markets_region_id_orders>) li -> System.out.println("params->list "
						+ li.next() + " added " + li.getAddedSubList().stream().map(order -> order.price).collect(Collectors.toList())
						+ " ; removed " + li.getRemoved() + " hasnext" + li.next()));
		ConnectedImpl.wait(orders);
	}
}
