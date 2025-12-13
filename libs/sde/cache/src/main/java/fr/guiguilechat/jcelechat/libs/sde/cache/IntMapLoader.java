package fr.guiguilechat.jcelechat.libs.sde.cache;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * load an archive into a map of ints to given type.
 * <p>
 * discriminants are used in snakeyaml to coerce a map into the root type
 * </p>
 *
 * @param <U>
 */
@Accessors(fluent = true)
public class IntMapLoader<U> {

	public final String fileName;

	public final Class<U> rootClass;

	public IntMapLoader(String fileName, Class<U> rootClass) {
		this.fileName = fileName;
		this.rootClass = rootClass;
	}

	@Getter(lazy = true)
	private final String yamlFileName = fileName + ".yaml";

	@Getter(lazy = true)
	private final JacksonYamlLHMLoader<U> yaml = new JacksonYamlLHMLoader<>(yamlFileName(), rootClass);

}
