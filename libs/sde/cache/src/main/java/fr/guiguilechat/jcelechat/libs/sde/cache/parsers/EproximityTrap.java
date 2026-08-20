package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EproximityTrap {

	//
	// SDE loading
	//

	public static final IntMapLoader<EproximityTrap> LOADER = new IntMapLoader<>(
			"proximityTrap",
			EproximityTrap.class);

	//
	// file structure
	//

	public int dbuffDuration;
	public Map<Integer, BigDecimal> dbuffs = new LinkedHashMap<>();
	public int forceDecloakDuration;
	public int resetDelay;
	public boolean showPerimeterLights;
	public int triggerDelay;
	public int triggerFilterTypeListID;
	public int triggerRange;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : triggerFilterTypeListID=" + first.triggerFilterTypeListID);
	}

}
