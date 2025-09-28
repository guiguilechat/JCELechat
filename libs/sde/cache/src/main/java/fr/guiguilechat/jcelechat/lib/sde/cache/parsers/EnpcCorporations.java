package fr.guiguilechat.jcelechat.lib.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.lib.sde.cache.yaml.SnakeYamlLHMLoader;

public class EnpcCorporations {

	//
	// SDE loading
	//

	public static final String SDE_FILE = "npcCorporations";
	public static final String SDE_FILE_YAML = SDE_FILE + ".yaml";

	public static final JacksonYamlLHMLoader<EnpcCorporations> LOADER_JACKSON = new JacksonYamlLHMLoader<>(
			SDE_FILE_YAML);

	public static final SnakeYamlLHMLoader<EnpcCorporations> LOADER_SNAKEYAML = new SnakeYamlLHMLoader<>(SDE_FILE_YAML,
			EnpcCorporations.class, Set.of("tickerName"));

	public static final JacksonYamlLHMLoader<EnpcCorporations> LOADER = LOADER_SNAKEYAML;

	//
	// file structure
	//

	public List<Integer> allowedMemberRaces = new ArrayList<>();
	public int ceoID;
	public HashMap<Integer, BigDecimal> corporationTrades = new HashMap<>();
	public boolean deleted;
	public HashMap<String, String> description = new HashMap<>();
	public int enemyID;
	public HashMap<Integer, BigDecimal> exchangeRates = new HashMap<>();
	public String extent;
	public int factionID;
	public int friendID;
	public boolean hasPlayerPersonnelManager;
	public int iconID;
	public int initialPrice;
	public HashMap<Integer, Integer> investors = new HashMap<>();
	public int mainActivityID;
	public int memberLimit;
	public BigDecimal minSecurity;
	public int minimumJoinStanding;
	public HashMap<String, String> name = new HashMap<>();
	public int raceID;
	public int secondaryActivityID;
	public boolean sendCharTerminationMessage;
	public long shares;
	public String size;
	public BigDecimal sizeFactor;
	public int solarSystemID;
	public int stationID;
	public BigDecimal taxRate;
	public String tickerName;
	public boolean uniqueName;
	public HashMap<Integer, Edivisions> divisions = new HashMap<>();
	public ArrayList<Integer> lpOfferTables = new ArrayList<>();

	public static class Edivisions {
		public int divisionNumber;
		public int leaderID;
		public int size;
	}


	//

	public String enName() {
		return name.getOrDefault("en", "unknowncorp" + name);
	}

	public static final int CONCORD_ID = 1000125;

	public static Map<Integer, BigDecimal> concordRates() {
		return LOADER.load().get(CONCORD_ID).exchangeRates;
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : solarSystemID=" + first.solarSystemID + " name=" + first.enName());
	}

}
