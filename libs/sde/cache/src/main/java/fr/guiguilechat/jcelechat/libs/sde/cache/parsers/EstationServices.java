package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EstationServices {

	//
	// SDE loading
	//

	public static final IntMapLoader<EstationServices> LOADER = new IntMapLoader<>(
			"stationServices",
			EstationServices.class);

	//
	// file structure
	//

	public HashMap<String, String> description = new HashMap<>();
	public HashMap<String, String> serviceName = new HashMap<>();

	//

	public String enName() {
		return serviceName == null ? null : serviceName.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.getOrDefault("en", "no description " + description);
	}

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " description=" + first.enDescription());
	}

}
