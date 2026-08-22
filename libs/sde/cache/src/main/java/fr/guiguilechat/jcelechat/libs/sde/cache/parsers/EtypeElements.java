package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EtypeElements {

	//
	// SDE loading
	//

	public static final MapIntLoader<EtypeElements> LOADER = new MapIntLoader<>(
			"typeElements",
			EtypeElements.class);

	//
	// file structure
	//

	public Map<Integer, Integer> elements = new LinkedHashMap<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : elements.size=" + first.elements.size());
	}

}
