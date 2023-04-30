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

public class EstaStationTypes {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/bsd/staStationTypes.yaml");
	private static ArrayList<EstaStationTypes> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EstaStationTypes> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EstaStationTypes.class);
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

	public static Map<Integer, EstaStationTypes> loadById() {
		HashMap<Integer, EstaStationTypes> ret = new HashMap<>();
		for (EstaStationTypes s : load()) {
			ret.put(s.stationTypeID, s);
		}
		return ret;
	}

	public boolean conquerable;
	public double dockEntryX, dockEntryY, dockEntryZ, dockOrientationX, dockOrientationY, dockOrientationZ;
	public int stationTypeID;

}
