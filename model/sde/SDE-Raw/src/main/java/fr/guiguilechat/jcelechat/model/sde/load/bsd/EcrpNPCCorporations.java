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
public class EcrpNPCCorporations {

	public static final File FILE = new File(SDECache.INSTANCE.extractCacheDir(), "sde/bsd/crpNPCCorporations.yaml");
	private static ArrayList<EcrpNPCCorporations> cache;

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<EcrpNPCCorporations> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(ArrayList.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						node.setType(EcrpNPCCorporations.class);
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

	private static HashMap<Integer, EcrpNPCCorporations> cacheById = null;

	public static HashMap<Integer, EcrpNPCCorporations> loadById() {
		ArrayList<EcrpNPCCorporations> list = load();
		synchronized (list) {
			if (cacheById == null) {
				HashMap<Integer, EcrpNPCCorporations> ret = new HashMap<>();
				for (EcrpNPCCorporations e : load()) {
					ret.put(e.corporationID, e);
				}
				cacheById = ret;
			}
			return cacheById;
		}
	}

	public int border, corporationID, corridor;
	public String description;
	public int enemyID;
	public char extent;
	public int factionID, friendID, fringe, hub, iconID, initialPrice, investorID1, investorID2, investorID3, investorID4,
	investorShares1, investorShares2, investorShares3, investorShares4;
	public double minSecurity;
	public int publicShares;
	public boolean scattered;
	public char size;
	public double sizeFactor;
	public int solarSystemID, stationCount, stationSystemCount;

}
