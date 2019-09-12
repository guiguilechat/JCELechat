package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;

public class VolumicPrice {

	public static class TypeData {

		public final R_get_universe_types_type_id type;
		public final ObsDoubleHolder buyPrice;
		public final ObsDoubleHolder volPrice;

		public TypeData(R_get_universe_types_type_id t, RegionalMarket market) {
			type = t;
			buyPrice = market.getBO(t.type_id, 1);
			volPrice = buyPrice.div((double) t.volume);
		}

	}

	public static void main(String[] args) {

		int parrallelism = Runtime.getRuntime().availableProcessors() * 1000;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		ESIAccess esi = ESIAccess.INSTANCE;
		RegionalMarket market = esi.markets.getMarket(10000002);

		Set<String> createdItems = IntStream.of(esi.universe.cache.categories(9).get().groups).parallel()
				.mapToObj(gi -> esi.universe.cache.groups(gi).get())
				.flatMap(group -> IntStream.of(group.types).parallel().mapToObj(i -> esi.universe.cache.types(i).get()))
				.filter(type -> type.published && type.market_group_id != 0)
				// .peek(type -> System.err.println("type " + type.name + " is
				// published"))
				.map(type -> type.name.replace(" Blueprint", "")).collect(Collectors.toSet());
		System.err.println("created items set generated " + createdItems.size());

		List<TypeData> list = esi.universe.cache.categories().get().parallelStream()
				.map(catid -> esi.universe.cache.categories(catid).get()).filter(cat -> cat.published)
				// .peek(cat -> System.err.println("cat " + cat.name + " is published"))
				.flatMap(cat -> IntStream.of(cat.groups).parallel().mapToObj(i -> esi.universe.cache.groups(i).get()))
				.filter(group -> group.published)
				// .peek(group -> System.err.println("group " + group.name + " is
				// published"))
				.flatMap(group -> IntStream.of(group.types).parallel().mapToObj(i -> esi.universe.cache.types(i).get()))
				.filter(type -> createdItems.contains(type.name))
				.peek(type -> System.err.println("type " + type.name + " is	created")).map(t -> new TypeData(t, market))
				.collect(Collectors.toList());
		System.err.println("types fetched, sorting");
		Collections.sort(list, (t1, t2) -> Double.compare(t1.volPrice.get(), t2.volPrice.get()));
		for (TypeData data : list) {
			System.out.println(data.type.name + "\t" + data.volPrice.get());
		}
	}

}
