package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlLHMLoader;

/**
 * an entry in the sde/fsd/planetSchematics.yaml
 */
public class EplanetSchematics {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/planetSchematics.yaml";

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EplanetSchematics>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlLHMLoader<Integer, EplanetSchematics> LOADER_SNAKEYAML = new OldSnakeYamlLHMLoader<>(
			SDE_FILE) {

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

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EplanetSchematics>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class SchemType {
		public boolean isInput;
		public int quantity;
	}

	public int cycleTime;
	public Map<String, String> nameID = new HashMap<>();
	public List<Integer> pins = new ArrayList<>();
	public Map<Integer, SchemType> types = new HashMap<>();

	public String enName() {
		return nameID.getOrDefault("en", "");
	}

}
