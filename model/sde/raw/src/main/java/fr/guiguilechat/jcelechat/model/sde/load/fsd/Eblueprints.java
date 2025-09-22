package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlLHMLoader;

/**
 * an entry in the fsd/typeIDs.yaml
 */
public class Eblueprints {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/blueprints.yaml";

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, Eblueprints>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlLHMLoader<Integer, Eblueprints> LOADER_SNAKEYAML = new OldSnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("blueprintTypeID"::equals).findAny().isPresent()) {
						node.setType(Eblueprints.class);
					}
				}
			}
		}
	};

	public static final OldJacksonYamlLoader<LinkedHashMap<Integer, Eblueprints>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int blueprintTypeID;
	public int maxProductionLimit;

	/**
	 * used in the blueprints as requirement, or products
	 */
	public static class Material {
		public int quantity;
		public int typeID;
		public float probability = 1.0f;
	}

	public static class Skill {
		public int typeID;
		public int level;
	}

	public enum ActivityType {
		copying {
			@Override
			public fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity of(BPActivities act) {
				return act.copying;
			}
		},
		invention {
			@Override
			public fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity of(BPActivities act) {
				return act.invention;
			}
		},
		manufacturing {
			@Override
			public fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity of(BPActivities act) {
				return act.manufacturing;
			}
		},
		reaction {
			@Override
			public fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity of(BPActivities act) {
				return act.reaction;
			}
		},
		research_material {
			@Override
			public fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity of(BPActivities act) {
				return act.research_material;
			}
		},
		research_time {
			@Override
			public fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity of(BPActivities act) {
				return act.research_time;
			}
		};

		public abstract fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity of(
				BPActivities act);
	}

	public BPActivities activities = new BPActivities();

	public static class BPActivities {

		public static class Activity {
			public ArrayList<Material> materials = new ArrayList<>();
			public ArrayList<Material> products = new ArrayList<>();
			public ArrayList<Skill> skills = new ArrayList<>();
			public int time;

			public boolean active() {
				return time > 0 || !materials.isEmpty() || !products.isEmpty() || !skills.isEmpty();
			}
		}

		public Activity copying = new Activity();
		public Activity invention = new Activity();
		public Activity reaction = new Activity();

		public Activity manufacturing = new Activity();
		public Activity research_material = new Activity();
		public Activity research_time = new Activity();

	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == Eblueprints.class) {
			return ((Eblueprints) obj).blueprintTypeID == blueprintTypeID;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return blueprintTypeID;
	}

	public static void main(String[] args) {
		System.err.println("loaded : " + LOADER.load().size());
		var first = LOADER.load().entrySet().iterator().next().getValue();
		System.err.println(
				"first : id=" + first.blueprintTypeID + " maxproduction=" + first.maxProductionLimit);
	}

}
