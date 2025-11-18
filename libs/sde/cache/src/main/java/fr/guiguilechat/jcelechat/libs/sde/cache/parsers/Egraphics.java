package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Egraphics {

	//
	// SDE loading
	//

	public static final IntMapLoader<Egraphics> LOADER = new IntMapLoader<>(
			"graphics",
			Egraphics.class,
			Set.of("graphicFile"),
			Set.of("iconFolder"),
			Set.of("sofHullName"));

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
