package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.stream.Stream;

public class ShowAllShipsNames {

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 50;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		long time = System.currentTimeMillis();
		boolean cached = false;
		if (cached) {
			CacheStatic cache = ESIStatic.INSTANCE.cache;
			cache.insurance.prices().get().parallelStream().map(price -> cache.universe.types(price.type_id).get())
			.forEachOrdered(t -> System.err.println(t.name + "\t" + t.type_id));
		} else {
			ESIStatic esi = ESIStatic.INSTANCE;
			Stream.of(esi.get_insurance_prices(null).getOK()).parallel()
			.map(price -> esi.get_universe_types(price.type_id, null).getOK())
			.forEachOrdered(t -> System.err.println(t.name + "\t" + t.type_id));
		}
		System.err.println("total fetch " + 0.001 * (System.currentTimeMillis() - time) + "s");
	}

}
