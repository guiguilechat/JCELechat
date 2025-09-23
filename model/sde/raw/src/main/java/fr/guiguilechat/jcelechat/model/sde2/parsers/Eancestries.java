package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Eancestries {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "ancestries";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eancestries>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, Eancestries> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

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
	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public int intelligence;
	public int memory;
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();
	public int perception;
	public String shortDescription;
	public int willpower;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println("first : bloodine=" + first.bloodlineID + " name=" + first.enName());
	}

}
