package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

@SuppressWarnings("serial")
public class EskinrComponentPointValues extends LinkedHashMap<Integer, Integer> {

	//
	// SDE loading
	//

	public static final MapIntLoader<EskinrComponentPointValues> LOADER = new MapIntLoader<>(
			"skinrComponentPointValues",
			EskinrComponentPointValues.class);

	//
	// file structure
	//

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : size=" + first.size());
	}

}
