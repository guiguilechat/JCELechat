package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapStringLoader;

public class EmilitaryCampaignObjectives {

	//
	// SDE loading
	//

	public static final MapStringLoader<EmilitaryCampaignObjectives> LOADER =
			new MapStringLoader<>(
					"militaryCampaignObjectives",
					EmilitaryCampaignObjectives.class);

	//
	// file structure
	//

	public static class Annotations {
		public int requiredEnlistmentWithFactionID;
		public HashMap<String, String> restrictionTooltip = new HashMap<>();
		public HashMap<String, String> warning1 = new HashMap<>();
		public HashMap<String, String> warning2 = new HashMap<>();

		public String enRestrictionTooltip() {
			return restrictionTooltip == null ? null : restrictionTooltip.get("en");
		}

		public String enWarning1() {
			return warning1 == null ? null : warning1.get("en");
		}

		public String enWarning2() {
			return warning2 == null ? null : warning2.get("en");
		}

	}

	public Annotations annotations;
	public String campaignID;
	public String careerPath;
	public List<String> contentTags = new ArrayList<>();

	public static class ContributionMethodConfiguration {
		public String name;

		public static class Parameter {
			public String key;

			public static class Matcher {
				public static class TypedValues {
					public String valueType;
					public List<Integer> values = new ArrayList<>();
				}

				public List<TypedValues> values = new ArrayList<>();
			}

			public Matcher matcher;
		}

		public List<Parameter> parameters = new ArrayList<>();
	}

	public ContributionMethodConfiguration contributionMethodConfiguration;

	public static class Issuer {
		public int corporationID;
	}

	public Issuer issuer;
	public int maxProgressPerParticipant;
	public int presentingCharacterID;

	public static class Rewards {
		public static class Reward {
			public int amountPerInterval;
			public BigDecimal gainPercentPerInterval;

			public static class Issuer {
				public int corporationID;
				public int factionID;
			}
			public Issuer issuer;
			public long progressInterval;
		}

		public Reward isk, lp, standing;
	}

	public Rewards rewards;
	public HashMap<String, String> subtitle = new HashMap<>();
	public long targetProgress;
	public HashMap<String, String> title = new HashMap<>();

	public String enSubtitle() {
		return subtitle == null ? null : subtitle.get("en");
	}

	public String enTitle() {
		return title == null ? null : title.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : contentTags.size=" + first.contentTags.size());
	}

}
