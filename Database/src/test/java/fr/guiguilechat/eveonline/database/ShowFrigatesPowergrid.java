package fr.guiguilechat.eveonline.database;

import java.util.Arrays;
import java.util.HashSet;

import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class ShowFrigatesPowergrid {

	public static void main(String[] args) {
		YamlDatabase db = new YamlDatabase();
		HashSet<String> frigatesGroups = new HashSet<>(Arrays.asList("Frigate", "Interceptor", "Covert Ops",
				"Electronic Attack Ship", "Assault Frigate", "Logistics Frigate"));
		db.getHulls().values().stream().filter(h -> frigatesGroups.contains(h.groupName))
		.sorted((h1, h2) -> h2.attributes.powergrid - h1.attributes.powergrid)
		.forEach(h -> System.err.println(h.name + " " + h.attributes.powergrid));
	}

}
