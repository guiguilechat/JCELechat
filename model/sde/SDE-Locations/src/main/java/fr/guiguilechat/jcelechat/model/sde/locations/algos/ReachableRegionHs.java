package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class ReachableRegionHs {

	/**
	 * get all the systems that are reachable from the source, only through jumps
	 * in the same region and in HS
	 *
	 * @param source
	 *          solarsystem to start the exploration
	 * @return a set containing the source and all the systems of the region that
	 *         are reachable through jumps in HS and in the same region.
	 */
	public static Set<SolarSystem> around(SolarSystem source, String... otherRegions) {
		Set<String> regions = new HashSet<>();
		regions.add(source.region);
		if (otherRegions != null) {
			regions.addAll(Arrays.asList(otherRegions));
		}
		// systems that are reachable through Hs and by the region only
		Set<SolarSystem> reachable = new HashSet<>(Arrays.asList(source));
		Set<SolarSystem> futureLoop = new HashSet<>(Arrays.asList(source));
		while (!futureLoop.isEmpty()) {
			Set<SolarSystem> nextLayer = new HashSet<>();
			for (SolarSystem exploreSyst : futureLoop) {
				for (String sysName : exploreSyst.adjacentSystems) {
					SolarSystem adjacent = SolarSystem.getSystem(sysName);
					if (adjacent.isHS() && regions.contains(adjacent.region) && reachable.add(adjacent)) {
						nextLayer.add(adjacent);
					}
				}
			}
			futureLoop = nextLayer;
		}
		return reachable;
	}

}
