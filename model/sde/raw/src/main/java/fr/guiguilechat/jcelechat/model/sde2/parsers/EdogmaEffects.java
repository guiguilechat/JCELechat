package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EdogmaEffects {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "dogmaEffects";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EdogmaEffects>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EdogmaEffects> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("effectCategory"::equals).findAny().isPresent()) {
						node.setType(EdogmaEffects.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EdogmaEffects>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public boolean disallowAutoRepeat;
	public Map<String, String> displayName = new LinkedHashMap<>();
	public int dischargeAttributeID;
	public int distribution;
	public int durationAttributeID;
	public int effectCategory; // replace with categoryID later
	public int effectID;// remove later
	public String effectName;
	public boolean electronicChance;
	public int falloffAttributeID;
	public int fittingUsageChanceAttributeID;
	public String guid;
	public int iconID;
	public boolean isAssistance;
	public boolean isOffensive;
	public boolean isWarpSafe;

	public static class ModifierInfo {
		public String domain;
		public int effectID;
		public String func;
		public int groupID;
		public int modifiedAttributeID;
		public int modifyingAttributeID;
		public int operation;
		public int skillTypeID;
	}
	public List<ModifierInfo> modifierInfo = new ArrayList<>();

	public int npcActivationChanceAttributeID;
	public int npcUsageChanceAttributeID;
	public boolean propulsionChance;
	public boolean published;
	public int rangeAttributeID;
	public boolean rangeChance;
	public int resistanceAttributeID;
	public int trackingSpeedAttributeID;
	public String sfxName; // remove later

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.effectName);
	}
}
