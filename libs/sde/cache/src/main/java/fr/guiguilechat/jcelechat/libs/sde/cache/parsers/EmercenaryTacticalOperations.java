package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EmercenaryTacticalOperations {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmercenaryTacticalOperations> LOADER =
			new IntMapLoader<>(
					"mercenaryTacticalOperations",
					EmercenaryTacticalOperations.class);

	//
	// file structure
	//

	public int anarchyImpact;
	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public int developmentImpact;
	public int dungeonID;
	public int infomorphBonus;
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();

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
		System.out.println("first : anarchy_impact=" + first.anarchyImpact + " name=" + first.enName());
	}

}
