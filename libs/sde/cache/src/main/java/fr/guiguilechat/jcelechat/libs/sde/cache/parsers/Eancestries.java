package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Eancestries {

	//
	// SDE loading
	//

	public static final IntMapLoader<Eancestries> LOADER = new IntMapLoader<>(
			"ancestries",
			Eancestries.class);

	//
	// file structure
	//

	/**
	 * {@link Ebloodlines}
	 */
	public int bloodlineID;
	public int charisma;
	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public int intelligence;
	public int memory;
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();
	public int perception;
	public String shortDescription;
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
		System.out.println("first : bloodine=" + first.bloodlineID + " name=" + first.enName());
	}

}
