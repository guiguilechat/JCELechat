package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Eicons {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "icons";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Eicons> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Eicons> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Eicons.class, Set.of("iconFile"));

	public static final JacksonYamlLHMLoader<Eicons> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public String iconFile;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.iconFile);
	}
}
