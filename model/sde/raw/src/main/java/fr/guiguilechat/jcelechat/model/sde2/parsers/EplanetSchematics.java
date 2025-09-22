package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

/**
 * an entry in the sde/fsd/planetSchematics.yaml
 */
public class EplanetSchematics {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "planetSchematics";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EplanetSchematics>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EplanetSchematics> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("cycleTime"::equals).findAny().isPresent()) {
						node.setType(EplanetSchematics.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EplanetSchematics>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class SchemType {
		public boolean isInput;
		public int quantity;
	}

	public int cycleTime;
	public Map<String, String> name = new HashMap<>();
	public List<Integer> pins = new ArrayList<>();
	public Map<Integer, SchemType> types = new HashMap<>();

	public String enName() {
		return name.getOrDefault("en", "");
	}

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println("first : cycletime=" + first.cycleTime + " name=" + first.enName());
	}

}
