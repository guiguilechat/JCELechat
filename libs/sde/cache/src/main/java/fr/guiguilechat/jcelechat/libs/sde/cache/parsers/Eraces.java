package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Eraces {

	//
	// SDE loading
	//

	public static final IntMapLoader<Eraces> LOADER = new IntMapLoader<>(
			"races",
			Eraces.class);

	//
	// file structure
	//
	public HashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public HashMap<String, String> name = new LinkedHashMap<>();
	public int shipTypeID;
	public Map<Integer, Integer> skills = new LinkedHashMap<>();

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
