package fr.guiguilechat.eveonline.model.database.yaml;

import java.util.ArrayList;

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

		public Material() {
		}
	}

	public static class Skill {
		public String name;
		public int level;
		// public int skill_id;

		public Skill() {
		}
	}

	public static class Activity {
		public ArrayList<Material> materials = new ArrayList<>();
		public ArrayList<Material> products = new ArrayList<>();
		public ArrayList<Skill> skills = new ArrayList<>();
		public int time;

		public Activity() {
		}
	}

	public Activity copying, invention, manufacturing, research_material, research_time, reaction;

}
