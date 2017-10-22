package fr.guiguilechat.eveonline.model.database;

import java.util.HashSet;

import fr.guiguilechat.eveonline.model.database.EveDatabase;
import fr.guiguilechat.eveonline.model.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;

public class PrintOreData {

	public static void main(String[] args) {
		EveDatabase db = new YamlDatabase();
		System.err.println("ore;id;extraction volume;max sec");
		HashSet<Double> knowSecs = new HashSet<>();
		db.getAsteroids().entrySet().stream()
		.sorted((a, b) -> (int) Math.signum(b.getValue().maxSecurity - a.getValue().maxSecurity)).forEach(e -> {
			Asteroid a = e.getValue();
			if (a.maxSecurity <= -1 || a.groupName.equals("Ice")) {
				return;
			}
					System.err.println(
							"" + e.getKey() + ";" + a.id + ";" + a.volume + ";" + (knowSecs.add(a.maxSecurity) ? a.maxSecurity : ""));
			if (a.compressedTo != null) {
				Asteroid to = db.getAsteroids().get(a.compressedTo);
				double volume = to.compressRatio * a.volume;
				System.err.println("" + to.name + ";" + to.id + ";" + volume);
			}
		});

	}

}
