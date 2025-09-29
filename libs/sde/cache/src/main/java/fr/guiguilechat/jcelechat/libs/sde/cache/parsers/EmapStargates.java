package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EmapStargates extends InSpace {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapStargates";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmapStargates> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmapStargates> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EmapStargates.class, Set.of("position"));

	public static final JacksonYamlLHMLoader<EmapStargates> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Destination {
		public int solarSystemID;
		public int stargateID;
	}

	public Destination destination;
	public int solarSystemID;
	public int typeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : solarSystemID=" + first.solarSystemID + " target=" + first.destination.stargateID);
	}
}
