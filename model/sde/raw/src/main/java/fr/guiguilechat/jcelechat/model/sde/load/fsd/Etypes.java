package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

/**
 * an entry in the fsd/typeIDs.yaml
 */
public class Etypes {

	private static final Logger logger = LoggerFactory.getLogger(Etypes.class);

	public static final SnakeYamlLHMLoader<LinkedHashMap<Integer, Etypes>> LOADER
	// = new JackonYamlLoader<>("fsd/types.yaml") ;
			= new SnakeYamlLHMLoader<>("fsd/types.yaml") {

				@Override
				protected void preprocess(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter("groupID"::equals).findAny().isPresent()) {
								node.setType(Etypes.class);
							}
						}
					}
				}
			};

	public static class Etraits {
		public static class Bonus {
			public double bonus;
			public HashMap<String, String> bonusText;
			public int importance;
			public boolean isPositive;
			public int nameID;
			public int unitID;
		}

		public int iconID;
		public ArrayList<Bonus> miscBonuses;
		public ArrayList<Bonus> roleBonuses;
		public HashMap<Integer, ArrayList<Bonus>> types;
	}

	private static final HashMap<Integer, String> ERROR_NAMES = new HashMap<>();

	public static String getName(int typeId) {
		var type = LOADER.load().get(typeId);
		if (type != null) {
			return type.enName();
		}
		synchronized (ERROR_NAMES) {
			return ERROR_NAMES.computeIfAbsent(typeId, i -> {
				logger.warn("can't load type id " + i, new Exception());
				return "missingType_" + i;
			});
		}
	}

	public float basePrice;
	public float capacity;
	/** key is language short, like "en" */
	public HashMap<String, String> description = new HashMap<>();
	public int factionID;
	public int graphicID;
	public int groupID;
	public int iconID;
	public int marketGroupID;
	public float mass;
	/** key is mastery level, value is the list of ?? id */
	public HashMap<Integer, List<Integer>> masteries = new HashMap<>();
	public float metaGroupID;
	/** key is language short, like "en" */
	public HashMap<String, String> name = new HashMap<>();
	public int portionSize;
	public boolean published;
	public int raceID;
	public float radius;
	public String sofFactionName;
	public int sofMaterialSetID;
	public int soundID;
	public Etraits traits;
	public Integer variationParentTypeID;
	public float volume;

	public String enName() {
		String ret = name.get("en");
		if (ret == null) {
			ret = description.get("en");
		}
		if (ret == null) {
			ret = "unnamed_g" + groupID + "_ic" + iconID;
		}
		return ret;
	}

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded " + loaded.size() + " types");
	}

}
