package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

@SuppressWarnings("serial")
public class Emasteries extends HashMap<Integer, Integer[]> {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "masteries";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Emasteries>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, Emasteries> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("0"::equals).findAny().isPresent()) {
						node.setType(Emasteries.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Emasteries>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	// nothing, it's a map ! I have no fucking idea what it represents. And I don't
	// care.

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : size=" + first.size());
	}
}
