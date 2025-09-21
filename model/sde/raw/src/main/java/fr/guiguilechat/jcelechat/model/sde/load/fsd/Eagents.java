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

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eagents>> LOADER_JACKSON = new JacksonYamlLoader<>(
			"fsd/agents.yaml");

	public static final SnakeYamlLHMLoader<Integer, Eagents> LOADER_SNAKEYAML
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

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eagents>> LOADER = LOADER_SNAKEYAML;

	// structure

	public int agentTypeID;
	public int corporationID;
	public int divisionID;
	public boolean isLocator;
	public int level;
	public int locationID;
}
