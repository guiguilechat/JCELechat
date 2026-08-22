package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EsystemDbuffEmitters {

	//
	// SDE loading
	//

	public static final MapIntLoader<EsystemDbuffEmitters> LOADER = new MapIntLoader<>(
			"systemDbuffEmitters",
			EsystemDbuffEmitters.class);

	//
	// file structure
	//

	public Map<Integer, BigDecimal> dbuffs = new LinkedHashMap<>();
	public int duration;
	public boolean excludeProtected;
	public int interval;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : duration=" + first.duration);
	}

}
