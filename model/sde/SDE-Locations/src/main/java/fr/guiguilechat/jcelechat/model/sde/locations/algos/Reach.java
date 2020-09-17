package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class Reach {

	/**
	 * get all the systems that are reachable from the source, following a
	 * predicate. Algo is basically BFS
	 *
	 * @param source
	 *          solarsystem to start the exploration
	 * @param accept
	 *          the predicate on the system we allow to jump into
	 * @return a set containing the source(always) and all the systems that are
	 *         reachable through jumps via systems being accepted
	 */
	public static Set<SolarSystem> from(SolarSystem source, Predicate<SolarSystem> accept) {
		// systems that are reachable through Hs and by the region only
		Set<SolarSystem> reachable = new HashSet<>(Arrays.asList(source));
		Set<SolarSystem> futureLoop = new HashSet<>(Arrays.asList(source));
		while (!futureLoop.isEmpty()) {
			Set<SolarSystem> nextLayer = new HashSet<>();
			for (SolarSystem exploreSyst : futureLoop) {
				for (String sysName : exploreSyst.adjacentSystems) {
					SolarSystem adjacent = SolarSystem.getSystem(sysName);
					if (accept.test(adjacent) && reachable.add(adjacent)) {
						nextLayer.add(adjacent);
					}
				}
			}
			futureLoop = nextLayer;
		}
		return reachable;
	}

}
