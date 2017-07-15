package fr.guiguilechat.eveonline.database.yaml;

import java.util.ArrayList;

import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.sde.fsd.EtypeIDs;

public class Blueprint extends Type {

	/**
	 * used in the blueprints as requirement, or products
	 */
	public static class Material {
		public int quantity;
		public String name;
		/**
		 * probability is !=1 if this is a produc.
		 */
		public float probability = 1.0f;

		public Material(fr.guiguilechat.eveonline.sde.fsd.Eblueprints.Material sdeMat, SDEData sde) {
			quantity = sdeMat.quantity;
			EtypeIDs item = sde.getType(sdeMat.typeID);
			name = item == null ? "unknown_" + sdeMat.typeID : item.enName();
			probability = sdeMat.probability;
		}

		public Material() {
		}
	}

	public static class Skill {
		public String name;
		public int level;

		public Skill(fr.guiguilechat.eveonline.sde.fsd.Eblueprints.Skill skill, SDEData sde) {
			level = skill.level;
			name = sde.getType(skill.typeID).enName();
		}

		public Skill() {
		}
	}

	public static class Activity {
		public ArrayList<Material> materials = new ArrayList<>();
		public ArrayList<Material> products = new ArrayList<>();
		public ArrayList<Skill> skills = new ArrayList<>();
		public int time;

		public Activity(fr.guiguilechat.eveonline.sde.fsd.Eblueprints.BPActivities.Activity activity, SDEData sde) {
			time = activity.time;
			activity.materials.stream().map(m -> new Material(m, sde)).forEach(materials::add);
			activity.products.stream().map(m -> new Material(m, sde)).forEach(products::add);
			activity.skills.stream().map(m -> new Skill(m, sde)).forEach(skills::add);
		}

		public Activity() {
		}
	}

	public Activity copying, invention, manufacturing, research_material, research_time, reaction;

}
