package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position2D;

public class EmapSolarSystems extends InSpace {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmapSolarSystems> LOADER = new IntMapLoader<>(
			"mapSolarSystems",
			EmapSolarSystems.class);

	//
	// file structure
	//

	public boolean border;
	/** {@link EmapConstellations} */
	public int constellationID;
	public boolean corridor;
	/** {@link Ecategories} */
	public List<Integer> disallowedAnchorCategories = new ArrayList<>();
	/** {@link Egroups} */
	public List<Integer> disallowedAnchorGroups = new ArrayList<>();
	/** {@link Efactions} */
	public int factionID;
	public boolean fringe;
	public boolean hub;
	public boolean international;
	public BigDecimal luminosity;
	public LinkedHashMap<String, String> name;
	/** {@link EmapPlanets} */
	public List<Integer> planetIDs = new ArrayList<>();
	public Position2D position2D;
	public BigDecimal radius;
	/** {@link EmapRegions} */
	public int regionID;
	public boolean regional;
	public String securityClass;
	public BigDecimal securityStatus;
	/** {@link EmapStars} */
	public int starID;
	/** {@link EmapStargates} */
	public List<Integer> stargateIDs = new ArrayList<>();
	public String visualEffect;
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
