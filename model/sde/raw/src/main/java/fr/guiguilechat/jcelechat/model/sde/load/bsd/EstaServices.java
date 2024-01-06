package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

/**
 * @deprecated since CCP moved it in the FSD
 *
 */
@Deprecated(forRemoval = true)
public class EstaServices {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "sde/bsd/staServices.yaml");
	private static ArrayList<EstaServices> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EstaServices> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EstaServices.class);
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

	public static Map<Integer, String> loadById() {
		HashMap<Integer, String> ret = new HashMap<>();
		for (EstaServices s : load()) {
			ret.put(s.serviceID, s.serviceName);
		}
		return ret;
	}

	public int serviceID;
	public String serviceName;
	public String description;
}
