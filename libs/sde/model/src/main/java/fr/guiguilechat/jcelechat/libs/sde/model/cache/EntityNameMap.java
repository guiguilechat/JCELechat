package fr.guiguilechat.jcelechat.libs.sde.model.cache;

import java.util.stream.Stream;

public interface EntityNameMap<U> extends EntityMap<U> {

	U of(String name);

	default Stream<U> of(Stream<String> is) {
		if (is == null) {
			return Stream.of();
		}
		return is.map(this::of);
	}

}
