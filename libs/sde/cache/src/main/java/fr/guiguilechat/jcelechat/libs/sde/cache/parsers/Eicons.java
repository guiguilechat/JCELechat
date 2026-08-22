package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class Eicons {

	//
	// SDE loading
	//

	public static final MapIntLoader<Eicons> LOADER = new MapIntLoader<>(
			"icons",
			Eicons.class);

	//
	// file structure
	//

	public String iconFile;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.iconFile);
	}
}
