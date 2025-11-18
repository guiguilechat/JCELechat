package fr.guiguilechat.jcelechat.libs.sde.cache.yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.libs.sde.cache.tools.YamlTools;

/**
 * snakeyaml LinkedHashMap loader
 *
 * @param <U>
 */
public class SnakeYamlLHMLoader<U> extends JacksonYamlLHMLoader<U> {

	private final Class<U> topClass;
	private final List<Set<String>> topClassDisc;

	@SafeVarargs
	public SnakeYamlLHMLoader(String archiveFileName, Class<U> topClass, Set<String> firstDisc,
			Set<String>... nextDiscs) {
		super(archiveFileName, topClass);
		this.topClass = topClass;
		topClassDisc = Stream.concat(Stream.of(firstDisc), nextDiscs == null ? Stream.of() : Stream.of(nextDiscs))
				.toList();
	}


	protected void preprocess(Node node) {
		if (node.getNodeId() == NodeId.mapping) {
			MappingNode mn = (MappingNode) node;
			if (mn.getValue().size() > 0) {

				if (topClassDisc.stream().filter(fieldNames -> matching(mn, fieldNames)).findAny().isPresent()) {
					node.setType(topClass);
				}
				if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
						.filter("roleBonuses"::equals).findAny().isPresent()) {
				}
			}
		}
	}

	protected boolean matching(MappingNode mn, Set<String> requiredFieldNames) {
		Set<String> presentFields = mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
				.collect(Collectors.toSet());
		return presentFields.containsAll(requiredFieldNames);
	}

	@Override
	public LinkedHashMap<Integer, U> from(InputStream is) {
		Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

			@Override
			protected Construct getConstructor(Node node) {
				preprocess(node);
				return super.getConstructor(node);
			}
		};
		Yaml yaml = YamlTools.yaml(cons);
		return yaml.loadAs(is, LinkedHashMap.class);
	}

}
