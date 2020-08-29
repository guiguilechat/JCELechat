package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.distances.Distances;

public interface IDenLicker {

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

	}

	public default LinkedHashMap<SolarSystem, Integer> list(SolarSystem source, Params params) {
		List<SolarSystem> targets = Reach.fromHS(source, params.addRegions.toArray(String[]::new)).stream()
				.filter(ss -> ss.truesec > 0.45 && ss.truesec <= 0.65).collect(Collectors.toList());
		SysIndex idx = new SysIndex(targets);
		int[][] distances = Distances.of(idx, null);
		int sourceIdx = idx.index(source);
		List<SolarSystem> list = list(idx, distances, sourceIdx);
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
