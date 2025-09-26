package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.HashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

@SuppressWarnings("serial")
public class Emasteries extends HashMap<Integer, Integer[]> {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "masteries";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Emasteries> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Emasteries> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Emasteries.class, Set.of("0"));

	public static final JacksonYamlLHMLoader<Emasteries> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	// nothing, it's a map ! I have no fucking idea what it represents. And I don't
	// care.

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : size=" + first.size());
	}
}
