package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EresearchAgents {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "researchAgents";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EresearchAgents>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EresearchAgents> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("skills"::equals).findAny().isPresent()) {
						node.setType(EresearchAgents.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EresearchAgents>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class TypeID {
		public int typeID;
	}
	public List<TypeID> skills = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : skills=" + first.skills.size());
	}
}
