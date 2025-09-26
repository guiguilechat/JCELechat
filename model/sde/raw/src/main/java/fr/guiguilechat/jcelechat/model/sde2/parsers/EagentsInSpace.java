package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EagentsInSpace {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "agentsInSpace";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EagentsInSpace> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EagentsInSpace> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EagentsInSpace.class, Set.of("typeID"));

	public static final JacksonYamlLHMLoader<EagentsInSpace> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int dungeonID;
	public int solarSystemID;
	public int spawnPointID;
	public int typeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : dungeonid=" + first.dungeonID + " solarsystem=" + first.solarSystemID);
	}

}
