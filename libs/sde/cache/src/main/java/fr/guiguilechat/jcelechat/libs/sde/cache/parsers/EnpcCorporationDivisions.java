package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EnpcCorporationDivisions {

	//
	// SDE loading
	//

	public static final MapIntLoader<EnpcCorporationDivisions> LOADER = new MapIntLoader<>(
			"npcCorporationDivisions",
			EnpcCorporationDivisions.class);

	//
	// file structure
	//

	public HashMap<String, String> description = new HashMap<>();
	public String displayName;
	public String internalName;
	public HashMap<String, String> leaderTypeName = new HashMap<>();
	public HashMap<String, String> name = new HashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.displayName);
	}
}
