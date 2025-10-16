package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EnpcCorporationDivisions {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "npcCorporationDivisions";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EnpcCorporationDivisions> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EnpcCorporationDivisions> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML,
			EnpcCorporationDivisions.class, Set.of("leaderTypeName"));

	public static final JacksonYamlLHMLoader<EnpcCorporationDivisions> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public HashMap<String, String> description = new HashMap<>();
	public String displayName;
	public String internalName;
	public HashMap<String, String> leaderTypeName = new HashMap<>();
	public HashMap<String, String> name = new HashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.displayName);
	}
}
