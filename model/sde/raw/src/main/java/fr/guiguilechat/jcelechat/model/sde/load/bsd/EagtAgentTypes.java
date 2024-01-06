package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

@Deprecated(forRemoval = true)
public class EagtAgentTypes {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "sde/bsd/agtAgentTypes.yaml");
	private static ArrayList<EagtAgentTypes> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EagtAgentTypes> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EagtAgentTypes.class);
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

	public static HashMap<Integer, String> loadById() {
		HashMap<Integer, String> ret = new HashMap<>();
		for (EagtAgentTypes e : load()) {
			ret.put(e.agentTypeID, e.agentType);
		}
		return ret;
	}

	public String agentType;
	public int agentTypeID;

}
