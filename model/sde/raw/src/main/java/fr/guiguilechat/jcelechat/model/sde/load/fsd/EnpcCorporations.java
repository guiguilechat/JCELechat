package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import fr.guiguilechat.jcelechat.model.sde.load.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde.load.SnakeYamlLHMLoader;

public class EnpcCorporations {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "fsd/npcCorporations.yaml";

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EnpcCorporations>> LOADER_JACKSON = new JacksonYamlLoader<>(
			SDE_FILE);

	public static final SnakeYamlLHMLoader<Integer, EnpcCorporations> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(
			SDE_FILE) {

		protected void preprocess(org.yaml.snakeyaml.nodes.Node node) {
			if (node.getNodeId() == NodeId.mapping) {
				MappingNode mn = (MappingNode) node;
				if (mn.getValue().size() > 0) {
					if (mn.getValue().stream().map(nt -> ((ScalarNode) nt.getKeyNode()).getValue())
							.filter("tickerName"::equals).findAny().isPresent()) {
						node.setType(EnpcCorporations.class);
					}
				}
			}
		}
	};

	public static final JacksonYamlLoader<LinkedHashMap<Integer, EnpcCorporations>> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

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
		return LOADER.load().get(CONCORD_ID).exchangeRates;
	}

	//

	public static void main(String[] args) {
		for (Entry<Integer, EnpcCorporations> e : LOADER.load().entrySet()) {
			e.getValue().enName();
		}
	}

}
