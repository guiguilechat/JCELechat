package fr.guiguilechat.jcelechat.libs.regioncycler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IRegionCycler;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

/**
 * create several generation of solutions . Each generation, mutations are
 * added, and mutation and parents are merged, then the best are kept. A
 * solution is an ordering of the possible systems indexes. Solutions are stored
 * as the array of indices, as well as the total size to avoid useless
 * computation. An mutation is a permutation of two systems. mergin two
 * solutions consists in taking the beginning of the first, then adding the
 * remaining systems in the order they are present in the second.
 */
public class GeneticRegionCycler implements IRegionCycler {

	public static final GeneticRegionCycler INSTANCE = new GeneticRegionCycler();

	public long seed = 1;

	public static class Solution {
		public int[] ordering;
		public int size;

		void evaluate(int source, int[][] distances) {
			size = 0;
			int last = source;
			for (int i : ordering) {
				size += distances[i][last];
				last = i;
			}
			size += distances[last][source];
		}
	}

	public static Solution init(Random rd, int source, int[][] distances) {
		Solution ret = new Solution();
		ret.ordering = new int[distances.length - 1];
		for (int i = 0; i < ret.ordering.length; i++) {
			ret.ordering[i] = i >= source ? i + 1 : i;
		}
		ret.evaluate(source, distances);
		return ret;
	}

	public static Solution fromGreedy(SysIndex idx, int[][] distances, int sourceIdx) {
		Solution ret = new Solution();
		ret.ordering = GreedyRegionCycler.INSTANCE.list(idx, distances, sourceIdx).stream().skip(1).mapToInt(idx::index)
				.toArray();
		ret.evaluate(sourceIdx, distances);
		return ret;
	}

	public static Solution mutate(Solution from, Random rd, int source, int[][] distances) {
		Solution ret = new Solution();
		ret.ordering = Arrays.copyOf(from.ordering, from.ordering.length);
		int i1 = rd.nextInt(ret.ordering.length - 1);
		int i2 = i1 + 1;
		int tmp = ret.ordering[i1];
		ret.ordering[i1] = ret.ordering[i2];
		ret.ordering[i2] = tmp;
		ret.evaluate(source, distances);
		return ret;
	}

	public static Solution merge(Solution start, Solution end, int source, int[][] distances) {
		Solution ret = new Solution();
		ret.ordering = Arrays.copyOf(start.ordering, start.ordering.length);
		int nextIndex=ret.ordering.length/2;
		for(int toPlace : end.ordering) {
			boolean add=true;
			for (int i = 0; i < nextIndex; i++) {
				if (ret.ordering[i] == toPlace) {
					add = false;
					break;
				}
			}
			if (add) {
				ret.ordering[nextIndex] = toPlace;
				nextIndex++;
			}
		}
		ret.evaluate(source, distances);
		return ret;
	}

	@Override
	public List<SolarSystem> list(SysIndex idx, int[][] distances, int sourceIdx) {
		Random rd = new Random(seed);
		List<Solution> pool = new ArrayList<>();
		pool.add(init(rd, sourceIdx, distances));
		pool.add(fromGreedy(idx, distances, sourceIdx));
		int poolSize = 50;
		for (int generation = 0; generation < 200; generation++) {
			for (int j = 0; j < poolSize; j++) {
				pool.add(mutate(pool.get(rd.nextInt(pool.size())), rd, sourceIdx, distances));
			}
			for (int j = 0; j < poolSize; j++) {
				pool.add(merge(pool.get(rd.nextInt(pool.size())), pool.get(rd.nextInt(pool.size())), sourceIdx, distances));
			}
			// only keep the best
			Collections.sort(pool, Comparator.comparing(s -> s.size));
			while (pool.size() > poolSize) {
				pool.remove(poolSize);
			}
			System.err.println("generation " + generation + " best =" + pool.get(0).size);
		}
		return IntStream.of(pool.get(0).ordering).mapToObj(idx::system).collect(Collectors.toList());
	}

}
