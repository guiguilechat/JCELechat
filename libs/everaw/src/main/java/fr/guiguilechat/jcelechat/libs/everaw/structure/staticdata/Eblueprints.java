package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class Eblueprints {

	public static class Activities {

		public static class Activity {

			public static class Material {
				public int quantity;

				public int typeID;
			}

			public static class Product {
				public BigDecimal probability;

				public int quantity;

				public int typeID;
			}

			public static class Skill {
				public int level;

				public int typeID;
			}

			public List<Material> materials = new ArrayList<>();

			public List<Product> products = new ArrayList<>();

			public List<Skill> skills = new ArrayList<>();

			public int time;

		}

		public Activity copying;

		public Activity invention;

		public Activity manufacturing;

		public Activity reaction;

		@JsonProperty("research_material")
		public Activity researchMaterial;

		@JsonProperty("research_time")
		public Activity researchTime;

	}

	public Activities activities;

	public int blueprintTypeID;

	public int maxProductionLimit;

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Eblueprints> loader = new KeyValTimeLoader<>(
			Eblueprints.class, "staticdata/blueprints.static");

}
