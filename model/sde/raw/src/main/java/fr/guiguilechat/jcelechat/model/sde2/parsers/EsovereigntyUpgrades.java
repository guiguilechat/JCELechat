package fr.guiguilechat.jcelechat.model.sde2.parsers;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EsovereigntyUpgrades {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "sovereigntyUpgrades";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EsovereigntyUpgrades> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EsovereigntyUpgrades> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("mutually_exclusive_group"::equals).findAny().isPresent()) {
						node.setType(EsovereigntyUpgrades.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLHMLoader<EsovereigntyUpgrades> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int fuel_hourly_upkeep;
	public int fuel_startup_cost;
	public int fuel_type_id;
	public String mutually_exclusive_group;
	public int power_allocation;
	public int workforce_allocation;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : mutually_exclusive_group=" + first.mutually_exclusive_group);
	}
}
