package fr.guiguilechat.jcelechat.libs.regionstager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

/**
 * split a region into n main systems, in order to reduce the amount of jumps
 * from each system to the closest of those systems.<br />
 * This implementation bruteforces the possible main systems.
 * <p>
 * A valuation is an array of system indices that are used. Since in that
 * context, the array [1,2,3] is the same as [3,2,1] we only consider a
 * valuation as consisting in increasing indices. so [0,1] is a valid valuation
 * but [1,0] is not.
 * </p>
 * <p>
 * this implementation uses a increment on the valuations, and a prevaluation
 * function to get a invalid valuation that will be valid, to use in a for loop.
 * </p>
 *
 */
public class BruteStager implements IRegionStager {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BruteStager.class);

	public static final BruteStager INSTANCE = new BruteStager();

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
			ret += dist;
		}
		return ret;
	}

	@Override
	public List<SolarSystem> around(SysIndex idx, int[][] jumps, Params params) {
		int clusters = params.clusters;
		int[] bestSol = null;
		int bestEval = Integer.MAX_VALUE;
		for (int[] valuation = preValuation(clusters); nextValuation(valuation, idx.size() - 1);) {
			int val = eval(valuation, jumps);
			if (val < bestEval) {
				bestEval = val;
				bestSol = Arrays.copyOf(valuation, valuation.length);
				// logger.debug(" new solution " +
				// IntStream.of(bestSol).mapToObj(idx::system).collect(Collectors.toList())
				// + " value " + val + " avg=" + (useSquareDistance ? Math.sqrt(val) :
				// val) / idx.size());
			}
		}
		return IntStream.of(bestSol).mapToObj(idx::system).collect(Collectors.toList());
	}

}
