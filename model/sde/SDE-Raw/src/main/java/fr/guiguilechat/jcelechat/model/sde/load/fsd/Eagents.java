package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileNotFoundException;
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

/**
 * an entry in the fsd/agents.yaml
 */
public class Eagents {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/agents.yaml");

	private static LinkedHashMap<Integer, Eagents> cache = null;

	@SuppressWarnings("unchecked")
	public static synchronized LinkedHashMap<Integer, Eagents> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();

			Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "agentTypeID".equals(s)).findAny().isPresent()) {
								node.setType(Eagents.class);
							}
						}
					}
					Construct ret = super.getConstructor(node);
					return ret;
				}
			};
			Yaml yaml = new Yaml(cons);
			try {
				cache = yaml.loadAs(SDECache.fileReader(FILE), LinkedHashMap.class);
			} catch (FileNotFoundException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	// structure

	public int agentTypeID;
	public int corporationID;
	public int divisionID;
	public boolean isLocator;
	public int level;
	public int locationID;
}
