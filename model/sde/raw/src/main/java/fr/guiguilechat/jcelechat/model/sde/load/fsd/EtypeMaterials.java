package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlLHMLoader;

public class EtypeMaterials {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/typeMaterials.yaml";

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EtypeMaterials>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlLHMLoader<Integer, EtypeMaterials> LOADER_SNAKEYAML = new OldSnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("materials"::equals).findAny().isPresent()) {
						node.setType(EtypeMaterials.class);
					}
				}
			}
		}
	};

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, EtypeMaterials>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public static class Material {
		public int quantity;
		public int materialTypeID;

		@Override
		public String toString() {
			return "" + quantity + "×id:" + materialTypeID;
		}
	}

	public ArrayList<Material> materials = new ArrayList<>();

	@Override
	public String toString() {
		return materials.toString();
	}

}
