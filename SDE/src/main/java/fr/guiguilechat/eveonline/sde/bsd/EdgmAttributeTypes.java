package fr.guiguilechat.eveonline.sde.bsd;

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

import fr.guiguilechat.eveonline.sde.cache.SDECache;

public class EdgmAttributeTypes {

	public static final File FILE = new File(SDECache.CACHEDIR, "sde/bsd/dgmAttributeTypes.yaml");

	@SuppressWarnings("unchecked")
	public static ArrayList<EdgmAttributeTypes> load() {
		SDECache.donwloadSDE();
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
			return yaml.loadAs(new FileReader(FILE), ArrayList.class);
		} catch (FileNotFoundException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
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
