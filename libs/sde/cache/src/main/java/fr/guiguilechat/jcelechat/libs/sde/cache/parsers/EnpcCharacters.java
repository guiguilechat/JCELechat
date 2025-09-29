package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;

public class EnpcCharacters {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "npcCharacters";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EnpcCharacters> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EnpcCharacters> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EnpcCharacters.class, Set.of("corporationID"));

	public static final JacksonYamlLHMLoader<EnpcCharacters> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int ancestryID;

	public static class AgentData {
		public int agentTypeID;
		public int divisionID;
		public boolean isLocator;
		public int level;
	}
	public AgentData agent;

	public int bloodlineID;
	public int careerID;
	public boolean ceo;
	public int corporationID;
	public String description;
	/** false = female */
	public boolean gender;
	public int locationID;
	public Map<String, String> name = new LinkedHashMap<>();
	public int raceID;
	public int schoolID;

	public static class SkillId {
		public int typeID;
	}
	public List<SkillId> skills = new ArrayList<>();

	public int specialityID;
	public String startDate;
	public boolean uniqueName;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : corporation=" + first.corporationID + " gender=" + first.gender);
	}
}
