package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EcontrolTowerResources {

	//
	// SDE loading
	//

	public static final IntMapLoader<EcontrolTowerResources> LOADER = new IntMapLoader<>(
			"controlTowerResources",
			EcontrolTowerResources.class,
			Set.of("resources"));

	//
	// file structure
	//

	public static class Resource {
		/** {@link Efactions} */
		public int factionID;
		public BigDecimal minSecurityLevel;
		public int purpose;
		public int quantity;
		/** {@link Etypes} */
		public int resourceTypeID;
	}

	public List<Resource> resources = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : resources=" + first.resources.size());
	}
}
