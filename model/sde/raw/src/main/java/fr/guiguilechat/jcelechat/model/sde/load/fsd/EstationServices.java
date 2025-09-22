package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlLHMLoader;

public class EstationServices {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/stationServices.yaml";

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EstationServices>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlLHMLoader<Integer, EstationServices> LOADER_SNAKEYAML = new OldSnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("serviceNameID"::equals).findAny().isPresent()) {
						node.setType(EstationServices.class);
					}
				}
			}
		}
	};

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EstationServices>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public HashMap<String, String> descriptionID = new HashMap<>();
	public HashMap<String, String> serviceNameID = new HashMap<>();

	//

	public String enName() {
		return serviceNameID.getOrDefault("en", "unknownservice" + serviceNameID);
	}


}
