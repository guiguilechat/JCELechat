package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class EdgmAttributeTypes {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/dgmAttributeTypes.yaml");
	private static ArrayList<EdgmAttributeTypes> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EdgmAttributeTypes> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EdgmAttributeTypes.class);
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

	public static LinkedHashMap<Integer, EdgmAttributeTypes> loadByAttributeID() {
		LinkedHashMap<Integer, EdgmAttributeTypes> ret = new LinkedHashMap<>();
		for (EdgmAttributeTypes e : load()) {
			ret.put(e.attributeID, e);
		}
		return ret;
	}

	public int attributeID;
	public String attributeName;
	public int categoryID;
	public double defaultValue;
	public String description;
	public boolean highIsGood;
	public boolean published;
	public boolean stackable;
	public int iconID;
	public int unitID;
	public String displayName;

}
