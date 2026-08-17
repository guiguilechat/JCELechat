package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EmetenoxMoonDrill {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmetenoxMoonDrill> LOADER = new IntMapLoader<>(
			"metenoxMoonDrill",
			EmetenoxMoonDrill.class);

	//
	// file structure
	//

	public int miningCycleTime;
	public BigDecimal miningEfficiency;
	public int reagentsConsumedPerCycle;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : miningEfficiency=" + first.miningEfficiency);
	}

}
