package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.HashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EstationServices {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "stationServices";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EstationServices> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EstationServices> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EstationServices.class, Set.of("serviceName"));

	public static final JacksonYamlLHMLoader<EstationServices> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public HashMap<String, String> description = new HashMap<>();
	public HashMap<String, String> serviceName = new HashMap<>();

	//

	public String enName() {
		return serviceName == null ? null : serviceName.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.getOrDefault("en", "no description " + description);
	}

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " description=" + first.enDescription());
	}

}
