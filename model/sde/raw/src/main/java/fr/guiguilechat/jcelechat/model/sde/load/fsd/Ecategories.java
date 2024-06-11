package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class Ecategories {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "fsd/categories.yaml");
	private static LinkedHashMap<Integer, Ecategories> cache;

	public static synchronized LinkedHashMap<Integer, Ecategories> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			try {
				cache = from(new FileInputStream(FILE));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, Ecategories> from(InputStream is) {
		Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					MappingNode mn = (MappingNode) node;
					if (mn.getValue().size() > 0) {
						if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
								.filter("published"::equals).findAny().isPresent()) {
							node.setType(Ecategories.class);
						}
					}
				}
				Construct ret = super.getConstructor(node);
				return ret;
			}
		};
		Yaml yaml = SDECache.yaml(cons);
		return yaml.loadAs(is, LinkedHashMap.class);
	}

	/** key is language short, like "en" */
	public HashMap<String, String> name = new HashMap<>();
	public boolean published;
	public int iconID;

	public String enName() {
		return name == null || !name.containsKey("en") ? null : name.get("en");
	}

}
