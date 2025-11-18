package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EfreelanceJobSchemas {

	//
	// SDE loading
	//

	public static final IntMapLoader<EfreelanceJobSchemas> LOADER = new IntMapLoader<>(
			"freelanceJobSchemas",
			EfreelanceJobSchemas.class,
			true,
			Set.of("BoostShield"));

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

		public static class BooleanStruct extends WithTitle {
			public HashMap<String, String> choiceLabel;
			@JsonProperty("default")
			public boolean _default;

			public static class BoolOption {
				public HashMap<String, String> description = new HashMap<>();
				public HashMap<String, String> title = new HashMap<>();
			}

			public BoolOption optionFalse;
			public BoolOption optionTrue;
		}

		@JsonProperty("boolean")
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
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first.boststhield.title=" + first.BoostShield.enTitle() + " shipinsurance/parameters[boolean].title="
				+ first.ShipInsurance.parameters.get("cover_implants")._boolean.enTitle());
	}

}
