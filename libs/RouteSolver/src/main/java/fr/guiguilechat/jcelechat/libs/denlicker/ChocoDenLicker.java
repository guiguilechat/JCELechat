package fr.guiguilechat.jcelechat.libs.denlicker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.variables.InputOrder;
import org.chocosolver.solver.search.strategy.strategy.IntStrategy;
import org.chocosolver.solver.search.strategy.strategy.StrategiesSequencer;
import org.chocosolver.solver.variables.IntVar;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IDenLicker;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

public class ChocoDenLicker implements IDenLicker {

	public static final ChocoDenLicker INSTANCE = new ChocoDenLicker();

	@Override
	public List<SolarSystem> from(SysIndex idx, int[][] distances, int sourceIdx) {
		Model choco = new Model();
		// make the route as the indexes. first one must be the source.
		IntVar[] route = new IntVar[idx.size()];
		for (int i = 0; i < idx.size(); i++) {
			if (i == 0) {
				route[i] = choco.intVar("route_" + i, sourceIdx);
			} else {
				route[i] = choco.intVar("route_" + i, 0, idx.size() - 1, false);
			}
		}
		// force the ordering of the cycle : first index after the source is < last
		// index
		// that means we allow source, 1, 2 ; but not source, 2, 1.
		if (idx.size() > 2) {
			route[idx.size() - 1].lt(route[1]).post();
		}
		// each system appears only once
		IntVar one = choco.intVar(1);
		for (int i = 0; i < idx.size(); i++) {
			choco.count(i, route, one).post();
		}
		// create the positions for each system
		IntVar[] positions = new IntVar[idx.size()];
		// the index of the system in previous position
		IntVar[] previousIdx = new IntVar[idx.size()];
		for (int i = 0; i < idx.size(); i++) {
			if (i == sourceIdx) {
				positions[i] = choco.intVar(0);
				previousIdx[i] = route[idx.size() - 1];
			} else {
				positions[i] = choco.intVar(idx.system(i).name + "_index", 1, idx.size() - 1, false);
				choco.element(choco.intVar(i), route, positions[i], 0).post();
				previousIdx[i] = choco.intVar(0, idx.size() - 1, false);
				choco.element(previousIdx[i], route, positions[i], 1).post();
			}
		}

		// work by edges. edge (i, j) is used if previous(i)=j or previous(j)=i.
		IntVar[] edgeUsed = new IntVar[idx.size() * (idx.size() - 1) / 2];
		int[] edgeLength = new int[edgeUsed.length];
		for (int i = 1; i < idx.size(); i++) {
			for (int j = 0; j < i; j++) {
				// edge(1,0)=0
				// edge(2,0)=1
				// edge(2.1)=2
				int edgeIdx = i * (i - 1) / 2 + j;
				edgeLength[edgeIdx] = distances[i][j];
				edgeUsed[edgeIdx] = choco
						.boolVar(idx.system(i).name + "-" + idx.system(j).name + " [" + edgeLength[edgeIdx] + "]");
				edgeUsed[edgeIdx].eq(previousIdx[i].eq(j).or(previousIdx[j].eq(i))).post();
			}
		}

		// place constraints on the clusters.
		// Let C be a sub-cluster of systems.
		// Then there are at least two edges that exit this cluster that are used.
		// Also there are at most 2×size of this clusters edges that exit that
		// cluster.
		// since with n systems, it means there are 2^n - 2 possible clusters (-2 to
		// remove empty and full clusters) ; then we can't add constraints for 2^n .
		// Instead, we create the clusters of systems that are close together, by
		// aggregating the systems that are close to a cluster by a distance less
		// than the median edge distance.


		int medianEdge = IntStream.of(edgeLength).sorted().skip(edgeLength.length / 2).findFirst().getAsInt();
		// each cluster is defined by the index of the system with lower index in
		// that cluster.
		int[] clusters = new int[idx.size()];
		// we start with each system being its own cluster
		for (int i = 0; i < clusters.length; i++) {
			clusters[i] = i;
		}
		// first find the parent, if exists, for all systems.
		for (int i = 0; i < clusters.length; i++) {
			for (int j = i + 1; j < clusters.length; j++) {
				if (distances[i][j] < medianEdge && clusters[j] > i) {
					clusters[j] = i;
				}
			}
		}
		// then resolve the root of the parent for all systems.
		// recursive proof : since the systems before the i th are resolved to the
		// smallest one of the cluster, and the parent of a system
		// is lower index, then replacing the parent of system i by the parent of
		// its parent resolves to the smallest system of the cluster.
		for (int i = 0; i < clusters.length; i++) {
			clusters[i] = clusters[clusters[i]];
		}
		// now we have each system resolved to the smallest index of its cluster.

		// find a good min and max values for the objective.
		int sumLowEdges = IntStream.of(edgeLength).sorted().limit(idx.size()).sum();
		int sumHighEdges = IntStream.of(edgeLength).boxed().sorted((i, j) -> j - i).mapToInt(i -> i).limit(idx.size())
				.sum();
		int sumLowProx = 0;
		int sumHigProx = 0;
		for (int i = 0; i < distances.length; i++) {
			int further = 0, closer = Integer.MAX_VALUE;
			for (int j = 0; j < distances.length; j++) {
				if (i != j) {
					int distance = distances[i][j];
					if (distance > further) {
						further = distance;
					}
					if (distance < closer) {
						closer = distance;
					}
				}
			}
			sumLowProx += closer;
			sumHigProx += further;
		}
		System.err.println("sum N edges low " + sumLowEdges + " high " + sumHighEdges);
		System.err.println("sum prox low " + sumLowProx + " high " + sumHigProx);
		IntVar totalDist = choco.intVar("totaldist", Math.max(sumLowEdges, sumLowProx), Math.min(sumHighEdges, sumHigProx));
		choco.scalar(edgeUsed, edgeLength, "=", totalDist).post();
		choco.sum(edgeUsed, "=", idx.size()).post();

		Solver solver = choco.getSolver();

		IntVar[] edges_by_length_desc = IntStream.range(0, edgeLength.length).boxed()
				.sorted((i, j) -> edgeLength[j] - edgeLength[i]).map(i -> edgeUsed[i]).toArray(IntVar[]::new);
		IntStrategy removeHighEdges = Search.inputOrderLBSearch(edges_by_length_desc);
		IntStrategy nextRouteFast = Search.inputOrderLBSearch(route);
		IntStrategy nextRouteClosest = Search.intVarSearch(new InputOrder<>(choco), var -> {
			// find the actual index of the variable
			int varIdx = 0;
			for (; varIdx < route.length && route[varIdx] != var; varIdx++) {
			}
			int previous = route[varIdx - 1].getValue();
			int ret = 0;
			int dist = Integer.MAX_VALUE;
			for (int j = 0; j < distances.length; j++) {
				if (var.contains(j) && distances[previous][j] < dist) {
					dist = distances[previous][j];
					ret = j;
				}
			}
			return ret;
		}, route);

		StrategiesSequencer optimalSearch = new StrategiesSequencer(removeHighEdges, nextRouteClosest,
				Search.defaultSearch(choco));
		// FindAndProve<IntVar> fap = new
		// FindAndProve<>(choco.retrieveIntVars(true), nextRouteClosest,
		// optimalSearch);
		// solver.setSearch(fap);
		solver.setSearch(optimalSearch);

		solver.showSolutions();
		solver.showDecisions();
		solver.showContradiction();
		Solution solution = solver.findOptimalSolution(totalDist, false);
		// Solution solution = solver.findSolution();
		return Stream.of(route).map(iv -> idx.system(solution.getIntVal(iv))).collect(Collectors.toList());
	}

}
