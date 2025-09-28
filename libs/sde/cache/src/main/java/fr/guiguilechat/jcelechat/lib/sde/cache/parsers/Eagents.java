package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class Eagents {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "agents";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Eagents> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Eagents> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			Eagents.class, Set.of("agentTypeID"));

	public static final JacksonYamlLHMLoader<Eagents> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int agentTypeID;
	public int corporationID;
	public int divisionID;
	public boolean isLocator;
	public int level;
	public int locationID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : corporation=" + first.corporationID + " level=" + first.level);
	}
}
