package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

/**
 * an entry in the bsd/dgmTypeAttributes.yaml file
 *
 * @deprecated was moved to fsd
 */
@Deprecated
public class EdgmTypeAttributes {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/dgmTypeAttributes.yaml");
	private static ArrayList<EdgmTypeAttributes> cache;
	public int attributeID;
	public int typeID;
	public int valueInt;
	public float valueFloat;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EdgmTypeAttributes> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EdgmTypeAttributes.class);
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

	public static LinkedHashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> loadByTypeIDAttributeID() {
		LinkedHashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> ret = new LinkedHashMap<>();
		for (EdgmTypeAttributes e : load()) {
			HashMap<Integer, EdgmTypeAttributes> m = ret.get(e.typeID);
			if (m == null) {
				m = new HashMap<>();
				ret.put(e.typeID, m);
			}
			m.put(e.attributeID, e);
		}
		return ret;
	}

	@Override
	public String toString() {
		return "type" + typeID + ".att" + attributeID + "=" + (valueFloat != 0 ? valueFloat : valueInt);
	}
}
