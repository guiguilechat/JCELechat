package fr.guiguilechat.jcelechat.libs.sde.cache;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlMapStringLoader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * load an archive into a map of String to given type.<br />
 * Allows for later yaml/jsonl parsing.
 *
 * @param <U>
 */
@Accessors(fluent = true)
@RequiredArgsConstructor
public class StringMapLoader<U> implements MapLoader<String, U> {

	public final String fileName;

	public final Class<U> rootClass;

	@Getter(lazy = true)
	private final String yamlFileName = fileName + ".yaml";

	@Getter(lazy = true)
	private final YamlMapStringLoader<U> yaml = new YamlMapStringLoader<>(yamlFileName(), rootClass);

}
