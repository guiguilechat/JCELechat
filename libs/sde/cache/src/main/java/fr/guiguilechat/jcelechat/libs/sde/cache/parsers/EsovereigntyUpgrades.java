package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EsovereigntyUpgrades {

	//
	// SDE loading
	//

	public static final MapIntLoader<EsovereigntyUpgrades> LOADER = new MapIntLoader<>(
			"sovereigntyUpgrades",
			EsovereigntyUpgrades.class);

	//
	// file structure
	//

	public static class Fuel {
		public int hourly_upkeep;
		public int startup_cost;
		public int type_id;

	}

	public Fuel fuel;

	public String mutually_exclusive_group;
	public int power_allocation;
	public int power_production;
	public int workforce_allocation;
	public int workforce_production;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : mutually_exclusive_group=" + first.mutually_exclusive_group);
	}
}
