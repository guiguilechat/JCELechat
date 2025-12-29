package fr.guiguilechat.jcelechat.sde.items;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.types.*;

public class ShowItemsNameContained {

	public static void main(String[] args) {
		List<EveType> types = Stream
				.of(Accessories.METACAT, AncientRelics.METACAT, Apparel.METACAT, Asteroid.METACAT, Celestial.METACAT,
						Charge.METACAT, Commodity.METACAT, Decryptors.METACAT, Deployable.METACAT, Drone.METACAT, Entity.METACAT,
						ExpertSystems.METACAT, Fighter.METACAT, Implant.METACAT, InfrastructureUpgrades.METACAT)
				.flatMap(mc -> mc.load().values().stream())
				.filter(it->it.published)
				.collect(Collectors.toList());
		Map<EveType, Set<EveType>> containedIn = new HashMap<>();
		for (EveType et : types) {
			containedIn.put(et,
					types.stream().filter(type -> type.name.toLowerCase().contains(et.name.toLowerCase()) && type.id != et.id)
					.collect(Collectors.toSet()));
		}
		List<Entry<EveType, Set<EveType>>> sorted = new ArrayList<>(containedIn.entrySet());
		Comparator<Entry<EveType, Set<EveType>>> compare = Comparator.comparing(e -> -e.getValue().size());
		compare = compare.thenComparing(e -> e.getKey().name);
		sorted.sort(compare);
		for (Entry<EveType, Set<EveType>> e : sorted) {
			java.lang.System.out.println(e.getKey().name + "\t" + e.getKey().id + "\t" + e.getValue().size());
			for (EveType et2 : e.getValue()) {
				java.lang.System.out.println("\t\t\t" + et2.name + "\t" + et2.id);
			}
		}
	}

}
