package fr.guiguilechat.jcelachat.programs.npcdamages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MainNPCDamages {

	public static void main(String[] args) {

		Set<String> shipTypes = new HashSet<>(
				Arrays.asList("Frigate", "Destroyer", "Cruiser", "BattleCruiser", "Battleship"));
		Set<String> factions = new HashSet<>(
				Arrays.asList("Angel Cartel", "Blood Raiders", "Guristas", "Mordus Legion", "Rogue Drone", "Sansha's Nation",
						"Serpentis"));
		// faction > type > entity ids
		HashMap<String, HashMap<String, List<Integer>>> groupedrats = new HashMap<>();
		R_get_universe_categories_category_id entityCat = ESIStatic.INSTANCE.cache.universe
				.categories(Entity.METACAT.getCategoryId()).get();
		IntStream.of(entityCat.groups).parallel().forEach(grouip -> {
			R_get_universe_groups_group_id entitygroup = ESIStatic.INSTANCE.cache.universe.groups(grouip).get();
			String name = entitygroup.name;
			if (name.contains("Deadspace") || name.contains("Asteroid")) {
				String type = shipTypes.stream().filter(n -> name.contains(n)).findAny().orElse(null);
				if (type != null) {
					String faction = factions.stream().filter(n -> name.contains(n)).findAny().orElse(null);
					if (faction != null) {
						// System.err.println(" " + entitygroup.name);
					} else {
						System.err.println("no faction for " + name);
					}
				} else {
					System.err.println("no ship type for " + name);
				}
			} else {
				System.err.println("no domain for " + name);
			}
		});
	}

}
