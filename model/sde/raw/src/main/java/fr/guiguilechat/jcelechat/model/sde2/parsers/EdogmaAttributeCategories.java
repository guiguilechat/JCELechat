package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EdogmaAttributeCategories {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "dogmaAttributeCategories";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EdogmaAttributeCategories> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EdogmaAttributeCategories> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EdogmaAttributeCategories.class, Set.of("name"));

	public static final JacksonYamlLHMLoader<EdogmaAttributeCategories> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public String description;
	public String name;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.name);
	}
}
