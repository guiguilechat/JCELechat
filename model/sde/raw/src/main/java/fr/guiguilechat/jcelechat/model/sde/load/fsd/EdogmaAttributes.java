package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

/**
 *
 */
public class EdogmaAttributes {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/dogmaAttributes.yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EdogmaAttributes>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE);

	public static final SnakeYamlLHMLoader<Integer, EdogmaAttributes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("attributeID"::equals).findAny().isPresent()) {
						node.setType(EdogmaAttributes.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EdogmaAttributes>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int attributeID;
	public int categoryID;
	public int chargeRechargeTimeID;
	public int dataType;
	public float defaultValue;
	public String description;
	public Map<String, String> displayNameID = new HashMap<>();
	public boolean displayWhenZero;
	public boolean highIsGood;
	public int iconID;
	public int maxAttributeID;
	public int minAttributeID;
	public String name;
	public boolean published;
	public boolean stackable;
	public Map<String, String> tooltipDescriptionID = new HashMap<>();
	public Map<String, String> tooltipTitleID = new HashMap<>();
	public Integer unitID;

	public static void main(String[] args) {
		System.out.println("loaded " + LOADER.load().size() + " attributes");
	}

}
