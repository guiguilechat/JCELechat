package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlALLoader;

public class EinvNames {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "bsd/invNames.yaml";

	public static final JacksonYamlLoader<ArrayList<EinvNames>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE);

	public static final SnakeYamlALLoader<EinvNames> LOADER_SNAKEYAML = new SnakeYamlALLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				node.setType(EinvNames.class);
			}
		}
	};

	public static final JacksonYamlLoader<ArrayList<EinvNames>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public int itemID;
	public String itemName;

	// usage call cache

	private static LinkedHashMap<Integer, String> byIdCache = null;

	public static LinkedHashMap<Integer, String> loadById() {
		if (byIdCache == null) {
			ArrayList<EinvNames> data = LOADER.load();
			synchronized (data) {
				if (byIdCache == null) {
					LinkedHashMap<Integer, String> ret = new LinkedHashMap<>();
					data.stream().forEach(e -> ret.put(e.itemID, e.itemName));
					byIdCache = ret;
				}
			}
		}
		return byIdCache;
	}
}
