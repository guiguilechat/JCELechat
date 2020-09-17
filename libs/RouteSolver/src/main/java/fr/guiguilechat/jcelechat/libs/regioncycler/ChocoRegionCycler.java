package fr.guiguilechat.jcelechat.libs.regioncycler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

public class ChocoRegionCycler implements IRegionCycler {

	private static final Logger logger = LoggerFactory.getLogger(ChocoRegionCycler.class);

	public static final ChocoRegionCycler INSTANCE = new ChocoRegionCycler();

	@Override
	public List<SolarSystem> list(SysIndex idx, int[][] distances, int sourceIdx) {
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
		choco.allDifferent(route).post();
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
						.boolVar(idx.system(i).name + "-" + idx.system(j).name + "[" + edgeLength[edgeIdx] + "]");
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

		int[] clusters = createClusters(distances, (int) Math.ceil(Math.sqrt(idx.size())));
		int[] clustersRoot = IntStream.of(clusters).distinct().toArray();
		int minDistFromClusters = 0;
		if (clustersRoot.length > 1) {
			for (int clusterRoot : clustersRoot) {
				int[] clusterSystems = IntStream.range(clusterRoot, idx.size()).filter(sysi -> clusters[sysi] == clusterRoot)
						.toArray();
				logger.debug("cluster [" + idx.system(clusterRoot) + "] systems="
						+ IntStream.of(clusterSystems).mapToObj(idx::system).collect(Collectors.toList()));
				Set<IntVar> exitEdges = new HashSet<>();
				// for each couple i,j with i in the cluster and j not in the cluster.
				for (int i = 0; i < distances.length; i++) {
					if (clusters[i] == clusterRoot) {
						for (int j = 0; j < distances.length; j++) {
							if (clusters[j] != clusterRoot) {
								// same edge value as above
								int edgeIdx = j < i ? i * (i - 1) / 2 + j : j * (j - 1) / 2 + i;
								exitEdges.add(edgeUsed[edgeIdx]);
							}
						}
					}
				}
				logger.debug("cluster [" + idx.system(clusterRoot) + "] exits=" + exitEdges);
				IntVar exitEdgesUsed = choco.intVar("cl_" + idx.system(clusterRoot).name + ".exitEdges", 2, distances.length);
				choco.sum(exitEdges.toArray(IntVar[]::new), "=", exitEdgesUsed).post();
			}

			// generate the min distance for systems internal to a cluster, and
			// between
			// system out of a cluster, for each cluster main system.
			int[] clusterMinIntra = new int[idx.size()];
			int[] clusterMinInter = new int[idx.size()];
			for (int i = 0; i < idx.size(); i++) {
				clusterMinIntra[i] = clusterMinInter[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < idx.size(); i++) {
				for (int j = 0; j < i; j++) {
					int cl1 = clusters[i], cl2 = clusters[j], dist = distances[i][j];
					if (cl1 == cl2) {
						// update intra if needed
						if (dist < clusterMinIntra[cl1]) {
							clusterMinIntra[cl1] = dist;
						}
					} else {
						// update intra for both if needed
						if (dist < clusterMinInter[cl1]) {
							clusterMinInter[cl1] = dist;
						}
						if (dist < clusterMinInter[cl2]) {
							clusterMinInter[cl2] = dist;
						}
					}
				}
			}
			// now we can sum : for each cluster, the number of systems inside, -1 ;
			// time the inter distance, plus the intra distance.
			// that sum gives us a new minimum for distances
			for (int clusterRoot : clustersRoot) {
				int nb = (int) IntStream.of(clusters).filter(i -> i == clusterRoot).count();
				int inter = clusterMinInter[clusterRoot];
				int intra = 0;
				if (nb > 1) {
					intra = (nb - 1) * clusterMinIntra[clusterRoot];
				}
				logger.debug("cluster " + idx.system(clusterRoot) + "(" + nb + ") add inter=" + inter + " intra=" + intra);
				minDistFromClusters += inter + intra;
			}
		}

		// find a good min and max values for the objective.
		// get the nth lowest, highest edges with n being the number of systems.
		int sumLowEdges = IntStream.of(edgeLength).sorted().limit(idx.size()).sum();
		int sumHighEdges = IntStream.of(edgeLength).boxed().sorted((i, j) -> j - i).mapToInt(i -> i).limit(idx.size())
				.sum();
		// also, for each system the highest/lowest proximity with another system
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

		int lb = Math.max(sumLowEdges, sumLowProx);
		if (minDistFromClusters > lb) {
			lb = minDistFromClusters;
		}
		int ub = Math.min(sumHighEdges, sumHigProx);
		logger.debug("variable objectif from " + lb + " (prox=" + sumLowProx + " edges=" + sumLowEdges + " clusters="
				+ minDistFromClusters + ") to " + ub + " (prox=" + sumHigProx + " edges=" + sumHighEdges + ")");

		IntVar totalDist = choco.intVar("totaldist", lb, ub);
		choco.scalar(edgeUsed, edgeLength, "=", totalDist).post();
		choco.sum(edgeUsed, "=", idx.size()).post();

		boolean exit = false;
		if (exit) {
			return null;
		}

		Solver solver = choco.getSolver();

		// remove high edges strategy removes all the edges, from the highest to the
		// lowest edge distance excluxed.
		int lowestEdge = IntStream.of(edgeLength).sorted().limit(1).sum();
		IntVar[] edges_by_length_desc = IntStream.range(0, edgeLength.length).boxed()
				.filter(i -> edgeLength[i] > lowestEdge)
				.sorted((i, j) -> edgeLength[j] - edgeLength[i]).map(i -> edgeUsed[i]).toArray(IntVar[]::new);
		IntStrategy removeHighEdges = Search.inputOrderLBSearch(edges_by_length_desc);
		// IntStrategy nextRouteFast = Search.inputOrderLBSearch(route);
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
		// solver.showDecisions();
		// solver.showContradiction();
		Solution solution = solver.findOptimalSolution(totalDist, false);
		// Solution solution = solver.findSolution();
		return Stream.of(route).map(iv -> idx.system(solution.getIntVal(iv))).collect(Collectors.toList());
	}

	public static int[] createClusters(int[][] distances, int maxClusters) {
		int[] clustering = new int[distances.length];
		// we start with each system being its own cluster
		for (int i = 0; i < clustering.length; i++) {
			clustering[i] = i;
		}
		int[] edgesLength = Stream.of(distances).flatMapToInt(iArr -> IntStream.of(iArr)).sorted().distinct()
				.filter(i -> i > 0).toArray();
		logger.debug("clustering " + distances.length + " systems into max " + maxClusters + " clusters ; edges length are "
				+ IntStream.of(edgesLength).boxed().collect(Collectors.toList()));
		long nbClusters = IntStream.of(clustering).distinct().count();
		for (int edgeIndex = 0; edgeIndex < edgesLength.length && nbClusters > maxClusters; edgeIndex++) {
			int edgeLength = edgesLength[edgeIndex];
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < i; j++) {
					if (distances[i][j] == edgeLength && clustering[i] != clustering[j]) {
						// we merge the two clusters
						int replaced = Math.max(clustering[i], clustering[j]);
						int newval = Math.min(clustering[i], clustering[j]);
						for (int k = 0; k < clustering.length; k++) {
							if (clustering[k] == replaced) {
								clustering[k] = newval;
							}
						}
					}
				}
			}
			nbClusters = IntStream.of(clustering).distinct().count();
			logger.debug("after mergin all clusters distant by " + edgeLength + " ; keep " + nbClusters + " clusters");
		}
		return clustering;
	}

}
