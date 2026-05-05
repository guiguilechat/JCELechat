package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Earchetypes {

	//
	// SDE loading
	//

	public static final IntMapLoader<Earchetypes> LOADER =
			new IntMapLoader<>(
					"archetypes",
					Earchetypes.class);

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();

	public Map<String, String> title = new LinkedHashMap<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enTitle() {
		return title == null ? null : title.get("en");
	}

	// testing

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enTitle());
	}

}
