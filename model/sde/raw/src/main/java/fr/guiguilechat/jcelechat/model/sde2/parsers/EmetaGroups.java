package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.HashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmetaGroups {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "metaGroups";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EmetaGroups> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EmetaGroups> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("name"::equals).findAny().isPresent()) {
						node.setType(EmetaGroups.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLHMLoader<EmetaGroups> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class ColorRGB {
		public BigDecimal b, g, r;
	}

	public ColorRGB color;
	public HashMap<String, String> description = new HashMap<>();
	public int iconID;
	public String iconSuffix;
	public HashMap<String, String> name = new HashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}
}
