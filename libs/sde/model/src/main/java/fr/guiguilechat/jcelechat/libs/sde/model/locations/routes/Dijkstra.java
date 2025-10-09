package fr.guiguilechat.jcelechat.libs.sde.model.locations.routes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Dijkstra (width-first) algorithm
 *
 * @param <T>
 */
@RequiredArgsConstructor
public abstract class Dijkstra<T> {

	private final T start;

	public record ItemDist<T>(T item, T parent, double distancefromStart) {
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final List<ItemDist<T>> toVisit = new ArrayList<>(List.of(new ItemDist<>(start, null, 0)));

	/**
	 * @return iterable over the adjacent items of from
	 */
	protected abstract Iterable<T> reachable(T from);

	/**
	 * @param end an item reachable from start
	 * @return actual distance from start to end
	 */
	protected abstract double distance(T start, T end);

	/**
	 * for each item visited, the distance from start and its parent.
	 */
	protected Map<T, ItemDist<T>> visited = new HashMap<>();

	protected void visitNext() {
		ItemDist<T> nxt = popNextVisit();
		if (nxt == null) {
			return;
		}
		T visit = nxt.item();
		visited.put(visit, nxt);

		// add all adjacent elements
		boolean changed = false;
		Iterable<T> adjIt = reachable(visit);
		if (adjIt != null) {
			for (T adj : adjIt) {
				ItemDist<T> previousVist = visited.get(adj);
				if (previousVist == null) {
					double adjDistance = distance(visit, adj) + nxt.distancefromStart();
					toVisit().add(new ItemDist<>(adj, visit, adjDistance));
					changed = true;
					continue;
				}
			}
		}
		if (changed) {
			Collections.sort(toVisit(), Comparator.comparing(ItemDist::distancefromStart));
//			System.err.println("after visiting " + visit + " tovisit=" + toVisit());
		}
	}

	/**
	 * find next item to visit, that has not already been visited
	 */
	protected ItemDist<T> popNextVisit() {
		while (!toVisit().isEmpty()) {
			ItemDist<T> nxt = toVisit().remove(0);
			if (!visited.containsKey(nxt.item())) {
				return nxt;
			}
		}
		return null;
	}

	protected void till(T target) {
		while (!visited.containsKey(target) && !toVisit().isEmpty()) {
			visitNext();
		}
	}

	protected List<ItemDist<T>> route(T target) {
		ItemDist<T> lastStep = visited.get(target);
		if (lastStep == null) {
			return null;
		}
		List<ItemDist<T>> ret = new ArrayList<>();
		while (lastStep.parent() != null) {
			ret.add(lastStep);
			lastStep = visited.get(lastStep.parent());
		}
		return ret.reversed();
	}

	public List<ItemDist<T>> to(T target) {
		till(target);
		return route(target);
	}

}
