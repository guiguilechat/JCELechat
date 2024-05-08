package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;

public class MainGroupLPStores {

	public static class GroupData {

		// the sets that are smaller than the considered set
		List<Set<Integer>> smallers = new ArrayList<>();

		// the sets that are bigger than the considered set.
		List<Set<Integer>> biggers = new ArrayList<>();

	}

	public static void main(String... args) {
		HashMap<Integer, R_get_loyalty_stores_corporation_id_offers> id2offer = new HashMap<>();
		CacheStatic access = ESIRawPublic.INSTANCE.cache();
		// precache
		access.corporations.npccorps().flatten(corp -> {
			access.corporations.get(corp);
			return access.loyalty.stores_offers(corp);
		}).mapItems(o -> access.universe.types(o.type_id));
		List<Integer> corps = access.corporations.npccorps().get();
		Map<Set<Integer>, Set<Integer>> offers2corps = new HashMap<>();
		for (int corpid : corps) {
			access.corporations.get(corpid);
			access.loyalty.stores_offers(corpid);
			List<R_get_loyalty_stores_corporation_id_offers> offers = access.loyalty.stores_offers(corpid).get();
			id2offer.putAll(offers.stream().collect(Collectors.toMap(o -> o.offer_id, o -> o)));
			Set<Integer> set = offers.stream().map(o -> o.offer_id).collect(Collectors.toSet());
			offers2corps.computeIfAbsent(set, o -> new HashSet<>()).add(corpid);
		}

		offers2corps.remove(Collections.emptySet());
		HashMap<Set<Integer>, GroupData> offers2Data = new HashMap<>();

		for (Entry<Set<Integer>, Set<Integer>> e : offers2corps.entrySet()) {
			Set<Integer> evaluated = e.getKey();
			// System.out.println(e.getKey().size() + " :" + e.getValue().stream()
			// .map(corpid -> access.corporations.get(corpid).get().name).sorted()
			// .collect(Collectors.toList()));
			GroupData data = offers2Data.computeIfAbsent(evaluated, s -> new GroupData());
			for (Set<Integer> other : offers2corps.keySet()) {
				if (other == evaluated) {
					continue;
				}
				if (evaluated.containsAll(other)) {
					data.smallers.add(other);
				}
				if (other.containsAll(evaluated)) {
					data.biggers.add(other);
				}
			}
		}

		List<Entry<Set<Integer>, GroupData>> sortedBySmaller = offers2Data.entrySet().stream()
				.sorted((e1, e2) -> Integer.compare(e2.getValue().biggers.size(), e1.getValue().biggers.size()))
				.collect(Collectors.toList());
		Set<Set<Integer>> done = new HashSet<>();
		for (Entry<Set<Integer>, GroupData> e : sortedBySmaller) {
			Set<Integer> evaluated = e.getKey();
			GroupData data = e.getValue();
			if (!done.contains(evaluated)) {
				System.out.println("(" + evaluated.size() + ")" + offers2corps.get(evaluated).stream()
						.map(corpid -> access.corporations.get(corpid).get().name).sorted().collect(Collectors.toList()));
				for (Set<Integer> bigger : data.biggers) {
					System.out.println("  (" + bigger.size() + ")" + offers2corps.get(bigger).stream()
							.map(corpid -> access.corporations.get(corpid).get().name).sorted().collect(Collectors.toList()));
					done.add(bigger);
				}
			}
		}
	}

}
