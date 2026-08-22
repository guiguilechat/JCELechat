package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EshipTreeGroups {

	//
	// SDE loading
	//

	public static final MapIntLoader<EshipTreeGroups> LOADER = new MapIntLoader<>(
			"shipTreeGroups",
			EshipTreeGroups.class);

	//
	// file structure
	//

	public Map<String, String> description = new LinkedHashMap<>();
	public Map<Integer, Integer> elements = new LinkedHashMap<>();
	public String icon;
	public String iconLarge;
	public String iconSmall;
	public String iconSmallNPC;
	public Map<String, String> name = new LinkedHashMap<>();

	public static class PreReqSkill {
		public static class DisplayLevel {
			public boolean display;
			public int level;
		}

		public Map<Integer, DisplayLevel> skills = new LinkedHashMap<>();
	}

	public Map<Integer, PreReqSkill> preReqSkills = new LinkedHashMap<>();

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
