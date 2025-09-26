package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.parsers.inspace.OrbitingCelestial;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmapPlanets extends OrbitingCelestial {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapPlanets";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmapPlanets> LOADER_JACKSON_YAML = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmapPlanets> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EmapPlanets.class, Set.of("solarSystemID"));

	public static final JacksonYamlLHMLoader<EmapPlanets> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public List<Integer> asteroidBeltIDs;
	public List<Integer> moonIDs;
	public List<Integer> npcStationIDs;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : solarSystemID=" + first.solarSystemID + " typeID=" + first.typeID);
		for (var e : loaded.entrySet()) {
			if (e.getValue().celestialIndex == 0) {
				System.err.println(e.getKey() + " has no celestialindex");
			}
		}
	}

}
