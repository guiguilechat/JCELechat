package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlLHMLoader;

public class EstationOperations {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/stationOperations.yaml";

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EstationOperations>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlLHMLoader<Integer, EstationOperations> LOADER_SNAKEYAML = new OldSnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("activityID"::equals).findAny().isPresent()) {
						node.setType(EstationOperations.class);
					}
				}
			}
		}
	};

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EstationOperations>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int activityID;
	public double border;
	public double corridor;
	public HashMap<String, String> descriptionID = new HashMap<>();
	public double fringe;
	public double hub;
	public double manufacturingFactor;
	public HashMap<String, String> operationNameID = new HashMap<>();
	public double ratio;
	public double researchFactor;
	public int[] services;
	public HashMap<Integer, Integer> stationTypes = new HashMap<>();


}
