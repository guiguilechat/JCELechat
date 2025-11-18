package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position3D;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class Elandmarks {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "landmarks";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Elandmarks> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Elandmarks> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Elandmarks.class, Set.of("name"));

	public static final JacksonYamlLHMLoader<Elandmarks> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public HashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public long locationID;
	public HashMap<String, String> name = new LinkedHashMap<>();
	public Position3D position;

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
