package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.HashMap;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

@SuppressWarnings("serial")
public class Emasteries extends HashMap<Integer, Integer[]> {

	//
	// SDE loading
	//

	public static final IntMapLoader<Emasteries> LOADER = new IntMapLoader<>(
			"masteries",
			Emasteries.class,
			Set.of("0"));

	//
	// file structure
	//

	// nothing, it's a map ! I have no fucking idea what it represents. And I don't
	// care.

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : size=" + first.size());
	}
}
