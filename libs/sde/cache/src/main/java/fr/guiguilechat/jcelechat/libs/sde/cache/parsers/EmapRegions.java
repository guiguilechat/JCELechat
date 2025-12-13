package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;

public class EmapRegions extends InSpace {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmapRegions> LOADER = new IntMapLoader<>(
			"mapRegions",
			EmapRegions.class);

	//
	// file structure
	//

	/** {@link EmapConstellations} */
	public List<Integer> constellationIDs = new ArrayList<>();
	public Map<String, String> description = new LinkedHashMap<>();
	/** {@link Efactions} */
	public int factionID;
	public LinkedHashMap<String, String> name;
	public int nebulaID;
	public int wormholeClassID;

	public String enName() {
		return name == null ? null : name.get("en");
	}

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		long withname = loaded.values().stream().filter(m -> m.name != null).count();
		System.out.println("named=" + withname);
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : whclass=" + first.wormholeClassID + " name=" + first.enName() + " position=" + first.position);
	}
}
