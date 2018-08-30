package fr.guiguilechat.jcelechat.model.jcesi;

import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.order_type;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableDoubleValue;

public class ExampleStaticAccess {

	@SuppressWarnings({ "unused", "static-access" })
	public static void main(String[] args) {

		/** a static access does not need an account to retrieve data */
		ESIStatic stat = ESIStatic.INSTANCE;

		int theforge = 10000002;
		int veldspar = 1230;

		/**
		 * get the history of veldspar, in The Forge.<br />
		 * take the first (=random) value and get the average sale value
		 */
		double veldsparAVG = stat.get_markets_history(theforge, veldspar, null)[0].average;

		/**
		 * some access have pages, we can fetch all the pages using this
		 * method<br />
		 * get all the page of present BUY orders in TheForge for Veldpsar,
		 */
		List<R_get_markets_region_id_orders> bos = stat
				.loadPages((p, h) -> stat.get_markets_orders(order_type.buy, p, theforge, veldspar, h), null);
		/** then you can get eg the maximum BO */
		double maxbo = bos.stream().mapToDouble(bo -> bo.price).max().getAsDouble();

		/**
		 * The esi has a built in cache manager.<br />
		 * The cache fetch the pages and put the data in the holder
		 */
		ObsListHolder<R_get_markets_region_id_orders> cachebos = stat.cache.markets
				.orders(fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.order_type.buy, theforge,
						veldspar);

		/** cache should return the same holder when given the same parameters */
		if (stat.cache.markets.orders(order_type.buy, theforge, veldspar) != cachebos) {
			throw new RuntimeException();
		}

		/**
		 * wait for the data to be fetched at least once. This is actually not
		 * needed unless you are in a main
		 */
		cachebos.waitData();

		/**
		 * Cache data are refreshed as soon as the expiry date is reached. When
		 * working with a cache, you must sync over the holder to avoid cache
		 * corruption
		 */
		synchronized (cachebos) {
			maxbo = cachebos.copy().stream().mapToDouble(bo -> bo.price).max().getAsDouble();
		}

		/** you can create bindings to keep extracted data in sync */
		DoubleBinding maxBOBinding = Bindings.createDoubleBinding(() -> {
			synchronized (cachebos) {
				return cachebos.copy().stream().mapToDouble(bo -> bo.price).max().getAsDouble();
			}
		}, cachebos.asObservable());

		/**
		 * since this is tedious and error prone I bring some in the modeled class
		 */
		ObservableDoubleValue maxBOObs = ESIAccess.INSTANCE.markets.getMarket(theforge).getBO(veldspar, 1);

		/**
		 * ^You should use this one ^ Everybody loves maxBOObs.
		 */
	}
}