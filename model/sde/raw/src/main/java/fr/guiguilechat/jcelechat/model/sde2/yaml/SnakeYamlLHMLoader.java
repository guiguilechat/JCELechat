package fr.guiguilechat.jcelechat.model.sde2.yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;

/**
 * snakeyaml LinkedHashMap loader
 *
 * @param <U>
 */
public abstract class SnakeYamlLHMLoader<U> extends JacksonYamlLHMLoader<U> {

	public SnakeYamlLHMLoader(String archiveFileName) {
		super(archiveFileName);
	}

	protected abstract void preprocess(Node node);

	@Override
	public LinkedHashMap<Integer, U> from(InputStream is) {
		Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

			@Override
			protected Construct getConstructor(Node node) {
				preprocess(node);
				return super.getConstructor(node);
			}
		};
		Yaml yaml = YamlCache.yaml(cons);
		return yaml.loadAs(is, LinkedHashMap.class);
	}

}
