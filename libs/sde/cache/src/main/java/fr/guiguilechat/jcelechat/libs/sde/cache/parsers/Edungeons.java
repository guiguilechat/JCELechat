package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Edungeons {

	//
	// SDE loading
	//

	public static final IntMapLoader<Edungeons> LOADER =
			new IntMapLoader<>(
					"dungeons",
					Edungeons.class);

	//
	// file structure
	//

	public List<Integer> allowedShipsList;
	public int archetypeID;

	public Map<String, String> description = new LinkedHashMap<>();
	public int factionID;
	public Map<String, String> gameplayDescription = new LinkedHashMap<>();
	public Map<String, String> name = new LinkedHashMap<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enGameplayDescription() {
		return gameplayDescription == null ? null : gameplayDescription.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	// testing

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}

}
