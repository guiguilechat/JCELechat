package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmapStargates extends InSpace {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapStargates";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapStargates>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EmapStargates> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("position"::equals).findAny().isPresent()) {
						node.setType(EmapStargates.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapStargates>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Destination {
		public int solarSystemID;
		public int stargateID;
	}

	public Destination destination;
	public int solarSystemID;
	public int typeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.err.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println(
				"first : solarSystemID=" + first.solarSystemID + " target=" + first.destination.stargateID);
	}
}
