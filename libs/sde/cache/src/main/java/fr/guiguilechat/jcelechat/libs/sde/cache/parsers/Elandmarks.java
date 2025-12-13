package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position3D;

public class Elandmarks {

	//
	// SDE loading
	//

	public static final IntMapLoader<Elandmarks> LOADER = new IntMapLoader<>(
			"landmarks",
			Elandmarks.class);

	//
	// file structure
	//

	public HashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public long locationID;
	public HashMap<String, String> name = new LinkedHashMap<>();
	public Position3D position;

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
