package fr.guiguilechat.jcelechat.libs.denlicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.IDenLicker;
import fr.guiguilechat.jcelechat.model.sde.locations.algos.SysIndex;

public class GreedyDenLicker implements IDenLicker {

	public static final GreedyDenLicker INSTANCE = new GreedyDenLicker();

	private static class Edge {
		public int i, j;
		public int dist;
	}

	@Override
	public List<SolarSystem> from(SysIndex idx, int[][] distances, int sourceIdx) {
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < idx.size(); i++) {
			for (int j = 0; j < i; j++) {
				Edge edge = new Edge();
				edge.i = i;
				edge.j = j;
				edge.dist = distances[i][j];
				edges.add(edge);
			}
		}
		Collections.sort(edges, (e1, e2) -> e1.dist - e2.dist);
		// System.err.println("dist 0 =" + edges.get(0).dist);
		Set<Integer> reached = new HashSet<>();
		Set<Integer> inside = new HashSet<>();
		Edge edge = edges.get(0);
		reached.add(edge.i);
		reached.add(edge.j);
		List<Edge> accepted = new ArrayList<>();
		accepted.add(edge);
		edges.remove(edge);
		while (inside.size() < idx.size() - 2) {
			// System.err.println("searching edge for idx " + reached + " out of " +
			// inside);
			edge = edges.stream().filter(e -> reached.contains(e.i) && !reached.contains(e.j) && !inside.contains(e.j)
					|| reached.contains(e.j) && !reached.contains(e.i) && !inside.contains(e.i)).findFirst().get();
			accepted.add(edge);
			if (reached.contains(edge.i)) {
				reached.remove(edge.i);
				inside.add(edge.i);
				reached.add(edge.j);
			} else {
				reached.remove(edge.j);
				inside.add(edge.j);
				reached.add(edge.i);
			}
			edges.remove(edge);
		}
		edge = new Edge();
		Integer[] remain = reached.toArray(Integer[]::new);
		edge.i = remain[0];
		edge.j = remain[1];
		accepted.add(edge);
		ArrayList<SolarSystem> ret = new ArrayList<>();
		ret.add(idx.system(sourceIdx));
		int lastIdx = sourceIdx;
		Edge lastEdge = null;
		while (ret.size() < idx.size()) {
			edge = null;
			for (Edge edge2 : accepted) {
				if (edge2 != lastEdge && (edge2.i == lastIdx || edge2.j == lastIdx)) {
					edge = edge2;
				}
			}
			if (edge.i == lastIdx) {
				lastIdx = edge.j;
			} else {
				lastIdx = edge.i;
			}
			lastEdge = edge;
			ret.add(idx.system(lastIdx));
		}
		return ret;
	}

}
