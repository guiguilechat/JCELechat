package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileNotFoundException;
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

public class EgroupIDs {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "sde/fsd/groupIDs.yaml");
	private static LinkedHashMap<Integer, EgroupIDs> cache;

	@SuppressWarnings("unchecked")
	public static synchronized LinkedHashMap<Integer, EgroupIDs> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "published".equals(s)).findAny().isPresent()) {
								node.setType(EgroupIDs.class);
							}
						}
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = yaml.loadAs(SDECache.fileReader(FILE),
						LinkedHashMap.class);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	public HashMap<String, String> name = new HashMap<>();
	public boolean published;
	public boolean anchorable;
	public boolean anchored;
	public int categoryID;
	public boolean fittableNonSingleton;
	public boolean useBasePrice;
	public int iconID;

	public String enName() {
		return name == null || !name.containsKey("en") ? null : name.get("en");
	}

}
