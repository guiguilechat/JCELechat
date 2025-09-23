package fr.guiguilechat.jcelechat.model.sde2.parsers;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Eagents {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "agents";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<Eagents> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Eagents> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

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

	public static final JacksonYamlLHMLoader<Eagents> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int agentTypeID;
	public int corporationID;
	public int divisionID;
	public boolean isLocator;
	public int level;
	public int locationID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : corporation=" + first.corporationID + " level=" + first.level);
	}
}
