package fr.guiguilechat.jcelechat.libs.spring.update.manager;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * implement to have default entities management methods
 */
public interface EntityService {

	/**
	 * @return actual class name. Used to avoid proxy name when called from outside
	 *         service
	 */
	default String serviceName() {
		return getClass().getSimpleName();
	}

	/**
	 * TODO use actual implementation : 1000 for oracle, Integer.maxInt for others
	 *
	 * @return maximum elements we can add in a list query param.
	 */
	default int maxInList() {
		return 1000;
	}

	default <T> Stream<List<T>> partitionInList(List<T> elements) {
		return partition(elements, maxInList());
	}

	/**
	 * partition a list of items into a list of limited-size sublists
	 *
	 * @param <T>
	 * @param elements
	 * @return
	 */
	static <T> Stream<List<T>> partition(List<T> elements, int maxSize) {
		if (elements == null) {
			return Stream.of();
		}
		if (maxSize >= elements.size()) {
			return Stream.of(elements);
		}
		return IntStream.iterate(0, i -> i < elements.size(), i -> i + maxSize)
				.mapToObj(i -> elements.subList(i, Math.min(elements.size(), i + maxSize)));
	}

}
