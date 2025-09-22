package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Ebloodlines {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "bloodlines";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Ebloodlines>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, Ebloodlines> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("description"::equals).findAny().isPresent()) {
						node.setType(Ebloodlines.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Ebloodlines>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int charisma;
	public long corporationID;
	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public int intelligence;
	public int memory;
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();
	public int perception;
	public int raceID;
	public int willpower;

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	public static void main(String[] args) {
		System.err.println("loaded : " + LOADER.load().size());
		var first = LOADER.load().entrySet().iterator().next().getValue();
		System.err.println(
				"first : corporation=" + first.corporationID + " name=" + first.enName());
	}

}
