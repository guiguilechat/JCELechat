package fr.guiguilechat.eveonline.database.retrieval.sde.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.eveonline.database.retrieval.sde.SDEDumper;

public class EdgmAttributeTypes {

	public static final File FILE = new File(SDEDumper.CACHEDIR, "sde/bsd/dgmAttributeTypes.yaml");

	@SuppressWarnings("unchecked")
	public static ArrayList<EdgmAttributeTypes> load() {
		SDEDumper.donwloadSDE();
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

	public static HashMap<Integer, EdgmAttributeTypes> loadByAttributeID() {
		HashMap<Integer, EdgmAttributeTypes> ret = new HashMap<>();
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
