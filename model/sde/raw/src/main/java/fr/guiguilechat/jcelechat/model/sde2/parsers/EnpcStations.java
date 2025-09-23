package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.parsers.inspace.Orbiting;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EnpcStations extends Orbiting {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "npcStations";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EnpcStations>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EnpcStations> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("ownerID"::equals).findAny().isPresent()) {
						node.setType(EnpcStations.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EnpcStations>> LOADER = LOADER_SNAKEYAML;

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
		for (var e : loaded.entrySet()) {
			if (e.getValue().celestialIndex == 0) {
				System.err.println(e.getKey() + " has no celestialindex");
			}
		}
	}
}
