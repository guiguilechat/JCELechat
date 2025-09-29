package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Orbiting;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EnpcStations extends Orbiting {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "npcStations";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EnpcStations> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EnpcStations> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EnpcStations.class, Set.of("ownerID"));

	public static final JacksonYamlLHMLoader<EnpcStations> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int operationID;
	public int ownerID;
	public BigDecimal reprocessingEfficiency;
	public int reprocessingHangarFlag;
	public BigDecimal reprocessingStationsTake;
	public boolean useOperationName;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : index=" + first.celestialIndex);
//		for (var e : loaded.entrySet()) {
//			if (e.getValue().celestialIndex == 0) {
//				System.err.println(e.getKey() + " has no celestialindex");
//			}
//		}
	}
}
