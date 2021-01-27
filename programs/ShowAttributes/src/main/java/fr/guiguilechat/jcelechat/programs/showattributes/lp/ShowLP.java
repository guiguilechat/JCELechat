package fr.guiguilechat.jcelechat.programs.showattributes.lp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;

public class ShowLP {

	private static CacheStatic access = ESIStatic.INSTANCE.cache();

	public static class GroupData {

		Set<Integer> offers;

		public GroupData(Set<Integer> offers) {
			this.offers = offers;
		}

		// the sets that are smaller than the considered set
		List<GroupData> smallers = new ArrayList<>();

		// the sets that are bigger than the considered set.
		List<GroupData> biggers = new ArrayList<>();

	}

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 100;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		TypeGrouper tg = new TypeGrouper();
		// precache
		access.corporations.npccorps().flatten(corp -> {
			access.corporations.get(corp);
			return access.loyalty.stores_offers(corp);
		}).mapItems(o -> tg.semantic(o.type_id)).get();

		tg.streamGroups().forEachOrdered(l -> {
			if (l.size() > 1) {
				// System.err.println(l.stream().map(t ->
				// t.name).collect(Collectors.toList()));
			}
		});

		List<Integer> corps = access.corporations.npccorps().get();
		Map<Set<Integer>, Set<Integer>> offers2corps = new HashMap<>();
		for (int corpid : corps) {
			List<R_get_loyalty_stores_corporation_id_offers> offers = access.loyalty.stores_offers(corpid).get();
			Set<Integer> set = offers.stream().map(o -> tg.semantic(o.type_id)).collect(Collectors.toSet());
			offers2corps.computeIfAbsent(set, o -> new HashSet<>()).add(corpid);
		}

		offers2corps.remove(Collections.emptySet());
		HashMap<Set<Integer>, GroupData> offers2Data = new HashMap<>();

		for (Entry<Set<Integer>, Set<Integer>> e : offers2corps.entrySet()) {
			Set<Integer> evaluated = e.getKey();
			// System.out.println(e.getKey().size() + " :" + e.getValue().stream()
			// .map(corpid -> access.corporations.get(corpid).get().name).sorted()
			// .collect(Collectors.toList()));
			GroupData data = offers2Data.computeIfAbsent(evaluated, s -> new GroupData(s));
			for (Set<Integer> other : offers2corps.keySet()) {
				GroupData otherData = offers2Data.computeIfAbsent(other, s -> new GroupData(s));
				if (otherData == data) {
					continue;
				}
				if (evaluated.containsAll(other)) {
					data.smallers.add(otherData);
				}
				if (other.containsAll(evaluated)) {
					data.biggers.add(otherData);
				}
			}
		}

		showGroup("", offers2Data.values(), new HashSet<>(), offers2corps);

	}

	public static void showGroup(String prefix, Collection<GroupData> gd, Set<GroupData> done,
			Map<Set<Integer>, Set<Integer>> offers2corps) {
		List<GroupData> sortedByBiggerSizeDesc = gd.stream()
				.sorted((e1, e2) -> Integer.compare(e2.biggers.size(), e1.biggers.size())).collect(Collectors.toList());
		for (GroupData data : sortedByBiggerSizeDesc) {
			Set<Integer> evaluated = data.offers;
			if (done == null || !done.contains(data)) {
				System.out.println(prefix + "(" + evaluated.size() + ")" + offers2corps.get(evaluated).stream()
						.map(corpid -> access.corporations.get(corpid).get().name).sorted().collect(Collectors.toList()));
				showGroup("  " + prefix, data.biggers, null, offers2corps);
				if (done != null) {
					done.addAll(data.biggers);
				}
			}
		}
	}

}
