package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EindustryModifierSources {

	//
	// SDE loading
	//

	public static final IntMapLoader<EindustryModifierSources> LOADER = new IntMapLoader<>(
			"industryModifierSources",
			EindustryModifierSources.class);

	//
	// file structure
	//

	public static class ActivityModifiers {

		public static class PropertyModifier {
			public int dogmaAttributeID;
			public int filterID;
		}

		public PropertyModifier[] cost,
				material,
				time;

	}

	public ActivityModifiers copying,
			invention,
			manufacturing,
			reaction,
			researchMaterial,
			researchTime;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : modifiescopying=" + (first.copying != null));
	}

}
