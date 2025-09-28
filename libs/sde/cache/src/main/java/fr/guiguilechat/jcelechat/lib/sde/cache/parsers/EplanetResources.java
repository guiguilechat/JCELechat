package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EplanetResources {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "planetResources";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EplanetResources> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EplanetResources> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EplanetResources.class, Set.of("power"), Set.of("workforce"), Set.of("cycle_minutes"));

	public static final JacksonYamlLHMLoader<EplanetResources> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int cycle_minutes;
	public int harvest_silo_max;
	public int maturation_cycle_minutes;
	public int maturation_percent;
	public int mature_silo_max;
	public int power;
	public int reagent_harvest_amount;
	public int reagent_type_id;
	public int workforce;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : power=" + first.power);
	}
}
