package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmapConstellations extends InSpace {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapConstellations";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapConstellations>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EmapConstellations> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("regionID"::equals).findAny().isPresent()) {
						node.setType(EmapConstellations.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapConstellations>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int factionID;
	public int regionID;
	public List<Integer> solarSystemIDs = new ArrayList<>();
	public int wormholeClassID;

	//

	public static void main(String[] args) {
		var data = LOADER.load();
		System.err.println("loaded : " + data.size());
		long withname = data.values().stream().filter(m -> m.name != null).count();
		System.err.println("named=" + withname);
		var first = data.entrySet().iterator().next().getValue();
		System.err.println(
				"first : regionID=" + first.regionID + " name=" + first.enName() + " position=" + first.position);
	}
}
