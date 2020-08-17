package fr.guiguilechat.jcelechat.libs.evestager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntValueSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.InputOrder;
import org.chocosolver.solver.search.strategy.strategy.IntStrategy;
import org.chocosolver.solver.variables.IntVar;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionStager;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

public class ChocoStager implements IRegionStager {

	public static final ChocoStager INSTANCE = new ChocoStager();

	@Override
	public List<SolarSystem> around(SysIndex idx, int[][] jumps, int clusters, boolean useSquareDistance) {
		Model choco = new Model();
		int highestDist = 0;
		for (int i = 0; i < jumps.length; i++) {
			for (int j = i + 1; j < jumps.length; j++) {
				highestDist = Math.max(highestDist, jumps[i][j]);
			}
		}
		IntVar[] clusterCenters = new IntVar[clusters];
		int[] allSys = IntStream.rangeClosed(0, idx.size()).toArray();
		for (int i = 0; i < clusters; i++) {
			clusterCenters[i] = choco.intVar("cluster_" + i + ".center", allSys);
			if (i != 0) {
				clusterCenters[i].gt(clusterCenters[i - 1]).post();
			}
		}
		// clusterDistances[i][j] is the distance from system i to the center of the
		// cluster j
		IntVar[][] clusterDistances = new IntVar[idx.size()][];
		// closest[i] is the distance from system i to the closest center of a
		// cluster
		IntVar[] closest = new IntVar[idx.size()];
		for (int i = 0; i < idx.size(); i++) {
			clusterDistances[i] = new IntVar[clusters];
			for (int cl = 0; cl < clusters; cl++) {
				clusterDistances[i][cl] = choco.intVar(idx.system(i).name + ".dist_" + cl, 0, highestDist);
				choco.element(clusterDistances[i][cl], jumps[i], clusterCenters[cl]).post();
			}
			closest[i] = choco.intVar(idx.system(i).name + ".closest", 0, highestDist);
			choco.min(closest[i], clusterDistances[i]).post();
		}
		IntVar totalDist;
		if (useSquareDistance) {
			IntVar[] closestSquare = new IntVar[idx.size()];
			for (int i = 0; i < idx.size(); i++) {
				closestSquare[i] = choco.intVar("", 0, highestDist * highestDist);
				choco.square(closestSquare[i], closest[i]).post();
			}
			totalDist = choco.intVar("sum_square_dist", 0, idx.size() * highestDist * highestDist / 4);
			choco.sum(closestSquare, "=", totalDist).post();
		} else {
			totalDist = choco.intVar("sum_dist", 0, idx.size() * highestDist / 2);
			choco.sum(closest, "=", totalDist).post();
		}
		Solver solver = choco.getSolver();
		// solver.showDecisions();
		// solver.showContradiction();
		// solver.showSolutions();

		int[] sumDists = IntStream.range(0, jumps.length).map(i2 -> IntStream.of(jumps[i2]).sum()).toArray();
		int[] sumSqDists = IntStream.range(0, jumps.length).map(i2 -> IntStream.of(jumps[i2]).map(i3 -> i3 * i3).sum())
				.toArray();
		int[] idxSortedBySumDistsInc = IntStream.range(0, jumps.length).boxed()
				.sorted((i1, i2) -> sumDists[i1] - sumDists[i2]).mapToInt(i -> i).toArray();
		int[] idxSortedBySumSqDistsInc = IntStream.range(0, jumps.length).boxed()
				.sorted((i1, i2) -> sumSqDists[i1] - sumSqDists[i2]).mapToInt(i -> i).toArray();
		int[] idxPriorities = useSquareDistance ? idxSortedBySumSqDistsInc : idxSortedBySumDistsInc;
		System.err.println(
				"systems priorities : " + IntStream.of(idxPriorities).mapToObj(idx::system).collect(Collectors.toList()));
		IntStrategy heuristic = Search.intVarSearch(new InputOrder<>(choco), (IntValueSelector) var -> {
			for (int i : idxPriorities) {
				if (var.contains(i)) {
					return i;
				}
			}
			return var.getLB();
		}, clusterCenters);
		// heuristic = Search.inputOrderLBSearch(clusterCenters);
		solver.setSearch(heuristic);

		Solution found = solver.findOptimalSolution(totalDist, false);
		if (found == null || !found.exists()) {
			return null;
		} else {
			List<SolarSystem> ret = new ArrayList<>();
			for (int i = 0; i < clusters; i++) {
				ret.add(idx.system(found.getIntVal(clusterCenters[i])));
			}
			return ret;
		}
	}

}
