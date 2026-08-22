package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EshipTreeFactions {

	//
	// SDE loading
	//

	public static final MapIntLoader<EshipTreeFactions> LOADER = new MapIntLoader<>(
			"shipTreeFactions",
			EshipTreeFactions.class);

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public Map<Integer, Integer> elements = new LinkedHashMap<>();
	public String icon;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	//
	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : enDescription=" + first.enDescription());
	}

}
