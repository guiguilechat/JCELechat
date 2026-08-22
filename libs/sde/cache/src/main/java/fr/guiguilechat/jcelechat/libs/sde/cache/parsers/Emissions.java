package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class Emissions {

	//
	// SDE loading
	//

	public static final MapIntLoader<Emissions> LOADER = new MapIntLoader<>(
			"missions",
			Emissions.class);

	//
	// file structure
	//

	public int agentTypeID;
	public int corporationID;
	public static class CourierMission {
		public int objectiveQuantity;
		public boolean objectiveSingleton;
		public int objectiveTypeID;
	}

	public CourierMission courierMission;
	public int expirationTime;
	public Map<Integer, BigDecimal> extraStandings = new LinkedHashMap<>();
	public boolean hasStandingRewards;
	public int initialAgentGiftQuantity;
	public int initialAgentGiftTypeID;
	public int factionID;
	public static class KillMission {
		public int dropItemInMissionContainer;
		public int dungeonID;
		public int objectiveQuantity;
		public int objectiveTypeID;
	}

	public KillMission killMission;

	public static class Messages {

		@JsonAlias("messages.epicMission.journalText.chapterTitle")
		public HashMap<String, String> epicMissionJournalTextChapterTitle = new HashMap<>();
		@JsonAlias("messages.epicMission.journalText.completedMessage")
		public HashMap<String, String> epicMissionJournalTextCompletedMessage = new HashMap<>();
		@JsonAlias("messages.epicMission.journalText.inProgressMessage")
		public HashMap<String, String> epicMissionJournalTextInProgressMessage = new HashMap<>();
		@JsonAlias("messages.mission.accepted.agentsays")
		public HashMap<String, String> missionAcceptedAgentsays = new HashMap<>();
		@JsonAlias("messages.mission.accept.charsays")
		public HashMap<String, String> missionAcceptCharsays = new HashMap<>();
		@JsonAlias("messages.mission.ask.charsays")
		public HashMap<String, String> missionAskCharsays = new HashMap<>();
		@JsonAlias("messages.mission.briefing")
		public HashMap<String, String> missionBriefing = new HashMap<>();
		@JsonAlias("messages.mission.complete.agentsays")
		public HashMap<String, String> missionCompleteAgentsays = new HashMap<>();
		@JsonAlias("messages.mission.complete.charsays")
		public HashMap<String, String> missionCompleteCharsays = new HashMap<>();
		@JsonAlias("messages.mission.completed.agentsays")
		public HashMap<String, String> missionCompletedAgentsays = new HashMap<>();
		@JsonAlias("messages.mission.completed.nextmission.agentsays")
		public HashMap<String, String> missionCompletedNextmissionAgentsays = new HashMap<>();
		@JsonAlias("messages.mission.decline.charsays")
		public HashMap<String, String> missionDeclineCharsays = new HashMap<>();
		@JsonAlias("messages.mission.declined.agentsays")
		public HashMap<String, String> missionDeclinedAgentsays = new HashMap<>();
		@JsonAlias("messages.mission.extrainfo.body")
		public HashMap<String, String> missionExtrainfoBody = new HashMap<>();
		@JsonAlias("messages.mission.extrainfo.header")
		public HashMap<String, String> missionExtrainfoHeader = new HashMap<>();
		@JsonAlias("messages.mission.offered.agentsays")
		public HashMap<String, String> missionOfferedAgentsays = new HashMap<>();
		@JsonAlias("messages.mission.offerexpired.email.body")
		public HashMap<String, String> missionOfferexpiredEmailBody = new HashMap<>();
		@JsonAlias("messages.mission.offerexpired.email.header")
		public HashMap<String, String> missionOfferexpiredEmailHeader = new HashMap<>();
		@JsonAlias("messages.mission.option1.charsays")
		public HashMap<String, String> missionOption1Charsays = new HashMap<>();
		@JsonAlias("messages.mission.option2.charsays")
		public HashMap<String, String> missionOption2Charsays = new HashMap<>();
		@JsonAlias("messages.mission.option3.charsays")
		public HashMap<String, String> missionOption3Charsays = new HashMap<>();
		@JsonAlias("messages.mission.option4.charsays")
		public HashMap<String, String> missionOption4Charsays = new HashMap<>();
		@JsonAlias("messages.mission.quit.charsays")
		public HashMap<String, String> missionQuitCharsays = new HashMap<>();
		@JsonAlias("messages.mission.quitted.agentsays")
		public HashMap<String, String> missionQuittedAgentsays = new HashMap<>();
		@JsonAlias("messages.mission.timeout.emailheader")
		public HashMap<String, String> missionTimeoutEmailheader = new HashMap<>();
		@JsonAlias("messages.mission.timeout.emailbody")
		public HashMap<String, String> missionTimeoutEmailbody = new HashMap<>();
		@JsonAlias("messages.root.agentsays")
		public HashMap<String, String> rootAgentsays = new HashMap<>();
		@JsonAlias("messages.root.missioninprogress.agentsays")
		public HashMap<String, String> rootMissioninprogressAgentsays = new HashMap<>();
	}

	public Messages messages;

	public static class MissionRewards {
		public class Reward {
			public int rewardQuantity;
			public int rewardTypeID;
		}

		public Reward bonusReward;
		public int bonusTimeInterval;
		public Reward reward;
	}

	public MissionRewards missionRewards;
	public HashMap<String, String> name = new HashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : enName=" + first.enName());
	}

}
