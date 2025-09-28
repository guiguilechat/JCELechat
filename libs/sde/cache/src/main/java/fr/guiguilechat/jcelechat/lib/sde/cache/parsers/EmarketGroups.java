package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EmarketGroups {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "marketGroups";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmarketGroups> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmarketGroups> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EmarketGroups.class, Set.of("name"));

	public static final JacksonYamlLHMLoader<EmarketGroups> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public boolean hasTypes;
	public int iconID;
	public Map<String, String> name = new LinkedHashMap<>();
	public int parentGroupID;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}
}
