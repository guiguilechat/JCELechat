package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import fr.guiguilechat.jcelechat.libs.sde.cache.loaders.MapIntLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSystem;

public class EmapSecondarySuns extends InSystem {

	//
	// SDE loading
	//

	public static final MapIntLoader<EmapSecondarySuns> LOADER =
			new MapIntLoader<>(
					"mapSecondarySuns",
					EmapSecondarySuns.class);

	//
	// file structure
	//

	public int effectBeaconTypeID;

	// test

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : effectBeaconTypeID=" + first.effectBeaconTypeID);
	}

}
