package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.route.PredicateRouter;
import fr.lelouet.tools.solver.IFondHamilton;
import fr.lelouet.tools.solver.SimpleGraph;

public interface IRegionCycler extends IFondHamilton {

	public static class Params {

		public Set<String> addRegions = new HashSet<>();

		public Params withRegion(String... regions) {
			addRegions.addAll(Arrays.asList(regions));
			return this;
		}

		public Params withRegion(Collection<String> regions) {
			addRegions.addAll(regions);
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

		public static final Predicate<SolarSystem> IMPORTANT_DENS = sys -> sys.truesec < 0.65;
		public static final Predicate<SolarSystem> IMPORTANT_REFUGES = sys -> sys.truesec < 0.95;

	}

	public default LinkedHashMap<SolarSystem, Integer> list(SolarSystem source, Params params) {
		Predicate<SolarSystem> allowedPred = params.allowedSystems
				.and(s -> s.region.equals(source.region) || params.addRegions.contains(s.region));
		// first find all the systems that are reachable via allowed systems
		Set<SolarSystem> targets = Reach.from(source, allowedPred);
		SimpleGraph<SolarSystem> graph = new SimpleGraph<>(Comparator.comparing(s -> s.name));
		for (SolarSystem ss : targets) {
			for (String adjName : ss.adjacentSystems) {
				SolarSystem adj = SolarSystem.getSystem(adjName);
				if (targets.contains(adj)) {
					graph.addEdge(ss, adj);
				}
			}
		}
		return this.solve(source, graph, params.importantSystems);
	}

}
