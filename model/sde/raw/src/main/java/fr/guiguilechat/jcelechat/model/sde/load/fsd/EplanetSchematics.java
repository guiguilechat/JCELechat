package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

/**
 * an entry in the sde/fsd/planetSchematics.yaml
 */
public class EplanetSchematics {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "fsd/planetSchematics.yaml");

	private static LinkedHashMap<Integer, EplanetSchematics> cache = null;

	public static synchronized LinkedHashMap<Integer, EplanetSchematics> load() {
		if (cache == null) {
			try {
				cache = from(new FileInputStream(FILE));
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException(e);
			}
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, EplanetSchematics> from(InputStream is) {
		Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

			@Override
			protected Construct getConstructor(Node node) {
				if (node.getNodeId() == NodeId.mapping) {
					MappingNode mn = (MappingNode) node;
					if (mn.getValue().size() > 0) {
						if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
								.filter("cycleTime"::equals).findAny().isPresent()) {
							node.setType(EplanetSchematics.class);
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

	public String enName() {
		return nameID.getOrDefault("en", "");
	}

	// structure

	public static class SchemType {
		public boolean isInput;
		public int quantity;
	}

	public int cycleTime;
	public Map<String, String> nameID = new HashMap<>();
	public List<Integer> pins = new ArrayList<>();
	public Map<Integer, SchemType> types = new HashMap<>();

}
