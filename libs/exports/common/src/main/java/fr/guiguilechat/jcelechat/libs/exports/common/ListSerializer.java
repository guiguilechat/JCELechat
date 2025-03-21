package fr.guiguilechat.jcelechat.libs.exports.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.representer.Representer;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
public class ListSerializer<T> {

	private final String resourcePath;

	private final Class<T> internalClass;

	public List<T> load(InputStream is) {
		try (InputStreamReader reader = new InputStreamReader(is)) {
			LoaderOptions options = new LoaderOptions();
			options.setCodePointLimit(Integer.MAX_VALUE);
			Constructor c = new Constructor(options) {
				@Override
				protected Object constructObject(Node node) {
					if (node instanceof SequenceNode && node.getStartMark().getIndex() == 0) {
						((SequenceNode) node).setListType(internalClass);
					}
					return super.constructObject(node);
				}
			};
			Yaml yaml = new Yaml(c, new Representer(new DumperOptions()), new DumperOptions(), options);
			return yaml.loadAs(reader, List.class);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final List<T> load = load(getClass().getClassLoader().getResourceAsStream(resourcePath));

	public File export(List<T> data, File folderout) {
		File output = new File(folderout, resourcePath);
		output.mkdirs();
		output.delete();
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(data, new FileWriter(output));
		} catch (IOException e) {
			throw new RuntimeException("while exporting to " + output.getAbsolutePath(), e);
		}
		return output;
	}

}
