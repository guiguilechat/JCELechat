package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

/**
 * an entry in the fsd/typeIDs.yaml
 */
public class Eblueprints {

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eblueprints>> LOADER_JACKSON = new JacksonYamlLoader<>(
			"fsd/blueprints.yaml");

	public static final SnakeYamlLHMLoader<Integer, Eblueprints> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			"fsd/blueprints.yaml") {

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

	public static final JacksonYamlLoader<LinkedHashMap<Integer, Eblueprints>> LOADER = LOADER_SNAKEYAML;

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
		copying,
		invention,
		manufacturing,
		reaction,
		research_material,
		research_time
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

		public Activity activity(ActivityType at) {
			return switch (at) {
				case copying -> copying;
				case invention -> invention;
				case manufacturing -> manufacturing;
				case reaction -> reaction;
				case research_material -> research_material;
				case research_time -> research_time;
						default ->
					throw new IllegalArgumentException("Unexpected value: " + at);

			};
		}

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

}
