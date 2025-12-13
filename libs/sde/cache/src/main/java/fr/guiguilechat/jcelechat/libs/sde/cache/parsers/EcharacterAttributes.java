package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EcharacterAttributes {

	//
	// SDE loading
	//

	public static final IntMapLoader<EcharacterAttributes> LOADER = new IntMapLoader<>(
			"characterAttributes",
			EcharacterAttributes.class);

	//
	// file structure
	//

	public String description;
	public int iconID;
	public HashMap<String, String> name = new HashMap<>();
	public String notes;
	public String shortDescription;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : name=" + first.enName() + " shortDesc=" + first.shortDescription);
	}

}
