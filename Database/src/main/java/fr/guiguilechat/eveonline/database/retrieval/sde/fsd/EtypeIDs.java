package fr.guiguilechat.eveonline.database.retrieval.sde.fsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.eveonline.database.retrieval.sde.SDEDumper;

/**
 * an entry in the fsd/typeIDs.yaml
 */
public class EtypeIDs {

	public static class Etraits {
		public static class Bonus {
			public double bonus;
			public HashMap<String, String> bonusText;
			public int importance;
			public int nameID;
			public int unitID;
		}

		public ArrayList<Bonus> miscBonuses;
		public ArrayList<Bonus> roleBonuses;
		public HashMap<Integer, ArrayList<Bonus>> types;
	}

	public static final File FILE = new File(SDEDumper.CACHEDIR, "sde/fsd/typeIDs.yaml");
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, EtypeIDs> load() {
		SDEDumper.donwloadSDE();
		Constructor cons = new Constructor(LinkedHashMap.class) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					MappingNode mn = (MappingNode) node;
					if (mn.getValue().size() > 0) {
						if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
								.filter(s -> "groupID".equals(s)).findAny().isPresent())
							node.setType(EtypeIDs.class);
					}
				}
				Construct ret = super.getConstructor(node);
				return ret;
			}
		};
		TypeDescription td = new TypeDescription(EtypeIDs.class);
		td.putMapPropertyType("name", String.class, String.class);
		td.putMapPropertyType("description", String.class, String.class);
		cons.addTypeDescription(td);
		Yaml yaml = new Yaml(cons);
		try {
			return yaml.loadAs(new FileReader(FILE), LinkedHashMap.class);
		} catch (FileNotFoundException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public double basePrice;
	public double capacity;
	public HashMap<String, String> description;
	public int factionID;
	public int graphicID;
	public int groupID;
	public int iconID;
	public int marketGroupID;
	public double mass;
	public HashMap<Integer, List<Integer>> masteries;
	public HashMap<String, String> name;
	public int portionSize;
	public boolean published;
	public int raceID;
	public double radius;
	public String sofFactionName;
	public int sofMaterialSetID;
	public int soundID;

	public Etraits traits;

	public double volume;

}
