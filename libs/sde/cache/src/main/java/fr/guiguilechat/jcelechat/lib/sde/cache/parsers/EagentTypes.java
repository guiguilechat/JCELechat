package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EagentTypes {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "agentTypes";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EagentTypes> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EagentTypes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EagentTypes.class, Set.of("name"));

	public static final JacksonYamlLHMLoader<EagentTypes> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}
}
