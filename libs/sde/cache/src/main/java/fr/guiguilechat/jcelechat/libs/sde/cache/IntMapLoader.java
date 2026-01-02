package fr.guiguilechat.jcelechat.libs.sde.cache;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlMapIntLoader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * load an archive into a map of ints to given type.<br />
 * Allows for later yaml/jsonl parsing.
 *
 * @param <U>
 */
@Accessors(fluent = true)
@RequiredArgsConstructor
public class IntMapLoader<U> {

	public final String fileName;

	public final Class<U> rootClass;

	@Getter(lazy = true)
	private final String yamlFileName = fileName + ".yaml";

	@Getter(lazy = true)
	private final YamlMapIntLoader<U> yaml = new YamlMapIntLoader<>(yamlFileName(), rootClass);

}
