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
 * an entry in the bsd/dgmTypeEffects.yaml file
 */
public class EdgmTypeEffects {

	public static final File FILE = new File(SDECache.CACHEDIR, "sde/bsd/dgmTypeEffects.yaml");
	public int effectID;
	public boolean isDefault;
	public int typeID;

	@SuppressWarnings("unchecked")
	public static ArrayList<EdgmTypeEffects> load() {
		SDECache.donwloadSDE();
		Constructor cons = new Constructor(ArrayList.class) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					node.setType(EdgmTypeEffects.class);
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

	public static LinkedHashMap<Integer, HashMap<Integer, EdgmTypeEffects>> loadByTypeIDEffectID() {
		LinkedHashMap<Integer, HashMap<Integer, EdgmTypeEffects>> ret = new LinkedHashMap<>();
		for (EdgmTypeEffects e : load()) {
			HashMap<Integer, EdgmTypeEffects> l = ret.get(e.typeID);
			if (l == null) {
				l = new HashMap<>();
				ret.put(e.typeID, l);
			}
			l.put(e.effectID, e);
		}
		return ret;
	}
}
