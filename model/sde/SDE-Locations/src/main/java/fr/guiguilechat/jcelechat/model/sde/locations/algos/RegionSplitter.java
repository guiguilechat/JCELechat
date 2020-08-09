package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;

/**
 * split a region into n main systems, in order to reduce the amount of jumps
 * from each system to the closest of those systems.
 */
public class RegionSplitter {

	private static final Logger logger = LoggerFactory.getLogger(RegionSplitter.class);

	public static List<SolarSystem> splitHS(SolarSystem source, int clusters) {
		IndexedSystems idx = new IndexedSystems(ReachableRegionHs.around(source));
		int[][] jumps = new int[idx.size()][idx.size()];
		for (int i = 0; i < idx.size(); i++) {
			SolarSystem from = idx.system(i);
			jumps[i][i] = 0;
			for (int j = i + 1; j < idx.size(); j++) {
				SolarSystem to = idx.system(j);
				jumps[i][j] = jumps[j][i] = fr.guiguilechat.jcelechat.model.sde.locations.algos.Router.route(from.id, to.id,
						SECSTATUS.HS).length;
			}
		}
		int[] bestSol = null;
		int bestVal = Integer.MAX_VALUE;
		for (int[] sol = preSolution(clusters); nextSolution(sol, idx.size() - 1);) {
			int val = eval(sol, jumps);
			if (val < bestVal) {
				bestVal = val;
				bestSol = Arrays.copyOf(sol, sol.length);
				logger.debug("  new solution " + IntStream.of(bestSol).mapToObj(idx::system).collect(Collectors.toList())
						+ " value " + val + " avg=" + Math.sqrt(val) / idx.size());
			}
		}
		return IntStream.of(bestSol).mapToObj(idx::system).collect(Collectors.toList());
	}

	/**
	 * create an invalid first solution. next call to nexSolution will actually
	 * return the first solution
	 *
	 * @param clusters
	 * @return
	 */
	public static int[] preSolution(int clusters) {
		int[] ret = new int[clusters];
		for (int i = 0; i < clusters; i++) {
			ret[i]=i;
		}
		ret[clusters - 1]--;
		return ret;
	}

	public static boolean nextSolution(int[] solution, int maxValue) {
		for (int backIdx = 0; backIdx < solution.length; backIdx++) {
			int idx = solution.length - 1 - backIdx;
			int mvalue = maxValue - backIdx;
			if (solution[idx] >= mvalue) {
				if (idx == 0) {
					return false;
				}
				solution[idx] = -1;
			} else {
				solution[idx]++;
				break;
			}
		}
		for (int i = 1; i < solution.length; i++) {
			if (solution[i] == -1) {
				solution[i] = solution[i - 1] + 1;
			}
		}
		return true;
	}

	public static int eval(int[] solution, int[][] jumps) {
		int ret = 0;
		for (int[] jump : jumps) {
			int dist = Integer.MAX_VALUE;
			for (int element : solution) {
				int dstSol = jump[element];
				if (dstSol < dist) {
					dist = dstSol;
				}
			}
			ret += dist * dist;
		}
		return ret;
	}

}
