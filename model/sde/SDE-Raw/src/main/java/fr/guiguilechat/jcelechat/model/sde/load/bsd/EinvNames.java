package fr.guiguilechat.jcelechat.model.sde.load.bsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class EinvNames {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/invNames.yaml");
	private static ArrayList<EinvNames> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EinvNames> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EinvNames.class);
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = SDECache.yaml(cons);
			try {
				cache = yaml.loadAs(new FileReader(FILE), ArrayList.class);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	private static Map<Integer, String> id2Names = null;

	public static Map<Integer, String> loadById() {
		if (id2Names == null) {
			ArrayList<EinvNames> data = load();
			synchronized (data) {
				if (id2Names == null) {
					id2Names = data.stream().collect(Collectors.toMap(e -> e.itemID, e -> e.itemName));
				}
			}
		}
		return id2Names;
	}

	// structure

	public int itemID;
	public String itemName;
}
