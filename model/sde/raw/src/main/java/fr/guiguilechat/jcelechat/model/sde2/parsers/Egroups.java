package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Egroups {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "groups";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Egroups>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, Egroups> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("published"::equals).findAny().isPresent()) {
						node.setType(Egroups.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Egroups>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	/** key is language short, like "en" */
	public HashMap<String, String> name = new HashMap<>();
	public boolean published;
	public boolean anchorable;
	public boolean anchored;
	public int categoryID;
	public boolean fittableNonSingleton;
	public boolean useBasePrice;
	public int iconID;

	public String enName() {
		return name == null || !name.containsKey("en") ? null : name.get("en");
	}

	public static void main(String[] args) {
		var data = LOADER.load();
		System.err.println("loaded : " + data.size());
		var first = data.entrySet().iterator().next().getValue();
		System.err.println(
				"first : name=" + first.enName() + " published=" + first.published);
	}

}
