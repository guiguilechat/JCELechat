package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EplanetResources {

	//
	// SDE loading
	//

	public static final IntMapLoader<EplanetResources> LOADER = new IntMapLoader<>(
			"planetResources",
			EplanetResources.class);

	//
	// file structure
	//

	public int power;

	public static class Reagent {
		public int amount_per_cycle;
		public int cycle_period;
		public int secured_capacity;
		public int type_id;
		public int unsecured_capacity;

	}
	public Reagent reagent;
	public int workforce;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : power=" + first.power);
	}
}
