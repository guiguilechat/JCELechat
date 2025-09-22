package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

/**
 * an entry in the fsd/agents.yaml
 */
public class Eagents {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/agents.yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eagents>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE);

	public static final SnakeYamlLHMLoader<Integer, Eagents> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("agentTypeID"::equals).findAny().isPresent()) {
						node.setType(Eagents.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eagents>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int agentTypeID;
	public int corporationID;
	public int divisionID;
	public boolean isLocator;
	public int level;
	public int locationID;

	public static void main(String[] args) {
		System.err.println("loaded : " + LOADER.load().size());
		var first = LOADER.load().entrySet().iterator().next().getValue();
		System.err.println("first : corporation=" + first.corporationID + " level=" + first.level);
	}
}
