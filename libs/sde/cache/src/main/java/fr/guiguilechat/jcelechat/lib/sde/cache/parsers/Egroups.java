package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class Egroups {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "groups";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Egroups> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Egroups> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Egroups.class, Set.of("published"));

	public static final JacksonYamlLHMLoader<Egroups> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public HashMap<String, String> name = new LinkedHashMap<>();
	public boolean published;
	public boolean anchorable;
	public boolean anchored;
	public int categoryID;
	public boolean fittableNonSingleton;
	public boolean useBasePrice;
	public int iconID;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : name=" + first.enName() + " published=" + first.published);
	}

}
