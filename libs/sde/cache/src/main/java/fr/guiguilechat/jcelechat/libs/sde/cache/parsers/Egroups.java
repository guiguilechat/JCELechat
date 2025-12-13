package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Egroups {

	//
	// SDE loading
	//

	public static final IntMapLoader<Egroups> LOADER = new IntMapLoader<>(
			"groups",
			Egroups.class);

	//
	// file structure
	//

	public boolean anchorable;
	public boolean anchored;
	/** {@link Ecategories} */
	public int categoryID;
	public boolean fittableNonSingleton;
	public int iconID;
	public HashMap<String, String> name = new LinkedHashMap<>();
	public boolean published;
	public boolean useBasePrice;

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
