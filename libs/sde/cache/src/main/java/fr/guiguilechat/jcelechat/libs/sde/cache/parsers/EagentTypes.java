package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EagentTypes {

	//
	// SDE loading
	//

	public static final MapIntLoader<EagentTypes> LOADER = new MapIntLoader<>(
			"agentTypes",
			EagentTypes.class);

	//
	// file structure
	//

	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}
}
