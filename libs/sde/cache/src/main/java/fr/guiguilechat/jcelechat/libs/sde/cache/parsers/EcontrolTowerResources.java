package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EcontrolTowerResources {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "controlTowerResources";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EcontrolTowerResources> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EcontrolTowerResources> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML,
			EcontrolTowerResources.class, Set.of("resources"));

	public static final JacksonYamlLHMLoader<EcontrolTowerResources> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Resource {
		public int factionID;
		public BigDecimal minSecurityLevel;
		public int purpose;
		public int quantity;
		public int resourceTypeID;
	}

	public List<Resource> resources = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : resources=" + first.resources.size());
	}
}
