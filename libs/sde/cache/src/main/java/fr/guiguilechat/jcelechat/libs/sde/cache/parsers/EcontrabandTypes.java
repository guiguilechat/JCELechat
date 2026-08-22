package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EcontrabandTypes {

	//
	// SDE loading
	//

	public static final MapIntLoader<EcontrabandTypes> LOADER = new MapIntLoader<>(
			"contrabandTypes",
			EcontrabandTypes.class);

	//
	// file structure
	//

	public static class FactionContraband {
		public BigDecimal attackMinSec;
		public BigDecimal confiscateMinSec;
		public BigDecimal fineByValue;
		public BigDecimal standingLoss;
	}

	/**
	 * key is {@link Efactions}
	 */
	public Map<Integer, FactionContraband> factions = new LinkedHashMap<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : factions=" + first.factions.size());
	}
}
