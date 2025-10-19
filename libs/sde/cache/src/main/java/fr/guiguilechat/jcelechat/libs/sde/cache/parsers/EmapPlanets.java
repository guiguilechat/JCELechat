package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.AttributesPlanet;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InStarOrbit;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.StatisticsCelestial;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;
import lombok.ToString;

@ToString
public class EmapPlanets extends InStarOrbit {

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
	public AttributesPlanet attributes = new AttributesPlanet();
	public List<Integer> moonIDs;
	public List<Integer> npcStationIDs;
	public long radius;
	public StatisticsCelestial statistics = new StatisticsCelestial();

	public LinkedHashMap<String, String> uniqueName;

	public String enUniqueName() {
		return uniqueName == null ? null : uniqueName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.uniqueName != null).count();
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
