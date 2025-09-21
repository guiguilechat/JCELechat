package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JackonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

/**
 * an entry in the fsd/agents.yaml
 */
public class Eagents {

	public static final JackonYamlLoader<LinkedHashMap<Integer, Eagents>> LOADER
	// = new JackonYamlLoader<>("fsd/agents.yaml");
			= new SnakeYamlLHMLoader<>("fsd/agents.yaml") {

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

	// structure

	public int agentTypeID;
	public int corporationID;
	public int divisionID;
	public boolean isLocator;
	public int level;
	public int locationID;
}
