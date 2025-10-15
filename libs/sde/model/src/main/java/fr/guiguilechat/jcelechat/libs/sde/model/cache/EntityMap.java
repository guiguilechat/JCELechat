package fr.guiguilechat.jcelechat.libs.sde.model.cache;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * resolves entity from their id. Since resolution can have specific strategies,
 * including synchronization, caching, or fetching,
 * different methods can be overriden in specific implementations
 *
 * @param <U>
 */
public interface EntityMap<U> {

	Set<Integer> ids();

	Collection<U> all();

	U of(int id);

	Collection<U> of(Iterable<Integer> ids);

	default Stream<U> of(IntStream is) {
		if (is == null) {
			return Stream.of();
		}
		return is.mapToObj(this::of);
	}

	void forEachId(IntConsumer onId);

	void forEachElement(Consumer<U> onElement);
}
