package fr.guiguilechat.jcelechat.model.sde2.parsers;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.parsers.inspace.Orbiting;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmapAsteroidBelts extends Orbiting {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapAsteroidBelts";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmapAsteroidBelts> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmapAsteroidBelts> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("solarSystemID"::equals).findAny().isPresent()) {
						node.setType(EmapAsteroidBelts.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLHMLoader<EmapAsteroidBelts> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	// nothing, it's just a celestial

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : solarSystemId=" + first.solarSystemID + " typeID=" + first.typeID);
		for (var e : loaded.entrySet()) {
			if (e.getValue().celestialIndex == 0) {
				System.err.println(e.getKey() + " has no celestialindex");
			}
		}
	}
}
