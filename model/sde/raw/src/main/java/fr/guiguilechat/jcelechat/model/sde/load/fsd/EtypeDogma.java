package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

public class EtypeDogma {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EtypeDogma.class);

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/typeDogma.yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EtypeDogma>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE);

	public static final SnakeYamlLHMLoader<Integer, EtypeDogma> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("dogmaAttributes"::equals).findAny().isPresent()) {
						node.setType(EtypeDogma.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EtypeDogma>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class EAttributes{

		public int attributeID;
		public Number value;

	}

	public static class Eeffects{

		public int effectID;
		public boolean isDefault;

	}

	public EAttributes[] dogmaAttributes;

	public Eeffects[] dogmaEffects;

	public static void main(String[] args) {
		System.out.println("loaded " + LOADER.load().size() + " types data");
	}

}
