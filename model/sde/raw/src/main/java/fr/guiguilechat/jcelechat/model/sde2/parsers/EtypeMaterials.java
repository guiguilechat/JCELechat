package fr.guiguilechat.jcelechat.model.sde2.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.SnakeYamlLHMLoader;

public class EtypeMaterials {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "typeMaterials";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EtypeMaterials>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<Integer, EtypeMaterials> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML) {

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

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EtypeMaterials>> LOADER = LOADER_SNAKEYAML;

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

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.err.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.err.println(
				"first : materials=" + first.materials.size());
	}

}
