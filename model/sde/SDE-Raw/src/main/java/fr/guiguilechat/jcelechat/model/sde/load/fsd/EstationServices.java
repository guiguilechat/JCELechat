package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class EstationServices {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/stationServices.yaml");
	private static LinkedHashMap<Integer, EstationServices> cache;

	@SuppressWarnings("unchecked")
	public static synchronized LinkedHashMap<Integer, EstationServices> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(LinkedHashMap.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "serviceNameID".equals(s)).findAny().isPresent()) {
								node.setType(EstationServices.class);
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

	// structure

	public HashMap<String, String> serviceNameID = new HashMap<>();
	public HashMap<String, String> descriptionID = new HashMap<>();

	//

	public String enName() {
		return serviceNameID.getOrDefault("en", "unknownservice" + serviceNameID);
	}


}
