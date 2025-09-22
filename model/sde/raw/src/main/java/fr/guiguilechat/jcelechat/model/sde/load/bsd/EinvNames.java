package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.OldJacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.OldSnakeYamlALLoader;

public class EinvNames {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "bsd/invNames.yaml";

	public static final OldJacksonYamlLoader<ArrayList<EinvNames>> LOADER_JACKSON = new OldJacksonYamlLoader<>(
			SDE_FILE);

	public static final OldSnakeYamlALLoader<EinvNames> LOADER_SNAKEYAML = new OldSnakeYamlALLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				node.setType(EinvNames.class);
			}
		}
	};

	public static final OldJacksonYamlLoader<ArrayList<EinvNames>> LOADER = LOADER_SNAKEYAML;

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
