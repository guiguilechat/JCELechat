package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EskinMaterials {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "skinMaterials";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EskinMaterials> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EskinMaterials> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EskinMaterials.class, Set.of("materialSetID"));

	public static final JacksonYamlLHMLoader<EskinMaterials> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public HashMap<String, String> displayName = new LinkedHashMap<>();
	public int materialSetID;

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : displayName=" + first.enDisplayName());
	}
}
