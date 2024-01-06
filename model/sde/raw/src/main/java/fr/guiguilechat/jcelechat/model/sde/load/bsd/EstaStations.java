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

public class EstaStations {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "sde/bsd/staStations.yaml");
	private static ArrayList<EstaStations> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EstaStations> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EstaStations.class);
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

	public static Map<Integer, EstaStations> loadById() {
		HashMap<Integer, EstaStations> ret = new HashMap<>();
		for (EstaStations s : load()) {
			ret.put(s.stationID, s);
		}
		return ret;
	}

	public int constellationID;
	public int corporationID;
	public int dockingCostPerVolume;
	public int maxShipVolumeDockable;
	public int officeRentalCost;
	public int operationID;
	public int regionID;
	public double reprocessingEfficiency;
	public int reprocessingHangarFlag;
	public double reprocessingStationsTake;
	public double security;
	public int solarSystemID;
	public int stationID;
	public String stationName;
	public int stationTypeID;
	public double x;
	public double y;
	public double z;

}
