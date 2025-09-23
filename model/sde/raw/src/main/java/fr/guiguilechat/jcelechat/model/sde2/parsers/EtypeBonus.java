package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EtypeBonus {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "typeBonus";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EtypeBonus> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EtypeBonus> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("roleBonuses"::equals).findAny().isPresent()) {
						node.setType(EtypeBonus.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLHMLoader<EtypeBonus> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Bonus {
		public BigDecimal bonus;
		public LinkedHashMap<String, String> bonusText = new LinkedHashMap<>();
		public int importance;
		public int unitID;
	}

	public List<Bonus> roleBonuses = new ArrayList<>();
	public Map<Integer, Bonus[]> types = new LinkedHashMap<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : rolebonuses=" + first.roleBonuses.size() + " types=" + first.types.size());
	}
}
