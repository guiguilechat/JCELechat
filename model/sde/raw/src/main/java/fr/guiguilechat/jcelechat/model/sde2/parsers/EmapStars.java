package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EmapStars {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "mapStars";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapStars>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EmapStars> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("solarSystemID"::equals).findAny().isPresent()) {
						node.setType(EmapStars.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EmapStars>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public long radius;
	public int solarSystemID;

	public static class StarStatistics {
		public BigDecimal age;
		public BigDecimal life;
		public BigDecimal luminosity;
		public String spectralClass;
		public BigDecimal temperature;

	}

	public StarStatistics statistics;
	public int typeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println("first : solarSystemId=" + first.solarSystemID + " typeID=" + first.typeID);
	}
}
