package fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.type.ConsumedMaterial;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.type.RequiredSkill;
import lombok.Getter;

public class Eblueprints {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Eblueprints> loader = new KeyValTimeLoader<>(
			Eblueprints.class, "staticdata/blueprints.static");

	public static class Activities {

		public static class Activity {

			public static class Product {

				public BigDecimal probability;
				public int quantity;
				@JsonProperty("typeID")
				public int typeId;
			}

			public List<ConsumedMaterial> materials = new ArrayList<>();
			public List<Product> products = new ArrayList<>();
			public List<RequiredSkill> skills = new ArrayList<>();
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
	@JsonProperty("blueprintTypeID")
	public int blueprintTypeId;
	public int maxProductionLimit;

}
