package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

/**
 *
 */
public class EdogmaAttributes {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "dogmaAttributes";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EdogmaAttributes> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EdogmaAttributes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

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

	public static final JacksonYamlLHMLoader<EdogmaAttributes> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int attributeID;
	public int categoryID;
	public int chargeRechargeTimeID;
	public int dataType;
	public BigDecimal defaultValue;
	public String description;
	public Map<String, String> displayName = new LinkedHashMap<>();
	public boolean displayWhenZero;
	public boolean highIsGood;
	public int iconID;
	public int maxAttributeID;
	public int minAttributeID;
	public String name;
	public boolean published;
	public boolean stackable;
	public Map<String, String> tooltipDescription = new LinkedHashMap<>();
	public Map<String, String> tooltipTitle = new LinkedHashMap<>();
	public Integer unitID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : name=" + first.name + " published=" + first.published);
	}

}
