package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EdbuffCollections {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "dbuffCollections";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EdbuffCollections> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EdbuffCollections> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("operationName"::equals).findAny().isPresent()) {
						node.setType(EdbuffCollections.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLHMLoader<EdbuffCollections> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public String aggregateMode;
	public String developerDescription;
	public Map<String, String> displayName = new LinkedHashMap<>();

	public static class ItemModifier {
		public int dogmaAttributeID;
	}
	public List<ItemModifier> itemModifiers = new ArrayList<>();

	public static class LocationGroupModifiers {
		public int dogmaAttributeID;
		public int groupID;
	}
	public ArrayList<LocationGroupModifiers> locationGroupModifiers = new ArrayList<>();

	public List<ItemModifier> locationModifiers = new ArrayList<>();

	public static class LocationRequiredSkillModifier {
		public int dogmaAttributeID;
		public int skillID;
	}
	public List<LocationRequiredSkillModifier> locationRequiredSkillModifiers = new ArrayList<>();

	public String operationName;
	public String showOutputValueInUI;

	public String enDisplayName() {
		return displayName == null ? null : displayName.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : itemodifiers=" + first.itemModifiers.size() + " locationgroupmodifiers="
				+ first.locationGroupModifiers.size());
	}
}
