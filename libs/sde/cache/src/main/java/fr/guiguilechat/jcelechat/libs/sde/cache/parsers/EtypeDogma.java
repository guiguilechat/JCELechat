package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EtypeDogma {

	//
	// SDE loading
	//

	public static final IntMapLoader<EtypeDogma> LOADER = new IntMapLoader<>(
			"typeDogma",
			EtypeDogma.class,
			Set.of("dogmaAttributes"));

	//
	// file structure
	//

	public static class EAttributes{
		/** {@link EdogmaAttributes} */
		public int attributeID;
		public BigDecimal value;

	}

	public static class Eeffects{
		/** {@link EdogmaEffects} */
		public int effectID;
		public boolean isDefault;

	}

	public List<EAttributes> dogmaAttributes = new ArrayList<>();

	public List<Eeffects> dogmaEffects = new ArrayList<>();

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : attributes=" + first.dogmaAttributes.size() + " effects=" + first.dogmaEffects.size());
	}

}
