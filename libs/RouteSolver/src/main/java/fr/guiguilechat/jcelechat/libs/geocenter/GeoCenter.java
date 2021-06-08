package fr.guiguilechat.jcelechat.libs.geocenter;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.locations.Invasions;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.Reach;
import fr.lelouet.tools.solver.SimpleGraph;
import fr.lelouet.tools.solver.SimpleGraph.AdjMatrix;

public class GeoCenter {

	/**
	 * evaluation of a {@link SolarSystem} to the systems inside the frontier.
	 * Contains several distance metrics options.
	 *
	 */

	public static class SysEval {
		public SysEval(SolarSystem ss) {
			system = ss;
		}

		public final SolarSystem system;

		/**
		 * sum of the distances to the systems
		 */
		public double dist1;

		/**
		 * root of the sum of the square distances.
		 */
		public double dist2;

		/**
		 * highest distance.
		 */
		public int distInf;
	}

	/**
	 * create a list of {@link SysEval} for all the systems reachable from a
	 * source
	 *
	 * @param source
	 *          the system to start the exploration
	 * @param systemAccept
	 *          the predicate to accept to pass by a solar system when exploring
	 * @return a new list
	 *
	 *         basically, this first explores the whole systems reachable from the
	 *         source, then create a graph representation, and evaluates the
	 *         systems in that graph
	 */
	public static List<SysEval> evaluate(SolarSystem source, Predicate<SolarSystem> systemAccept) {
		if (systemAccept == null) {
			systemAccept = ss -> true;
		}
		Set<SolarSystem> targets = Reach.from(source, systemAccept);
		SimpleGraph<SolarSystem> graph = new SimpleGraph<>(Comparator.comparing(s -> s.name));
		for (SolarSystem ss : targets) {
			for (String adjName : ss.adjacentSystems) {
				SolarSystem adj = SolarSystem.getSystem(adjName);
				if (targets.contains(adj)) {
					graph.addEdge(ss, adj);
				}
			}
		}
		AdjMatrix<SolarSystem> mat = graph.toMatrix();
		int[][] distances = mat.distances();
		return IntStream.range(0, distances.length).mapToObj(i -> {
			SysEval eval = new SysEval(mat.index.item(i));
			eval.dist1 = 1.0 * IntStream.of(distances[i]).sum() / distances.length;
			eval.distInf = IntStream.of(distances[i]).max().orElse(0);
			eval.dist2 = Math.sqrt(IntStream.of(distances[i]).mapToDouble(d -> d * d).sum()) / Math.sqrt(distances.length);
			return eval;
		}).collect(Collectors.toList());
	}

	//
	// frontier = region
	//

	public static Predicate<SolarSystem> frontierRegionHS(SolarSystem source) {
		return s -> s.isHS() && s.region.equals(source.region);
	}

	/**
	 * evaluate the systems in the same region than the source, and in HS.
	 *
	 * @param sysname
	 * @return
	 */
	public static List<SysEval> evalRegionHS(String sysname) {
		SolarSystem source = SolarSystem.getSystem(sysname);
		List<SysEval> l = evaluate(source, frontierRegionHS(source));
		return l;
	}

	//
	// frontier = pirate regions.
	//

	public static Predicate<SolarSystem> frontierPiratesHS(SolarSystem source) {
		Set<String> regions = new HashSet<>(Region.EMPIRE_FACTIONS.of(source).regions());
		return s -> s.isHS() && regions.contains(s.region);
	}

	/**
	 * evaluate the systems in the same pirate region than the source , and in HS.
	 *
	 * @param sysname
	 * @return
	 */
	public static List<SysEval> evalPirateHS(String sysname) {
		SolarSystem source = SolarSystem.getSystem(sysname);
		List<SysEval> l = evaluate(source, frontierPiratesHS(source));
		return l;
	}

	//
	// frontier = pirate regions and adjacent constels, in HS
	//

	/**
	 * make a predicate, based on a source system, that returns true when a target
	 * systel is in HS, in a constellation reachable from the source, and in same
	 * pirat type system.
	 *
	 * @param source
	 *          a system, not null.
	 * @return a new predicate.
	 */
	public static Predicate<SolarSystem> frontierPiratesAdjHS(SolarSystem source) {
		Set<String> constels = Region.EMPIRE_FACTIONS.of(source).regions().stream().map(Region::getRegion)
				.flatMap(region -> Stream.concat(region.constellations.stream(), region.adjacentConstellations.stream()))
				.collect(Collectors.toSet());
		return s -> s.isHS() && constels.contains(s.constellation);
	}

	/**
	 * evaluate the systems in the same pirate region than the source or an
	 * adjacent constel, and in HS.
	 *
	 * @param sysname
	 * @return
	 */
	public static List<SysEval> evalPirateAdjHS(String sysname) {
		SolarSystem source = SolarSystem.getSystem(sysname);
		List<SysEval> l = evaluate(source, frontierPiratesAdjHS(source));
		return l;
	}

	//
	// frontier = pirate regions and adjacent constels, in HS
	// Also limit to friendly invaded systems.
	//

	public static Predicate<SolarSystem> frontierPiratesAdjHS(SolarSystem source, boolean allowTrigs,
			boolean allowEdencom) {
		Set<String> constels = Region.EMPIRE_FACTIONS.of(source).regions().stream().map(Region::getRegion)
				.flatMap(region -> Stream.concat(region.constellations.stream(), region.adjacentConstellations.stream()))
				.collect(Collectors.toSet());
		Set<SolarSystem> invaded = Invasions.INSTANCE.getDangerousHSSystems(allowTrigs, allowEdencom);
		return s -> s.isHS() && constels.contains(s.constellation) && !invaded.contains(s);
	}

	/**
	 * evaluate the systems in the same pirate region than the source or an
	 * adjacent constel, and in HS. reject systems that are invaded and not
	 * controlled by edencom
	 *
	 * @param sysname
	 * @return
	 */
	public static List<SysEval> evalPirateAdjHSEdencom(String sysname) {
		SolarSystem source = SolarSystem.getSystem(sysname);
		List<SysEval> l = evaluate(source, frontierPiratesAdjHS(source, false, true));
		return l;
	}

	/**
	 * sort the list of evaluations, then print them.
	 *
	 * @param l
	 * @param cmp
	 */

	public static void print(List<SysEval> l, Comparator<SysEval> cmp) {
		if (cmp == null) {
			cmp= DIST1DIST2DISTINF;
		}
		Locale local = Locale.ENGLISH;
		l.sort(cmp);
		System.out.println("rank\tsystem\tconstel\tregion\tdist1\tdist2\tdistinf");
		int index = 1;
		for (SysEval e : l) {
			System.out.println("" + index + "\t" + e.system + "\t" + e.system.constellation + "\t" + e.system.region + "\t"
					+ String.format(local, "%.2f", e.dist1) + "\t"
					+ String.format(local, "%.2f", e.dist2) + "\t" + e.distInf);
			index++;
		}
	}

	public static final Comparator<SysEval> DIST1DIST2DISTINF = Comparator
			.comparing((Function<SysEval, Double>) t -> t.dist1)
			.thenComparing(eval -> eval.dist2).thenComparing(eval->eval.distInf);

}
