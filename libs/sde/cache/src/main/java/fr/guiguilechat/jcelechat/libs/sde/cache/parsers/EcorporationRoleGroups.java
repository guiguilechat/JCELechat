package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EcorporationRoleGroups {

	//
	// SDE loading
	//

	public static final IntMapLoader<EcorporationRoleGroups> LOADER = new IntMapLoader<>(
			"corporationRoleGroups",
			EcorporationRoleGroups.class);

	//
	// file structure
	//

	public String appliesTo;
	public String appliesToGrantable;
	public boolean isDivisional;
	public boolean isLocational;
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName() + " appliesTo=" + first.appliesTo
				+ " appliesToGrantable=" + first.appliesToGrantable);
	}

}
