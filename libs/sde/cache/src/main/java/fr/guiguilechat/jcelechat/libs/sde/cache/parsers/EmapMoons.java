package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.AttributesMoon;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InPlanetOrbit;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.StatisticsCelestial;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;
import lombok.ToString;

/**
 * moons' orbitid is actually planetId
 */
@ToString
public class EmapMoons extends InPlanetOrbit {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapMoons";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmapMoons> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmapMoons> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EmapMoons.class, Set.of("solarSystemID"));

	public static final JacksonYamlLHMLoader<EmapMoons> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public AttributesMoon attributes = new AttributesMoon();
	public List<Integer> npcStationIDs;
	public BigDecimal radius;
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
