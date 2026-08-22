package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EstationStandingsRestrictions {

	//
	// SDE loading
	//

	public static final IntMapLoader<EstationStandingsRestrictions> LOADER = new IntMapLoader<>(
			"stationStandingsRestrictions",
			EstationStandingsRestrictions.class);

	//
	// file structure
	//

	public Map<Integer, BigDecimal> services = new LinkedHashMap<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : services.size=" + first.services.size());
	}

}
