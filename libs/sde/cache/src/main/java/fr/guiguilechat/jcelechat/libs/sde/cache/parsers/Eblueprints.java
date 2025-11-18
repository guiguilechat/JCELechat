package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ActivityDetails;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ProducingActivityDetails;

/**
 * an entry in the fsd/typeIDs.yaml
 */
public class Eblueprints {

	//
	// SDE loading
	//

	public static final IntMapLoader<Eblueprints> LOADER = new IntMapLoader<>(
			"blueprints",
			Eblueprints.class,
			Set.of("blueprintTypeID"));

	//
	// file structure
	//

	/**
	 * self. Should be removed
	 */
	public int blueprintTypeID;
	/** max runs of a copied bpc */
	public int maxProductionLimit;

	public static class Material {
		public int quantity;
		/** {@link Etypes} */
		public int typeID;
	}

	public static class Product extends Material {
		public BigDecimal probability = new BigDecimal(1);
	}

	public static class Skill {
		public int level;
		/** {@link Etypes} */
		public int typeID;
	}

	public static class BPActivities {

		public static class ActivityDetails {
			public ArrayList<Material> materials = new ArrayList<>();
			public ArrayList<Skill> skills = new ArrayList<>();
			public int time;

			public boolean active() {
				return time > 0 || !materials.isEmpty() || !skills.isEmpty();
			}
		}

		public static class ProducingActivityDetails extends ActivityDetails {
			public ArrayList<Product> products = new ArrayList<>();

			@Override
			public boolean active() {
				return super.active() || !products.isEmpty();
			}
		}

		public ActivityDetails copying = new ActivityDetails();
		public ProducingActivityDetails invention = new ProducingActivityDetails();
		public ProducingActivityDetails reaction = new ProducingActivityDetails();

		public ProducingActivityDetails manufacturing = new ProducingActivityDetails();
		public ActivityDetails research_material = new ActivityDetails();
		public ActivityDetails research_time = new ActivityDetails();

	}

	public BPActivities activities = new BPActivities();

	//

	public enum ActivityType {
		copying {
			@Override
			public ActivityDetails of(
					BPActivities act) {
				return act.copying;
			}
		},
		invention {
			@Override
			public ProducingActivityDetails of(
					BPActivities act) {
				return act.invention;
			}
		},
		manufacturing {
			@Override
			public ProducingActivityDetails of(
					BPActivities act) {
				return act.manufacturing;
			}
		},
		reaction {
			@Override
			public ProducingActivityDetails of(
					BPActivities act) {
				return act.reaction;
			}
		},
		research_material {
			@Override
			public ActivityDetails of(
					BPActivities act) {
				return act.research_material;
			}
		},
		research_time {
			@Override
			public ActivityDetails of(
					BPActivities act) {
				return act.research_time;
			}
		};

		public abstract ActivityDetails of(
				BPActivities act);
	}

	//

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

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : id=" + first.blueprintTypeID + " maxproduction=" + first.maxProductionLimit);
	}

}
