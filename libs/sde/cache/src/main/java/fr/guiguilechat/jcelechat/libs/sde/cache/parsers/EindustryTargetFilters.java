package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EindustryTargetFilters {

	//
	// SDE loading
	//

	public static final MapIntLoader<EindustryTargetFilters> LOADER = new MapIntLoader<>(
			"industryTargetFilters",
			EindustryTargetFilters.class);

	//
	// file structure
	//

	public List<Integer> categoryIDs,
			groupIDs;

	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}

}
