package fr.guiguilechat.eveonline.sde.bsd;

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

import fr.guiguilechat.eveonline.sde.cache.SDECache;

/**
 * an entry in the bsd/dgmTypeAttributes.yaml file
 */
public class EdgmTypeAttributes {

	public static final File FILE = new File(SDECache.CACHEDIR, "sde/bsd/dgmTypeAttributes.yaml");
	public int attributeID;
	public int typeID;
	public int valueInt;
	public float valueFloat;

	@SuppressWarnings("unchecked")
	public static ArrayList<EdgmTypeAttributes> load() {
		SDECache.donwloadSDE();
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
			return yaml.loadAs(new FileReader(FILE), ArrayList.class);
		} catch (FileNotFoundException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
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
}
