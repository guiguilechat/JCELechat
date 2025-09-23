package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class Etypes {

	private static final Logger logger = LoggerFactory.getLogger(Etypes.class);

	//
	// SDE loading
	//

	public static final String SDE_FILE = "types";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Etypes>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, Etypes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML) {

		@Override
		protected void preprocess(Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("groupID"::equals).findAny().isPresent()) {
						node.setType(Etypes.class);
					}

				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Etypes>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public BigDecimal basePrice;
	public BigDecimal capacity;
	/** key is language short, like "en" */
	public HashMap<String, String> description = new HashMap<>();
	public int factionID;
	public int graphicID;
	public int groupID;
	public int iconID;
	public int marketGroupID;
	public BigDecimal mass;
	public int metaGroupID;
	/** key is language short, like "en" */
	public HashMap<String, String> name = new HashMap<>();
	public int portionSize;
	public boolean published;
	public int raceID;
	public BigDecimal radius;
	public int soundID;
	public Integer variationParentTypeID;
	public BigDecimal volume;


	public String enName() {
		return name == null ? null : name.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	//

	private static final HashMap<Integer, String> ERROR_NAMES = new HashMap<>();

	public static String getName(int typeId) {
		var type = LOADER.load().get(typeId);
		if (type != null) {
			return type.enName();
		}
		synchronized (ERROR_NAMES) {
			return ERROR_NAMES.computeIfAbsent(typeId, i -> {
				logger.warn("can't load type id " + i, new Exception());
				return "missingType_" + i;
			});
		}
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println("first : name=" + first.enName() + " description=" + first.enDescription());
	}

}
