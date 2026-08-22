package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EappliedProximityEffects {

	//
	// SDE loading
	//

	public static final MapIntLoader<EappliedProximityEffects> LOADER = new MapIntLoader<>(
			"appliedProximityEffects",
			EappliedProximityEffects.class);

	//
	// file structure
	//
	public LinkedHashMap<Integer, BigDecimal> dbuffs = new LinkedHashMap<>();
	public int delaySeconds;
	public int radius;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : dbuffs.size=" + first.dbuffs.size() + " delaySeconds=" + first.delaySeconds
				+ " radius=" + first.radius);
	}

}
