package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EindustryActivities {

	//
	// SDE loading
	//

	public static final MapIntLoader<EindustryActivities> LOADER = new MapIntLoader<>(
			"industryActivities",
			EindustryActivities.class);

	//
	// file structure
	//

	public String description;
	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : description=" + first.description + " name=" + first.name);
	}

}
