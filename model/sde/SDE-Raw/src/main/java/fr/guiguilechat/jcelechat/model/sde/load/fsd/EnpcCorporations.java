package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class EnpcCorporations {

	public static final File FILE = new File(SDECache.INSTANCE.cacheDir(), "sde/fsd/npcCorporations.yaml");
	private static LinkedHashMap<Integer, EnpcCorporations> cache;

	@SuppressWarnings("unchecked")
	public static synchronized LinkedHashMap<Integer, EnpcCorporations> load() {
		if (cache == null) {
			SDECache.INSTANCE.donwloadSDE();
			Constructor cons = new Constructor(LinkedHashMap.class, new LoaderOptions()) {

				@Override
				protected Construct getConstructor(Node node) {
					if (node.getNodeId() == NodeId.mapping) {
						MappingNode mn = (MappingNode) node;
						if (mn.getValue().size() > 0) {
							if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
									.filter(s -> "tickerName".equals(s)).findAny().isPresent()) {
								node.setType(EnpcCorporations.class);
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

	public int[] allowedMemberRaces = {};
	public int ceoID;
	public HashMap<Integer, Double> corporationTrades = new HashMap<>();
	public boolean deleted;
	public HashMap<String, String> descriptionID = new HashMap<>();
	public int enemyID;
	public HashMap<Integer, Double> exchangeRates = new HashMap<>();
	public String extent;
	public int factionID;
	public int friendID;
	public boolean hasPlayerPersonnelManager;
	public int iconID;
	public int initialPrice;
	public HashMap<Integer, Integer> investors = new HashMap<>();
	public int mainActivityID;
	public int memberLimit;
	public double minSecurity;
	public int minimumJoinStanding;
	public HashMap<String, String> nameID = new HashMap<>();
	public int publicShares;
	public int raceID;
	public int secondaryActivityID;
	public boolean sendCharTerminationMessage;
	public long shares;
	public String size;
	public double sizeFactor;
	public int solarSystemID;
	public int stationID;
	public double taxRate;
	public String tickerName;
	public boolean uniqueName;
	public String url;
	public HashMap<Integer, Edivisions> divisions = new HashMap<>();
	public ArrayList<Integer> lpOfferTables = new ArrayList<>();

	public static class Edivisions {
		public int divisionNumber;
		public int leaderID;
		public int size;
	}


	//

	public String enName() {
		return nameID.getOrDefault("en", "unknowncorp" + nameID);
	}

	public static final int CONCORD_ID = 1000125;

	public static Map<Integer, Double> concordRates() {
		return load().get(CONCORD_ID).exchangeRates;
	}

	//

	public static void main(String[] args) {
		for (Entry<Integer, EnpcCorporations> e : EnpcCorporations.load().entrySet()) {
			e.getValue().enName();
		}
	}

}
