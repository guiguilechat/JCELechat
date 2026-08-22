package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EcorporationActivities {

	//
	// SDE loading
	//

	public static final MapIntLoader<EcorporationActivities> LOADER = new MapIntLoader<>(
			"corporationActivities",
			EcorporationActivities.class);

	//
	// file structure
	//

	public HashMap<String, String> name = new LinkedHashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}
}
