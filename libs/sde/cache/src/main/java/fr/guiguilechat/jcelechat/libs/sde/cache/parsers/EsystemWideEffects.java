package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EsystemWideEffects {

	//
	// SDE loading
	//

	public static final MapIntLoader<EsystemWideEffects> LOADER = new MapIntLoader<>(
			"systemWideEffects",
			EsystemWideEffects.class);

	//
	// file structure
	//


	public Map<Integer, BigDecimal> dbuffs = new LinkedHashMap<>();
	public int eligibleTypeListID;
	public int environmentTypeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : dbuffs.size=" + first.dbuffs.size());
	}

}
