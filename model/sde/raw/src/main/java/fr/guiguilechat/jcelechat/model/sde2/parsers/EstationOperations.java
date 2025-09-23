package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EstationOperations {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "stationOperations";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EstationOperations>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EstationOperations> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

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

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EstationOperations>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int activityID;
	public double border;
	public double corridor;
	public HashMap<String, String> description = new HashMap<>();
	public double fringe;
	public double hub;
	public double manufacturingFactor;
	public HashMap<String, String> operationName = new HashMap<>();
	public double ratio;
	public double researchFactor;
	public int[] services;
	public HashMap<Integer, Integer> stationTypes = new HashMap<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enOperationName() {
		return operationName == null ? null : operationName.get("en");
	}

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err
				.println("first : operationName=" + first.enOperationName() + " description=" + first.enDescription());
	}

}
