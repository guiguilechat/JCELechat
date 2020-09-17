package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.distances.Distances;
import fr.guiguilechat.jcelechat.model.sde.locations.route.PredicateRouter;

public interface IRegionCycler {

	public static class Params {

		public Set<String> addRegions = new HashSet<>();

		public Params withRegion(String... regions) {
			addRegions.addAll(Arrays.asList(regions));
			return this;
		}

		public static Params empty() {
			return new Params();
		}

		public static Params regions(String... regions) {
			return new Params().withRegion(regions);
		}

		/**
		 * predicate on a system to check if we are allowed to jump in it.
		 */
		public Predicate<SolarSystem> allowedSystems = PredicateRouter.HSNOINVASION.predicate;

		public Params withAllowed(Predicate<SolarSystem> allowed) {
			allowedSystems = allowed;
			return this;
		}

		/**
		 * predicate on an allowed system to check if it is important.
		 */
		public Predicate<SolarSystem> importantSystems = null;

		public Params withImportant(Predicate<SolarSystem> important) {
			importantSystems = important;
			return this;
		}

	}

	public default LinkedHashMap<SolarSystem, Integer> list(SolarSystem source, Params params) {
		Predicate<SolarSystem> allowedPred = params.allowedSystems.and(s -> params.addRegions.contains(s.region));
		// first find all the systems that are reachable via allowed systems
		Set<SolarSystem> targets = Reach.from(source, allowedPred);
		// then keep only the important ones.
		if (params.importantSystems != null) {
			targets.removeIf(params.importantSystems.negate());
			// always accept the source as important
			targets.add(source);
		}
		// then reindex and make the distances matrix
		SysIndex idx = new SysIndex(targets);
		int[][] distances = Distances.of(idx, new PredicateRouter(allowedPred));
		int sourceIdx = idx.index(source);

		// call the actual implementation
		List<SolarSystem> list = list(idx, distances, sourceIdx);

		// then transform the list of systems into the linkedmap.
		LinkedHashMap<SolarSystem, Integer> ret = new LinkedHashMap<>();
		int lastIdx = idx.index(source);
		for (SolarSystem ss : list) {
			if (ss == source) {
				continue;
			}
			int index = idx.index(ss);
			ret.put(ss, distances[index][lastIdx]);
			lastIdx = index;
		}
		ret.put(source, distances[lastIdx][sourceIdx]);
		return ret;
	}

	/**
	 * return the list of systems for a circular route. The first one must be the
	 * source.
	 *
	 * @param idx
	 *          index of the systems to pass by
	 * @param distances
	 *          complete matrix of distances for each couple of systems
	 * @param sourceIdx
	 *          the index of the system to start the route and finish it.
	 * @return A route that passes by all the systems indexed, and try to minimize
	 *         the total distance.
	 */
	public List<SolarSystem> list(SysIndex idx, int[][] distances, int sourceIdx);

}
