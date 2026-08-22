package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class Egraphics {

	//
	// SDE loading
	//

	public static final MapIntLoader<Egraphics> LOADER = new MapIntLoader<>(
			"graphics",
			Egraphics.class);

	//
	// file structure
	//

	public String graphicFile;
	public String iconFolder;
	public String sofFactionName;
	public String sofHullName;
	public List<String> sofLayout;
	public String sofMaterialSetID;
	public String sofRaceName;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		loaded.entrySet().iterator().next().getValue();
	}
}
