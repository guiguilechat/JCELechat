package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.StringMapLoader;

public class EmilitaryCampaigns {

	//
	// SDE loading
	//

	public static final StringMapLoader<EmilitaryCampaigns> LOADER =
			new StringMapLoader<>(
					"militaryCampaigns",
					EmilitaryCampaigns.class);

	//
	// file structure
	//

	public static class Annotations {
		public String aoCampaignCardButtonImage;
		public String backgroundVideoLoop;
		public String briefingBackground;
		public HashMap<String, String> briefingFailureDescription = new HashMap<>();
		public HashMap<String, String> briefingFailureHeader = new HashMap<>();
		public HashMap<String, String> briefingFinalWords = new HashMap<>();
		public String briefingForeground;
		public HashMap<String, String> briefingGoalDescription = new HashMap<>();
		public HashMap<String, String> briefingHeader = new HashMap<>();
		public String briefingMiddleground;
		public HashMap<String, String> briefingSuccessDescription = new HashMap<>();
		public HashMap<String, String> briefingSuccessHeader = new HashMap<>();
		public String campaignSet;
		public String dashboardAmbientBackground;
		public String dashboardBackground;
		public String dashboardForeground;
		public String dashboardMiddleground;
		public HashMap<String, String> finishedCampaignEnded = new HashMap<>();
		public HashMap<String, String> finishedFailureDescription = new HashMap<>();
		public HashMap<String, String> finishedResolutionStateFailure = new HashMap<>();
		public HashMap<String, String> finishedResolutionStateSuccess = new HashMap<>();
		public HashMap<String, String> finishedSuccessDescription = new HashMap<>();
		public String foregroundVideoIntro;
		public String foregroundVideoLoop;
		public String foregroundVideoOutro;
		public String mapFocusEntityID;
		public HashMap<String, String> mapHeader = new HashMap<>();
		public HashMap<String, String> mapSection1Paragraph = new HashMap<>();
		public HashMap<String, String> mapSection1Title = new HashMap<>();
		public HashMap<String, String> mapSection2Paragraph = new HashMap<>();
		public HashMap<String, String> mapSection2Title = new HashMap<>();
		public HashMap<String, String> mapSection3Paragraph = new HashMap<>();
		public HashMap<String, String> mapSection3Title = new HashMap<>();
		public HashMap<String, String> mapSubheader = new HashMap<>();
		public HashMap<String, String> mapTitle = new HashMap<>();
		public String middlegroundVideoIntro;
		public String middlegroundVideoLoop;
		public String middlegroundVideoOutro;
		public HashMap<String, String> presentingCharacterName = new HashMap<>();
		public HashMap<String, String> presentingCharacterSubtitle = new HashMap<>();
		public String presentingCharacterTexturePath;
		public String race;
		public String themePack;
		public String towCampaignCardButtonImage;
	}

	public Annotations annotations;

	public static class Issuer {
		public int factionID;
	}

	public Issuer issuer;
	public HashMap<String, String> subtitle = new HashMap<>();
	public int targetProgress;
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
		System.out.println("first : title=" + first.enTitle());
	}

}
