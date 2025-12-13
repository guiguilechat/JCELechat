package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class Efactions {

	//
	// SDE loading
	//

	public static final IntMapLoader<Efactions> LOADER = new IntMapLoader<>(
			"factions",
			Efactions.class);

	//
	// file structure
	//

	/** {@link EnpcCorporations} */
	public int corporationID;
	public HashMap<String, String> description = new HashMap<>();
	public String flatLogo;
	public String flatLogoWithName;
	public int iconID;
	public List<Integer> memberRaces = new ArrayList<>();
	/** {@link EnpcCorporations} */
	public int militiaCorporationID;
	public HashMap<String, String> name = new HashMap<>();
	public HashMap<String, String> shortDescription = new HashMap<>();
	public BigDecimal sizeFactor;
	/** {@link EmapSolarSystems} */
	public int solarSystemID;
	public boolean uniqueName;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : name=" + first.enName());
	}
}
