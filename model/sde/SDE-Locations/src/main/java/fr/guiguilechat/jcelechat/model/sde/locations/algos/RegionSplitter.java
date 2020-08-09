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

	/**
	 * split the set of systems, around a source, accessible with jumps in the
	 * same region and in HS, in a given number of clusters around system.
	 *
	 * @param source
	 *          system to start the exploration of the region, in HS, around.
	 * @param clusters
	 *          number of central systems.
	 * @return a list of the systems that make the sum of square distances of each
	 *         concerned system in the region the lowest possible.
	 */
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
		int bestEval = Integer.MAX_VALUE;
		for (int[] valuation = preValuation(clusters); nextValuation(valuation, idx.size() - 1);) {
			int val = eval(valuation, jumps);
			if (val < bestEval) {
				bestEval = val;
				bestSol = Arrays.copyOf(valuation, valuation.length);
				logger.debug("  new solution " + IntStream.of(bestSol).mapToObj(idx::system).collect(Collectors.toList())
						+ " value " + val + " avg=" + Math.sqrt(val) / idx.size());
			}
		}
		return IntStream.of(bestSol).mapToObj(idx::system).collect(Collectors.toList());
	}

	/**
	 * create an invalid first evaluation. Next call to
	 * {@link #nextValuation(int[], int)} will actually instantiate it to the
	 * first valuation
	 *
	 * @param clusters
	 *          number of variables
	 * @return a new int[] of size clusters
	 */
	public static int[] preValuation(int clusters) {
		int[] ret = new int[clusters];
		for (int i = 0; i < clusters; i++) {
			ret[i]=i;
		}
		ret[clusters - 1]--;
		return ret;
	}

	/**
	 * modify the valuation to make it get the next value.
	 *
	 * @param valuation
	 *          the valuation to update to the next value
	 * @param maxValue
	 *          maximum allowed value in each variable
	 * @return true if the next valuation was found, false if there is no more
	 *         valuation.
	 */
	public static boolean nextValuation(int[] valuation, int maxValue) {
		for (int backIdx = 0; backIdx < valuation.length; backIdx++) {
			int idx = valuation.length - 1 - backIdx;
			int mvalue = maxValue - backIdx;
			if (valuation[idx] >= mvalue) {
				if (idx == 0) {
					return false;
				}
				valuation[idx] = -1;
			} else {
				valuation[idx]++;
				break;
			}
		}
		for (int i = 1; i < valuation.length; i++) {
			if (valuation[i] == -1) {
				valuation[i] = valuation[i - 1] + 1;
			}
		}
		return true;
	}

	/**
	 * evaluate a valuation
	 *
	 * @param valuation
	 *          the valuation to evaluate
	 * @param jumps
	 *          the symetric map of distances
	 * @return the sum of the square of distances from each system in the matrix
	 *         to the closest system in the valuation.
	 */
	public static int eval(int[] valuation, int[][] jumps) {
		int ret = 0;
		for (int[] jump : jumps) {
			int dist = Integer.MAX_VALUE;
			for (int element : valuation) {
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
