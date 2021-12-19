package fr.guiguilechat.jcelechat.model.sde.compile;

import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeDetails;
import fr.guiguilechat.jcelechat.model.sde.hierarchy.TypeHierarchy;
import fr.guiguilechat.jcelechat.model.sde.loaders.MixedLoader;

public class MixedLoaderTest {

	public void testLoader() {
		new MixedLoader();
		TypeHierarchy test = MixedLoader.load();
		TypeDetails abaddon = test.typeID2Details.get(24692);
		System.out.println("abaddon mass is " + abaddon.mass);
	}

}
