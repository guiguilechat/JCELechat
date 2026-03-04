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

	public int anarchy_impact;
	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public int development_impact;
	public int infomorph_bonus;
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
		System.out.println("first : anarchy_impact=" + first.anarchy_impact + " name=" + first.enName());
	}

}
