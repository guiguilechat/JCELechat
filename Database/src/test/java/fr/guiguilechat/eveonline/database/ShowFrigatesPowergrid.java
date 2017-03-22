package fr.guiguilechat.eveonline.database;

import java.util.Arrays;
import java.util.HashSet;

public class ShowFrigatesPowergrid {

	public static void main(String[] args) {
		Database db = Parser.getSDEDB();
		HashSet<String> frigatesGroups = new HashSet<>(Arrays.asList("Frigate", "Interceptor", "Covert Ops",
				"Electronic Attack Ship", "Assault Frigate", "Logistics Frigate"));
		db.hulls.values().stream().filter(h -> frigatesGroups.contains(h.group))
				.sorted((h1, h2) -> h2.attributes.powergrid - h1.attributes.powergrid)
				.forEach(h -> System.err.println(h.name + " " + h.attributes.powergrid));
	}

}
