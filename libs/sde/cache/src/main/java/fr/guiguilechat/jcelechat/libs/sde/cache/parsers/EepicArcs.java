package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EepicArcs {

	//
	// SDE loading
	//

	public static final IntMapLoader<EepicArcs> LOADER = new IntMapLoader<>(
			"epicArcs",
			EepicArcs.class);

	//
	// file structure
	//

	public int arcRestartInterval;
	public int factionID;
	public int iconID;

	public static class Mission {
		public int agentID;
		public int failMissionID;
		public List<Integer> nextMissions = new ArrayList<>();
	}

	public LinkedHashMap<Integer, Mission> missions = new LinkedHashMap<>();

	public LinkedHashMap<String, String> name = new LinkedHashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " missions.size=" + first.missions.size()
				+ " enName=" + first.enName());
	}

}
