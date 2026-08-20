package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Eschools {

	//
	// SDE loading
	//

	public static final IntMapLoader<Eschools> LOADER = new IntMapLoader<>(
			"schools",
			Eschools.class);

	//
	// file structure
	//

	public List<Integer> careerAgents = new ArrayList<>();
	public int careerID;
	public Map<String, String> characterDescription = new LinkedHashMap<>();
	public int corporationID;
	public Map<String, String> description = new LinkedHashMap<>();
	public int iconID;
	public boolean isStarterSpaceSchool;
	public Map<String, String> name = new LinkedHashMap<>();
	public int raceID;
	public List<Integer> startingStations = new ArrayList<>();
	public Map<String, String> title = new LinkedHashMap<>();


	public String enCharacterDescription() {
		return characterDescription == null ? null : characterDescription.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	public String enTitle() {
		return title == null ? null : title.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : enName=" + first.enName());
	}

}
