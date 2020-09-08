package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.Invasions;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class Reach {

	/**
	 * get all the systems that are reachable from the source, following a
	 * predicate. Algo is basically BFS
	 *
	 * @param source
	 *          solarsystem to start the exploration
	 * @return a set containing the source and all the systems of the region that
	 *         are reachable through jumps in HS and in the same region.
	 */
	public static Set<SolarSystem> from(SolarSystem source, Predicate<SolarSystem> filter) {
		// systems that are reachable through Hs and by the region only
		Set<SolarSystem> reachable = new HashSet<>(Arrays.asList(source));
		Set<SolarSystem> futureLoop = new HashSet<>(Arrays.asList(source));
		while (!futureLoop.isEmpty()) {
			Set<SolarSystem> nextLayer = new HashSet<>();
			for (SolarSystem exploreSyst : futureLoop) {
				for (String sysName : exploreSyst.adjacentSystems) {
					SolarSystem adjacent = SolarSystem.getSystem(sysName);
					if (filter.test(adjacent) && reachable.add(adjacent)) {
						nextLayer.add(adjacent);
					}
				}
			}
			futureLoop = nextLayer;
		}
		return reachable;
	}

	public static Set<SolarSystem> fromHS(SolarSystem source, Set<SolarSystem> avoid, String... otherRegions) {
		Set<String> regions = new HashSet<>();
		regions.add(source.region);
		if (otherRegions != null) {
			regions.addAll(Arrays.asList(otherRegions));
		}
		Set<SolarSystem> avoidf = avoid == null ? Collections.emptySet() : avoid;
		Predicate<SolarSystem> pred = avoid == null
				? ss -> ss.isHS() && regions.contains(ss.region)
						: ss -> ss.isHS() && regions.contains(ss.region) && !avoidf.contains(ss);
						return from(source, pred);
	}

	/**
	 * get the systems using BFS , avoid systems that are not HS, not in the
	 * source region of an additional region, or in the dangerous status of the
	 * invasions
	 *
	 * @param source
	 *          system to start the exploration
	 * @param addRegions
	 *          regions besides the source to accept the systems
	 * @return a new set of systems.
	 */
	public static Set<SolarSystem> fromHS(SolarSystem source, String... addRegions) {
		return fromHS(source, Invasions.INSTANCE.getPointSystems(false, false), addRegions);
	}

}
