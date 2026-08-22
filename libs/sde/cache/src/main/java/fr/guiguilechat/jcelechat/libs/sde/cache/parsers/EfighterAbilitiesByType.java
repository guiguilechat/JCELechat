package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;

public class EfighterAbilitiesByType {

	//
	// SDE loading
	//

	public static final MapIntLoader<EfighterAbilitiesByType> LOADER = new MapIntLoader<>(
			"fighterAbilitiesByType",
			EfighterAbilitiesByType.class);

	//
	// file structure
	//

	public static class AbilitySlot {
		public int abilityID;

		public static class Charge {
			public int chargeCount;
			public int rearmTimeSeconds;
		}

		public Charge charges;
		public int cooldownSeconds;
	}

	public AbilitySlot abilitySlot0;
	public AbilitySlot abilitySlot1;
	public AbilitySlot abilitySlot2;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : abilitySlot0.abilityID=" + first.abilitySlot0.abilityID);
	}

}
