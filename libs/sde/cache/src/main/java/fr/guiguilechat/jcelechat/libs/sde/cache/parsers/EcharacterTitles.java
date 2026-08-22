package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapStringLoader;

public class EcharacterTitles {

	//
	// SDE loading
	//

	public static final MapStringLoader<EcharacterTitles> LOADER =
			new MapStringLoader<>(
					"characterTitles",
					EcharacterTitles.class);

	//
	// file structure
	//

	public Map<String, String> name = new LinkedHashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next();
		System.out.println("first id=" + first.getKey() + " name=" + first.getValue().enName());
	}

}
