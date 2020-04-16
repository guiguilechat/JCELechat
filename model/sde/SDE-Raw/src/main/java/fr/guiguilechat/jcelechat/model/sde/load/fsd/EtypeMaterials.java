package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class EtypeMaterials {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/typeMaterials.yaml");
	private static LinkedHashMap<Integer, EtypeMaterials> cache;

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

	@SuppressWarnings("unchecked")
	public static synchronized LinkedHashMap<Integer, EtypeMaterials> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(LinkedHashMap.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "materials".equals(s)).findAny().isPresent()) {
								node.setType(EtypeMaterials.class);
							}
						}
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = yaml.loadAs(SDECache.fileReader(FILE),
						LinkedHashMap.class);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

}
