package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EcorporationRoles {

	//
	// SDE loading
	//

	public static final MapIntLoader<EcorporationRoles> LOADER = new MapIntLoader<>(
			"corporationRoles",
			EcorporationRoles.class);

	//
	// file structure
	//


	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();
	public List<Integer> roleGroupIDs = new ArrayList<>();
	public String shortName;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " description=" + first.enDescription()
				+ " shortName=" + first.shortName);
	}

}
