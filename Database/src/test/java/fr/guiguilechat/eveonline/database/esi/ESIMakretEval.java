package fr.guiguilechat.eveonline.database.esi;

import java.util.stream.IntStream;

public class ESIMakretEval {

	public static void main(String[] args) {

		int[] ids = IntStream.range(32, 32 + 10000).toArray();
		for (int repeat = 0; repeat < 4; repeat++) {
			System.err.println();
			System.err.println("repeat " + repeat);
			for (int threads : new int[] { 10, 100, 1000 }) {
				System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + threads);

				ESIMarket market = new ESIMarket(10000002);
				long start = System.currentTimeMillis();
				IntStream.of(ids).parallel().mapToDouble(i -> market.getBO(i, 1)).filter(i -> i != 0).count();
				long time = System.currentTimeMillis() - start;
				System.out.println("" + threads + " : " + time);
			}
		}

	}

}
