package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Ebloodlines {

	//
	// SDE loading
	//

	public static final IntMapLoader<Ebloodlines> LOADER = new IntMapLoader<>(
			"bloodlines",
			Ebloodlines.class,
			Set.of("description"));

	//
	// file structure
	//

	public int charisma;
	/**
	 * {@link EnpcCorporations}
	 */
	public long corporationID;
	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public int intelligence;
	public int memory;
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();
	public int perception;
	/**
	 * {@link Eraces}
	 */
	public int raceID;
	public int willpower;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : corporation=" + first.corporationID + " name=" + first.enName());
	}

}
