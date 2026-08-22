package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EschoolMap {

	//
	// SDE loading
	//

	public static final MapIntLoader<EschoolMap> LOADER = new MapIntLoader<>(
			"schoolMap",
			EschoolMap.class);

	//
	// file structure
	//

	public int schoolID;
	public int solarSystemID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : schoolID=" + first.schoolID);
	}

}
