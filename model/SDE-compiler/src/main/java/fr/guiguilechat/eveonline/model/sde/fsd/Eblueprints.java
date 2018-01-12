package fr.guiguilechat.eveonline.model.sde.fsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.eveonline.model.sde.cache.SDECache;

/**
 * an entry in the fsd/typeIDs.yaml
 */
public class Eblueprints {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/blueprints.yaml");

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

	public BPActivities activities = new BPActivities();

	public static class BPActivities {

		public static class Activity {
			public ArrayList<Material> materials = new ArrayList<>();
			public ArrayList<Material> products = new ArrayList<>();
			public ArrayList<Skill> skills = new ArrayList<>();
			public int time;
		}

		public Activity copying = new Activity();
		public Activity invention = new Activity();
		public Activity reaction = new Activity();

		public Activity manufacturing = new Activity();
		public Activity research_material = new Activity();
		public Activity research_time = new Activity();

	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, Eblueprints> load() {
		SDECache.INSTANCE.donwloadSDE();

		Constructor cons = new Constructor(LinkedHashMap.class) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					MappingNode mn = (MappingNode) node;
					if (mn.getValue().size() > 0) {
						if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
								.filter(s -> "blueprintTypeID".equals(s)).findAny().isPresent()) {
							node.setType(Eblueprints.class);
						}
					}
				}
				Construct ret = super.getConstructor(node);
				return ret;
			}
		};
		Yaml yaml = new Yaml(cons);
		try {
			return yaml.loadAs(new FileReader(FILE), LinkedHashMap.class);
		} catch (FileNotFoundException e) {
			throw new UnsupportedOperationException("catch this", e);
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
