package fr.guiguilechat.jcelechat.libs.sde.model.industry;

import java.math.BigDecimal;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ActivityDetails;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.Material;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ProducingActivityDetails;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.Product;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.Skill;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class BluePrint extends DataSourced<Eblueprints> {

	public static final Mapper<Eblueprints, BluePrint> CACHE = new Mapper<>(
			Eblueprints.LOADER.yaml(),
			BluePrint::new);

	/** max runs of a copied bpc */
	private final int maxProductionLimit;

	@Getter(lazy = true)
	private final Type type = datasource().types().of(id());

	@RequiredArgsConstructor
	@Accessors(fluent = true)
	public static class Consumed {
		public final int quantity;
		public final Type type;

		@Getter(lazy = true)
		private final String toString = type.toString() + (quantity != 1 ? " ×" + quantity : "");
	}

	protected List<Consumed> translateConsumed(List<Material> materials) {
		if (materials == null)
			return null;
		return materials.stream()
				.map(m -> new Consumed(m.quantity, datasource().types().of(m.typeID)))
				.toList();
	}

	@RequiredArgsConstructor
	@Accessors(fluent = true)
	public static class Produced {
		public final int quantity;
		public final Type type;
		public final BigDecimal probability;

		@Getter(lazy = true)
		private final String toString = type.toString() + (quantity != 1 ? " ×" + quantity : "")
				+ (probability.floatValue() < 1.0f ? "(p=" + probability + ")" : "");
	}

	protected List<Produced> translateProduced(List<Product> materials) {
		if (materials == null)
			return null;
		return materials.stream()
				.map(m -> new Produced(m.quantity, datasource().types().of(m.typeID), m.probability))
				.toList();
	}

	@RequiredArgsConstructor
	@Accessors(fluent = true)
	public static class Needed {
		public final int level;
		public final Type type;

		@Getter(lazy = true)
		private final String toString = type.toString();
	}

	protected List<Needed> translateNeeded(List<Skill> materials) {
		if (materials == null)
			return null;
		return materials.stream()
				.map(m -> new Needed(m.level, datasource().types().of(m.typeID)))
				.toList();
	}

	@RequiredArgsConstructor
	public static class Activity {
		public final List<Consumed> materials;
		public final List<Needed> skills;
		public final int time;

		public boolean active() {
			return time > 0 || !materials.isEmpty() || !skills.isEmpty();
		}
	}

	protected Activity translate(ActivityDetails source) {
		if (source == null) {
			return null;
		}
		List<Consumed> materials = translateConsumed(source.materials);
		List<Needed> skills = translateNeeded(source.skills);
		return new Activity(materials, skills, source.time);
	}

	public static class ProducingActivity extends Activity {
		public final List<Produced> products;

		public ProducingActivity(List<Consumed> materials,
				List<Needed> skills,
				int time,
				List<Produced> products) {
			super(materials, skills, time);
			this.products = products;
		}

		@Override
		public boolean active() {
			return super.active() || !products.isEmpty();
		}
	}

	protected ProducingActivity translate(ProducingActivityDetails source) {
		if (source == null) {
			return null;
		}
		List<Consumed> materials = translateConsumed(source.materials);
		List<Needed> skills = translateNeeded(source.skills);
		List<Produced> products = translateProduced(source.products);
		return new ProducingActivity(materials, skills, source.time, products);
	}

	@Getter(lazy = true)
	private final Activity copying = translate(source().activities.copying);
	@Getter(lazy = true)
	private final ProducingActivity invention = translate(source().activities.invention);
	@Getter(lazy = true)
	private final ProducingActivity reaction = translate(source().activities.reaction);
	@Getter(lazy = true)
	private final ProducingActivity manufacturing = translate(source().activities.manufacturing);
	@Getter(lazy = true)
	private final Activity researchMaterial = translate(source().activities.research_material);
	@Getter(lazy = true)
	private final Activity researchTime = translate(source().activities.research_time);

	public BluePrint(DataSource datasource, int id, Eblueprints source) {
		super(datasource, id, source);
		maxProductionLimit = source.maxProductionLimit;
	}

	protected BluePrint(int id, Eblueprints source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

}
