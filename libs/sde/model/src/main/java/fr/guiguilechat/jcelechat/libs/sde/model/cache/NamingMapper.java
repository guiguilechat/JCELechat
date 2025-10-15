package fr.guiguilechat.jcelechat.libs.sde.model.cache;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;

/**
 * mapper that also stores the elements by name
 */
public class NamingMapper<T, U> extends Mapper<T, U> implements EntityNameMap<U> {

	private final Function<U, String> namer;

	public NamingMapper(JacksonYamlLHMLoader<T> loader, BiFunction<Integer, T, U> constructor,
			Function<U, String> namer) {
		super(loader, constructor);
		this.namer = namer;
	}

	private Map<String, U> byNames = new LinkedHashMap<>();

	@Override
	public U of(String name) {
		U ret = byNames.get(name);
		if (ret == null) {
			try (var _ = lck.writeLock()) {
				ret = byNames.get(name);
				if (ret == null) {
					if (byNames.isEmpty()) {
						all().forEach(val -> byNames.put(namer.apply(val), val));
					}
					ret = byNames.get(name);
				}
			}
		}
		return ret;
	}

	@Override
	protected Stream<Collection<?>> caches() {
		return Stream.concat(super.caches(), Stream.of(byNames.keySet()));
	}

}
