package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EagentsInSpace {

	//
	// SDE loading
	//

	public static final IntMapLoader<EagentsInSpace> LOADER = new IntMapLoader<>(
			"agentsInSpace",
			EagentsInSpace.class,
			Set.of("typeID"));

	//
	// file structure
	//

	public int dungeonID;
	/** {@link EmapSolarSystems} */
	public int solarSystemID;
	/** no idea */
	public int spawnPointID;
	/** {@link Etypes} */
	public int typeID;

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : dungeonid=" + first.dungeonID + " solarsystem=" + first.solarSystemID);
	}

}
