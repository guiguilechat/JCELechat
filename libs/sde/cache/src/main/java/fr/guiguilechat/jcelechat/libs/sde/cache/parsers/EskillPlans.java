package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EskillPlans {

	//
	// SDE loading
	//

	public static final IntMapLoader<EskillPlans> LOADER = new IntMapLoader<>(
			"skillPlans",
			EskillPlans.class);

	//
	// file structure
	//

	public int careerPathID;

	public Map<String, String> description = new LinkedHashMap<>();
	public int factionID;
	public String internalName;

	public static class SkillLevel {
		public int level;
		public int typeID;
	}
	public List<SkillLevel> milestones = new ArrayList<>();
	public Map<String, String> name = new LinkedHashMap<>();
	public int npcCorporationDivision;
	public List<SkillLevel> skillRequirements = new ArrayList<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//
	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : enDescription=" + first.enDescription());
	}

}
