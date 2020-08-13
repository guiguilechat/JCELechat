package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

/**
 * @deprecated was moved to fsd
 */
@Deprecated(forRemoval = true)
public class EstaOperationServices {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/staOperationServices.yaml");
	private static ArrayList<EstaOperationServices> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EstaOperationServices> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EstaOperationServices.class);
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = yaml.loadAs(new FileReader(FILE), ArrayList.class);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	public static Map<Integer, Set<Integer>> loadByOperationId() {
		Map<Integer, Set<Integer>> ret = new HashMap<>();
		for (EstaOperationServices s : load()) {
			Set<Integer> services = ret.get(s.operationID);
			if (services == null) {
				services = new HashSet<>();
				ret.put(s.operationID, services);
			}
			services.add(s.serviceID);
		}
		return ret;
	}

	public int serviceID;
	public int operationID;
}
