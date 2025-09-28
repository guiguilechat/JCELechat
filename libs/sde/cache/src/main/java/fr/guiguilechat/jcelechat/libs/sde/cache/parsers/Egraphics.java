package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class Egraphics {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "graphics";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Egraphics> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Egraphics> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Egraphics.class, Set.of("name"));

	public static final JacksonYamlLHMLoader<Egraphics> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public String graphicFile;
	public String iconFolder;
	public String sofFactionName;
	public String sofHullName;
	public String sofRaceName;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		loaded.entrySet().iterator().next().getValue();
	}
}
