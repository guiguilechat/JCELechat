package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

public class Ecategories {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/categories.yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Ecategories>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE);

	public static final SnakeYamlLHMLoader<Integer, Ecategories> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("published"::equals).findAny().isPresent()) {
						node.setType(Ecategories.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Ecategories>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	/** key is language short, like "en" */
	public HashMap<String, String> name = new HashMap<>();
	public boolean published;
	public int iconID;

	public String enName() {
		return name == null ? null : name.get("en");
	}

}
