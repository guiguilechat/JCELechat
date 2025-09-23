package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EcontrolTowerResources {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "controlTowerResources";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EcontrolTowerResources>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EcontrolTowerResources> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("resources"::equals).findAny().isPresent()) {
						node.setType(EcontrolTowerResources.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EcontrolTowerResources>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Resource {
		public int factionID;
		public BigDecimal minSecurityLevel;
		public int purpose;
		public int quantity;
		public int resourceTypeID;
	}

	public List<Resource> resources = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println("first : resources=" + first.resources.size());
	}
}
