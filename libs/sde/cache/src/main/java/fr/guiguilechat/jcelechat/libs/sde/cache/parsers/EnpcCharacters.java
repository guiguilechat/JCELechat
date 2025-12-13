package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EnpcCharacters {

	//
	// SDE loading
	//

	public static final IntMapLoader<EnpcCharacters> LOADER = new IntMapLoader<>(
			"npcCharacters",
			EnpcCharacters.class);

	//
	// file structure
	//

	/** {@link Eancestries} */
	public int ancestryID;

	public static class AgentData {
		/** {@link EagentTypes} */
		public int agentTypeID;
		/** {@link EnpcCorporationDivisions} */
		public int divisionID;
		public boolean isLocator;
		public int level;
	}
	public AgentData agent;

	/** {@link Ebloodlines} */
	public int bloodlineID;
	/** ? */
	public int careerID;
	public boolean ceo;
	/** {@link EnpcCorporations} */
	public int corporationID;
	public String description;
	/** false = female */
	public boolean gender;
	public int locationID;
	public Map<String, String> name = new LinkedHashMap<>();
	public int raceID;
	public int schoolID;

	public static class SkillId {
		/** {@link Etypes} */
		public int typeID;
	}
	public List<SkillId> skills = new ArrayList<>();

	/** ? */
	public int specialityID;
	public String startDate;
	public boolean uniqueName;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : corporation=" + first.corporationID + " gender=" + first.gender);
	}
}
