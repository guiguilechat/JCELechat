package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

public class Eancestries {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/ancestries.yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eancestries>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE);

	public static final SnakeYamlLHMLoader<Integer, Eancestries> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("shortDescription"::equals).findAny().isPresent()) {
						node.setType(Eancestries.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eancestries>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int bloodlineID;
	public int charisma;
	public LinkedHashMap<String, String> descriptionID = new LinkedHashMap<>();
	public int iconID;
	public int intelligence;
	public int memory;
	public LinkedHashMap<String, String> nameID = new LinkedHashMap<>();
	public int perception;
	public String shortDescription;
	public int willpower;

	public String enDescription() {
		return descriptionID == null ? null : descriptionID.get("en");
	}

	public String enName() {
		return nameID == null ? null : nameID.get("en");
	}

	public static void main(String[] args) {
		System.err.println("loaded : " + LOADER.load().size());
		System.err.println("first bloodine is : " + LOADER.load().entrySet().iterator().next().getValue().bloodlineID);
	}

}
