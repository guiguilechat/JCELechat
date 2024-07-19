package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

/**
 * an entry in the fsd/typeIDs.yaml
 */
public class Eblueprints {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "fsd/blueprints.yaml");

	private static LinkedHashMap<Integer, Eblueprints> cache = null;

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

	public static enum ActivityType {
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

	public static synchronized LinkedHashMap<Integer, Eblueprints> load() {
		if (cache == null) {
			try {
				cache = from(new FileInputStream(FILE));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, Eblueprints> from(InputStream is) {
		Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {
			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					MappingNode mn = (MappingNode) node;
					if (mn.getValue().size() > 0) {
						if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
								.filter("blueprintTypeID"::equals).findAny().isPresent()) {
							node.setType(Eblueprints.class);
						}
					}
				}
				Construct ret = super.getConstructor(node);
				return ret;
			}
		};
		Yaml yaml = SDECache.yaml(cons);
		return yaml.loadAs(is, LinkedHashMap.class);
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
