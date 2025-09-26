package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde2.parsers.inspace.Orbiting;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EnpcStations extends Orbiting {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "npcStations";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EnpcStations> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EnpcStations> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EnpcStations.class, Set.of("ownerID"), Set.of("operationID"));

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
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : index=" + first.celestialIndex);
//		for (var e : loaded.entrySet()) {
//			if (e.getValue().celestialIndex == 0) {
//				System.err.println(e.getKey() + " has no celestialindex");
//			}
//		}
	}
}
