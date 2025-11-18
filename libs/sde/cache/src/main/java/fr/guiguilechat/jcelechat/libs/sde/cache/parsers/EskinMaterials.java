package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EskinMaterials {

	//
	// SDE loading
	//

	public static final IntMapLoader<EskinMaterials> LOADER = new IntMapLoader<>(
			"skinMaterials",
			EskinMaterials.class,
			Set.of("materialSetID"));

	//
	// file structure
	//

	public HashMap<String, String> displayName = new LinkedHashMap<>();
	public int materialSetID;

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : displayName=" + first.enDisplayName());
	}
}
