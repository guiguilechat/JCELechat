package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EplanetResources {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "planetResources";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EplanetResources> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EplanetResources> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EplanetResources.class,
			Set.of("power"),
			Set.of("workforce"),
			Set.of("reagent")
			);

	public static final JacksonYamlLHMLoader<EplanetResources> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int power;

	public static class Reagent {
		public int amount_per_cycle;
		public int cycle_period;
		public int secured_capacity;
		public int type_id;
		public int unsecured_capacity;

	}
	public Reagent reagent;
	public int workforce;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : power=" + first.power);
	}
}
