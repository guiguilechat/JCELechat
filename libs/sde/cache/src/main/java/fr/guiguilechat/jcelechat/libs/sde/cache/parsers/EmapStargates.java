package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSystem;

public class EmapStargates extends InSystem {

	//
	// SDE loading
	//

	public static final IntMapLoader<EmapStargates> LOADER = new IntMapLoader<>(
			"mapStargates",
			EmapStargates.class);

	//
	// file structure
	//

	public static class Destination {
		/** {@link EmapSolarSystems} */
		public int solarSystemID;
		/** {@link EmapStargates} */
		public int stargateID;
	}

	public Destination destination;

	public double distance() {
		return position.distance(LOADER.yaml().get(destination.stargateID).position);
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println(
				"first : solarSystemID=" + first.solarSystemID + " target=" + first.destination.stargateID);
	}

	public static double maxDistance() {
		return EmapStargates.LOADER.yaml().load().entrySet().stream()
				.filter(e -> e.getKey() < e.getValue().destination.stargateID)
				.map(Entry::getValue)
				.mapToDouble(EmapStargates::distance)
				.max().getAsDouble();
	}
}
