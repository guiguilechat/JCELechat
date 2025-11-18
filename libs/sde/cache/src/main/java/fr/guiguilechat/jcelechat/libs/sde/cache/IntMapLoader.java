package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.SnakeYamlLHMLoader;
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

	final Set<String> firstRootDiscriminant;
	final Set<String>[] nextRootDiscriminants;

	private boolean useJackson = true;

	@SafeVarargs
	public IntMapLoader(String fileName, Class<U> rootClass, Set<String> firstRootDiscriminant,
			Set<String>... nextRootDiscriminants) {
		this.fileName = fileName;
		this.rootClass = rootClass;
		this.firstRootDiscriminant = firstRootDiscriminant;
		this.nextRootDiscriminants = nextRootDiscriminants;
	}

	@SafeVarargs
	public IntMapLoader(String fileName, Class<U> rootClass, boolean useJackson, Set<String> firstRootDiscriminant,
			Set<String>... nextRootDiscriminants) {
		this(fileName, rootClass, firstRootDiscriminant, nextRootDiscriminants);
		this.useJackson = useJackson;
	}

	@Getter(lazy = true)
	private final String yamlFileName = fileName + ".yaml";

	@Getter(lazy = true)
	private final JacksonYamlLHMLoader<U> jackson = new JacksonYamlLHMLoader<>(yamlFileName(), rootClass);

	@Getter(lazy = true)
	private final SnakeYamlLHMLoader<U> snakeyaml = new SnakeYamlLHMLoader<U>(
			yamlFileName(),
			rootClass,
			firstRootDiscriminant,
			nextRootDiscriminants);

	public JacksonYamlLHMLoader<U> yaml() {
		return useJackson ? jackson() : snakeyaml();
	}

}
