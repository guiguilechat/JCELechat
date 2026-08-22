package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EnotificationTypes {

	//
	// SDE loading
	//

	public static final MapIntLoader<EnotificationTypes> LOADER = new MapIntLoader<>(
			"notificationTypes",
			EnotificationTypes.class);

	//
	// file structure
	//

	public Map<String, String> displayName = new LinkedHashMap<>();
	public String internalName;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : internalName=" + first.internalName);
	}

}
