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
 * width-first search with priority towards a target. If the
 * {@link #evalMin(Object, Object)} returns 0, then this is a {@link Dijkstra}
 * <p>
 * Since the evaluation heuristic is based on a target, we can't reuse an
 * algorithm for other tagets
 * </p>
 *
 * @param <T>
 */
@RequiredArgsConstructor
public abstract class AStar<T> {

	public final T start;

	public final T target;

	public record ItemDist<T>(T item, T parent, double distancefromStart) {
	}

	public record ItemEval<T>(T item, T parent, double distanceFromStart, double totalEval) {

		public ItemDist<T> visited() {
			return new ItemDist<>(item(), parent(), distanceFromStart);
		}

	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final List<ItemEval<T>> toVisit = new ArrayList<>(List.of(new ItemEval<>(start, null, 0, 0)));

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
	 * evaluate the minimum distance from an item, to the target
	 */
	protected abstract double evalMin(T start, T end);

	/**
	 * for each item visited, the distance from start and its parent.
	 */
	protected Map<T, ItemDist<T>> visited = new HashMap<>();

	protected void visitNext() {
		ItemEval<T> nxt = popNextVisit();
		if (nxt == null) {
			return;
		}
		T visit = nxt.item();
		visited.put(visit, nxt.visited());

		// add all adjacent elements
		boolean changed = false;
		Iterable<T> adjIt = reachable(visit);
		if (adjIt != null) {
			for (T adj : adjIt) {
				ItemDist<T> previousVist = visited.get(adj);
				if (previousVist == null) {
					double stepDistance = distance(visit, adj);
					double adjDistance = stepDistance + nxt.distanceFromStart();
					double adjEval = evalMin(adj, target);
//					System.err.println(" evaluated " + adj + " as " + adjEval);
					ItemEval<T> added = new ItemEval<>(adj, visit, adjDistance, adjDistance + adjEval);
					toVisit().add(added);
					changed = true;
					continue;
				}
			}
		}
		if (changed) {
			Collections.sort(toVisit(), Comparator.comparing(ItemEval::totalEval));
		}
	}

	/**
	 * find next item to visit, that has not already been visited
	 */
	protected ItemEval<T> popNextVisit() {
		while (!toVisit().isEmpty()) {
			ItemEval<T> nxt = toVisit().remove(0);
			if (!visited.containsKey(nxt.item())) {
				return nxt;
			}
		}
		return null;
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

	public List<ItemDist<T>> search() {
		while (!visited.containsKey(target) && !toVisit().isEmpty()) {
			visitNext();
		}
		return route(target);
	}

}
