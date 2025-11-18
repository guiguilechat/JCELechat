package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;

public class EmapConstellations extends InSpace {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmapConstellations> LOADER = new IntMapLoader<>(
			"mapConstellations",
			EmapConstellations.class,
			Set.of("regionID"));

	//
	// file structure
	//

	/** {@link Efactions} */
	public int factionID;
	public LinkedHashMap<String, String> name;
	/** {@link EmapRegions} */
	public int regionID;
	/** {@link EmapSolarSystems} */
	public List<Integer> solarSystemIDs = new ArrayList<>();
	public int wormholeClassID;


	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : regionID=" + first.regionID + " name=" + first.enName() + " position=" + first.position);
	}
}
