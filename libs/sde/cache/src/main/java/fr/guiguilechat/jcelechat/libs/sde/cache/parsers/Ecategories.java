package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Ecategories {

	//
	// SDE loading
	//

	public static final IntMapLoader<Ecategories> LOADER = new IntMapLoader<>(
			"categories",
			Ecategories.class);

	//
	// file structure
	//

	public int iconID;
	/** key is language short, like "en" */
	public HashMap<String, String> name = new HashMap<>();
	public boolean published;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : name=" + first.enName() + " published=" + first.published);
	}

}
