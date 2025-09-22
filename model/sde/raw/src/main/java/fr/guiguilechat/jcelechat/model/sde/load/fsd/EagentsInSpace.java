package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlLHMLoader;

/**
 * an entry in the fsd/agentsInSpace.yaml
 */
public class EagentsInSpace {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/agentsInSpace.yaml";

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EagentsInSpace>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlLHMLoader<Integer, EagentsInSpace> LOADER_SNAKEYAML = new OldSnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("typeID"::equals).findAny().isPresent()) {
						node.setType(EagentsInSpace.class);
					}
				}
			}
		}
	};

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EagentsInSpace>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int dungeonID;
	public int solarSystemID;
	public int spawnPointID;
	public int typeID;

	public static void main(String[] args) {
		System.err.println("loaded : " + LOADER.load().size());
		var first = LOADER.load().entrySet().iterator().next().getValue();
		System.err.println("first : dungeonid=" + first.dungeonID + " solarsystem=" + first.solarSystemID);
	}

}
