package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;

public class ShowTypeMissingDescription {

	public static void main(String[] args) {
		Map<Integer, Integer> descCounts = new HashMap<>();
		Map<Integer, Integer> nameCounts = new HashMap<>();
		for (Entry<Integer, Etypes> e : Etypes.LOADER.yaml().load().entrySet()) {
			HashMap<String, String> desc = e.getValue().description;
			HashMap<String, String> nam = e.getValue().name;
			int nbDesc = desc == null ? -1 : desc.size();
			descCounts.put(nbDesc, descCounts.getOrDefault(nbDesc, 0) + 1);
			int nbNames = nam == null ? -1 : nam.size();
			nameCounts.put(nbNames, nameCounts.getOrDefault(nbNames, 0) + 1);
		}
		System.out.println("description language numbers :");
		descCounts.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getKey))
				.forEach(e -> System.out.println("" + e.getKey() + "\t" + e.getValue()));
		System.out.println("name language numbers :");
		nameCounts.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getKey))
				.forEach(e -> System.out.println("" + e.getKey() + "\t" + e.getValue()));
	}

}
