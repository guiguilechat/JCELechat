package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.HashSet;
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

	public default List<SolarSystem> from(SolarSystem source, Params params) {
		List<SolarSystem> targets = ReachableRegionHs.around(source, params.addRegions.toArray(String[]::new)).stream()
				.filter(ss -> ss.truesec > 0.45 && ss.truesec <= 0.65).collect(Collectors.toList());
		SysIndex idx = new SysIndex(targets);
		int[][] distances = Distances.of(idx);
		int sourceIdx = idx.index(source);
		return from(idx, distances, sourceIdx);
	}

	public List<SolarSystem> from(SysIndex idx, int[][] distances, int sourceIdx);

}
