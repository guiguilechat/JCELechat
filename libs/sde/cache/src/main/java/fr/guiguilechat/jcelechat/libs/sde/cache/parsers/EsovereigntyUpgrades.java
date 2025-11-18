package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EsovereigntyUpgrades {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "sovereigntyUpgrades";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EsovereigntyUpgrades> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EsovereigntyUpgrades> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML,
			EsovereigntyUpgrades.class, Set.of("mutually_exclusive_group"));

	public static final JacksonYamlLHMLoader<EsovereigntyUpgrades> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Fuel {
		public int hourly_upkeep;
		public int startup_cost;
		public int type_id;

	}

	public Fuel fuel;

	public String mutually_exclusive_group;
	public int power_allocation;
	public int power_production;
	public int workforce_allocation;
	public int workforce_production;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : mutually_exclusive_group=" + first.mutually_exclusive_group);
	}
}
