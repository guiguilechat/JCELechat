package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EnpcCorporations {

	//
	// SDE loading
	//

	public static final IntMapLoader<EnpcCorporations> LOADER = new IntMapLoader<>(
			"npcCorporations",
			EnpcCorporations.class);

	//
	// file structure
	//

	/** {@link Eraces} */
	public List<Integer> allowedMemberRaces = new ArrayList<>();
	public int ceoID;
	public HashMap<Integer, BigDecimal> corporationTrades = new HashMap<>();
	public boolean deleted;
	public HashMap<String, String> description = new HashMap<>();
	public int enemyID;
	/**
	 * key is {@link EnpcCorporations}, value is multiplier from key to this. Only
	 * present for concord.
	 */
	public HashMap<Integer, BigDecimal> exchangeRates = new HashMap<>();
	public String extent;
	/** {@link Efactions} */
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
	/**
	 * references the LP offers which are not provided in the SDE.
	 */
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
		return LOADER.yaml().load().get(CONCORD_ID).exchangeRates;
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : solarSystemID=" + first.solarSystemID + " name=" + first.enName());
	}

}
