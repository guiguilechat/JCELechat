package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class Ebloodlines {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "bloodlines";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Ebloodlines> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Ebloodlines> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Ebloodlines.class, Set.of("description"));

	public static final JacksonYamlLHMLoader<Ebloodlines> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int charisma;
	public long corporationID;
	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public int intelligence;
	public int memory;
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();
	public int perception;
	public int raceID;
	public int willpower;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : corporation=" + first.corporationID + " name=" + first.enName());
	}

}
