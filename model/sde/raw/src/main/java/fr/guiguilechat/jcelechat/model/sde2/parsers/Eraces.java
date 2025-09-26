package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Eraces {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "races";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Eraces> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Eraces> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Eraces.class, Set.of("name"));

	public static final JacksonYamlLHMLoader<Eraces> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//
	public HashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public HashMap<String, String> name = new LinkedHashMap<>();
	public int shipTypeID;
	public Map<Integer, Integer> skills = new LinkedHashMap<>();

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
