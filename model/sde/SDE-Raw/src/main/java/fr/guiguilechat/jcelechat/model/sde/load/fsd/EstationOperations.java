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

public class EstationOperations {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/stationOperations.yaml");
	private static LinkedHashMap<Integer, EstationOperations> cache;

	@SuppressWarnings("unchecked")
	public static synchronized LinkedHashMap<Integer, EstationOperations> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "activityID".equals(s)).findAny().isPresent()) {
								node.setType(EstationOperations.class);
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

	public int activityID;
	public double border;
	public double corridor;
	public HashMap<String, String> descriptionID = new HashMap<>();
	public double fringe;
	public double hub;
	public double manufacturingFactor;
	public HashMap<String, String> operationNameID = new HashMap<>();
	public double ratio;
	public double researchFactor;
	public int[] services;
	public HashMap<Integer, Integer> stationTypes = new HashMap<>();


}
