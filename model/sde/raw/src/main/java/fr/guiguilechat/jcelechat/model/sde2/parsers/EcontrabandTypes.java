package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EcontrabandTypes {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "contrabandTypes";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EcontrabandTypes>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EcontrabandTypes> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("factions"::equals).findAny().isPresent()) {
						node.setType(EcontrabandTypes.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EcontrabandTypes>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class FactionContraband {
		public BigDecimal attackMinSec;
		public BigDecimal confiscateMinSec;
		public BigDecimal fineByValue;
		public BigDecimal standingLoss;
	}

	public Map<Integer, FactionContraband> factions = new LinkedHashMap<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println("first : factions=" + first.factions.size());
	}
}
