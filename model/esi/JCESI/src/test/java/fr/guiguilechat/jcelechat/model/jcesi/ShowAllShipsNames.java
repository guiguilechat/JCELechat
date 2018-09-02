package fr.guiguilechat.jcelechat.model.jcesi;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;

public class ShowAllShipsNames {

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 50;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		CacheStatic cache = ESIStatic.INSTANCE.cache;
		long time = System.currentTimeMillis();
		cache.insurance.prices().copy().parallelStream().map(price -> cache.universe.types(price.type_id).get())
				.forEachOrdered(t -> System.err.println(t.name + "\t" + t.type_id));
		System.err.println("total fetch " + 0.001 * (System.currentTimeMillis() - time) + "s");
	}

}
