package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EfreelanceJobSchemas {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "freelanceJobSchemas";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EfreelanceJobSchemas> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EfreelanceJobSchemas> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE_YAML,
			EfreelanceJobSchemas.class,
			Set.of("BoostShield"));

	public static final JacksonYamlLHMLoader<EfreelanceJobSchemas> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	/**
	 * things that have description, iconID, title.
	 */
	public static class WithTitle {
		public HashMap<String, String> description = new HashMap<>();
		public String iconID;
		public HashMap<String, String> title = new HashMap<>();

		public String enDescription() {
			return description == null ? null : description.get("en");
		}

		public String enTitle() {
			return title == null ? null : title.get("en");
		}

	}

	/**
	 * things that have description, iconID, title, unsetdescription.
	 */
	public static class WithUnset extends WithTitle {
		public HashMap<String, String> unsetDescription = new HashMap<>();

		public String enUnsetDescription() {
			return unsetDescription == null ? null : unsetDescription.get("en");
		}
	}

	public static class JobParameter extends WithTitle {

		public static class BooleanStruct {
			public List<String> choiceLabel;
			public boolean _default;

			public static class BoolOption {
				public HashMap<String, String> description = new HashMap<>();
				public HashMap<String, String> title = new HashMap<>();
			}

			public BoolOption optionFalse;
			public BoolOption optionTrue;
		}

		public BooleanStruct _boolean;

		public static class ItemDelivery extends WithTitle {
			public Matcher deliveryLocation;
			public Matcher inventoryType;
		}
		public ItemDelivery itemDelivery;

		public static class Matcher extends WithUnset {
			public List<String> acceptedValueTypes;
			public int maxEntries;
			public boolean optional;
			public String type;
		}
		public Matcher matcher;

	}

	public static class FrelanceJob extends WithTitle {
		public List<String> contentTags;

		public static class ContributionMultiplier extends WithUnset {
			public BigDecimal defaultValue;
			public BigDecimal maxValue;
			public BigDecimal minValue;
		}

		public ContributionMultiplier contributionMultiplier;
		public WithUnset maxContributionsPerParticipant;
		public WithUnset maxProgressPerContribution;
		public HashMap<String, JobParameter> parameters;
		public HashMap<String, String> progressDescription = new HashMap<>();
		public HashMap<String, String> rewardDescription = new HashMap<>();
		public HashMap<String, String> targetDescription = new HashMap<>();

		public String enProgressDescription() {
			return progressDescription == null ? null : progressDescription.get("en");
		}

		public String enRewardDescription() {
			return rewardDescription == null ? null : rewardDescription.get("en");
		}

		public String enTargetDescription() {
			return targetDescription == null ? null : targetDescription.get("en");
		}
	}

	public FrelanceJob BoostShield;

	public FrelanceJob CaptureFWComplex;

	public FrelanceJob DamageShip;

	public FrelanceJob DefendFWComplex;

	public FrelanceJob DeliverItem;

	public FrelanceJob KillCapsuleer;

	public FrelanceJob KillNPC;

	public FrelanceJob MineOre;

	public FrelanceJob RepairArmor;

	public FrelanceJob ShipInsurance;

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.BoostShield.enDescription());
	}

}
