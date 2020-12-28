package fr.guiguilechat.jcelechat.programs.showattributes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;

public class SortInvasionStandingGain {

	public static void main(String[] args) {
		CacheStatic esi = ESIStatic.INSTANCE.cache;
		Set<Integer> trigGroups = new HashSet<>(Arrays.asList(4028));
		Set<Integer> edenGroups = new HashSet<>(Arrays.asList(4034, 4035, 4036, 4037));
		for (int gid : trigGroups) {
			esi.universe.groups(gid);
		}
		for (int gid : edenGroups) {
			esi.universe.groups(gid);
		}
		int[] trigTypes = trigGroups.stream().flatMapToInt(gid -> IntStream.of(esi.universe.groups(gid).get().types))
				.toArray();
		int[] edenTypes = edenGroups.stream().flatMapToInt(gid -> IntStream.of(esi.universe.groups(gid).get().types))
				.toArray();
		for (int tid : trigTypes) {
			esi.universe.types(tid);
		}
		for (int tid : edenTypes) {
			esi.universe.types(tid);
		}
		HashMap<Float, Set<R_get_universe_types_type_id>> entitiesGroupedByEdenStanding = new HashMap<>();
		for (int tid : trigTypes) {
			R_get_universe_types_type_id type = esi.universe.types(tid).get();
			float factionLoss = Stream.of(type.dogma_attributes).filter(d -> d.attribute_id == 562).map(d -> d.value)
					.findFirst().orElse(0.0f);
			entitiesGroupedByEdenStanding.computeIfAbsent(factionLoss, d -> new HashSet<>()).add(type);
		}
		for (int tid : edenTypes) {
			R_get_universe_types_type_id type = esi.universe.types(tid).get();
			float factionLoss = -Stream.of(type.dogma_attributes).filter(d -> d.attribute_id == 562).map(d -> d.value)
					.findFirst().orElse(0.0f);
			entitiesGroupedByEdenStanding.computeIfAbsent(factionLoss, d -> new HashSet<>()).add(type);
		}
		List<Entry<Float, Set<R_get_universe_types_type_id>>> sorted = entitiesGroupedByEdenStanding.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getKey))
				.collect(Collectors.toList());
		for (Entry<Float, Set<R_get_universe_types_type_id>> e : sorted) {
			if (e.getKey() != 0.0f) {
				System.out.println(e.getKey() + "\t" + e.getValue().stream().map(t -> t.name).collect(Collectors.joining(";")));
			}
		}
	}

}
