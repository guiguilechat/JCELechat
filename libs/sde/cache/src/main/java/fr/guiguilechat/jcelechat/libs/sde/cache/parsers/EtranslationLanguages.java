package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.StringMapLoader;

public class EtranslationLanguages {

	//
	// SDE loading
	//

	public static final StringMapLoader<EtranslationLanguages> LOADER =
			new StringMapLoader<>(
					"translationLanguages",
					EtranslationLanguages.class);

	//
	// file structure
	//

	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next();
		System.out.println("first key=" + first.getKey() + " name=" + first.getValue().name);
	}

}
