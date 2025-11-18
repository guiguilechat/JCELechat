package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EdogmaUnits {

	//
	// SDE loading
	//

	public static final IntMapLoader<EdogmaUnits> LOADER = new IntMapLoader<>(
			"dogmaUnits",
			EdogmaUnits.class,
			Set.of("name"));

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public Map<String, String> displayName = new LinkedHashMap<>();

	public String name;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}
}
