package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class EinvTypeMaterials {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/invTypeMaterials.yaml");
	private static ArrayList<EinvTypeMaterials> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EinvTypeMaterials> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EinvTypeMaterials.class);
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = yaml.loadAs(new FileReader(FILE), ArrayList.class);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	public static Map<Integer, Map<Integer, Integer>> loadByTypeIdTypeId() {
		HashMap<Integer, Map<Integer, Integer>> ret = new HashMap<>();
		for (EinvTypeMaterials e : load()) {
			Map<Integer, Integer> store = ret.get(e.typeID);
			if (store == null) {
				store = new HashMap<>();
				ret.put(e.typeID, store);
			}
			store.put(e.materialTypeID, store.getOrDefault(e.materialTypeID, 0) + e.quantity);
		}
		return ret;
	}

	public int materialTypeID, quantity, typeID;

}
