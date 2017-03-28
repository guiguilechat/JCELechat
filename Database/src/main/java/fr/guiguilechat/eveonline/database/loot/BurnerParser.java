package fr.guiguilechat.eveonline.database.loot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.EveCentral;
import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;

public class BurnerParser {

	public static void main(String[] args) {
		BurnerParser bp = new BurnerParser();
		EveCentral central = new EveCentral(-1);
		ArrayList<LootEntry> list = bp.loadDirectory(new File(args[0]));

		central.cache(list.stream().flatMapToInt(e -> e.loots.keySet().stream().mapToInt(i -> i)).distinct().toArray());
		LinkedHashMap<String, Double> catValue = new LinkedHashMap<>();
		LinkedHashMap<String, Integer> catNumber = new LinkedHashMap<>();
		for (LootEntry le : list) {
			double lootBO = 0;
			for (Entry<Integer, Integer> e : le.loots.entrySet()) {
				lootBO += central.getBO(e.getKey()) * e.getValue();
			}
			for (String cat : new String[] { "burner", le.type + "-" + le.race + "-" + le.sec, le.type + "-" + le.race,
					"" + le.sec,
					le.type, le.race }) {
				catValue.put(cat, lootBO + catValue.getOrDefault(cat, 0.0));
				catNumber.put(cat, 1 + catNumber.getOrDefault(cat, 0));
			}
		}
		HashMap<String, Double> catToAvgBO = new HashMap<>();
		for (Entry<String, Double> e : catValue.entrySet()) {
			catToAvgBO.put(e.getKey(), e.getValue() / catNumber.get(e.getKey()));
		}
		ArrayList<Entry<String, Double>> sorted = new ArrayList<>(catToAvgBO.entrySet());
		Collections.sort(sorted, (e1, e2) -> (int) Math.signum(e2.getValue() - e1.getValue()));
		DecimalFormat df = new DecimalFormat("#.##");
		for (Entry<String, Double> e : sorted) {
			System.err.println(e.getKey() + " : " + df.format(e.getValue() / 1000000) + " : " + catNumber.get(e.getKey()));
		}
	}

	public static class LootEntry {

		/**
		 * date of loot
		 */
		public Date date;

		/**
		 * team | base | agent
		 */
		public String type;

		/**
		 * angel, jaguar, ..
		 */
		public String race;

		public float sec;

		public HashMap<Integer, Integer> loots = new HashMap<>();

	}

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	SDEData sde = new SDEData();

	public ArrayList<LootEntry> loadDirectory(File lootDir) {
		ArrayList<LootEntry> ret = new ArrayList<>();
		for (File lootFile : lootDir.listFiles()) {
			try {
				loadFile(lootFile, ret);
			} catch (IOException | ParseException e) {
				e.printStackTrace(System.err);
			}
		}
		return ret;
	}

	public void loadFile(File lootFile, ArrayList<LootEntry> list) throws IOException, ParseException {
		Date date = df.parse(lootFile.getName().split("\\.")[0]);
		// 0 = wait for new entry
		// 1 = writing an entry
		int state = 0;
		String line = null;
		LootEntry entry = null;
		BufferedReader br = new BufferedReader(new FileReader(lootFile));
		while ((line = br.readLine()) != null) {
			// System.err.println(line);
			switch (state) {
			case 0:
				if (line.length() > 0) {
					entry = new LootEntry();
					entry.date = date;
					String[] tokens = line.split(" ");
					entry.type = rename(tokens[0]);
					entry.race = rename(tokens[1]);
					entry.sec = Float.parseFloat(tokens[2]);
					list.add(entry);
					state = 1;
				}
				break;
			case 1:
				if (line.length() == 0) {
					state = 0;
				} else {
					String[] tokens = line.split("\\t");
					Integer id = sde.getDico().get(tokens[0].toLowerCase());
					Integer nb = entry.loots.get(id);
					nb = Integer.parseInt(tokens[1].replaceAll("[^\\d]", "")) + (nb == null ? 0 : nb);
					entry.loots.put(id, nb);
					// System.err.println("" + id + "->" + nb);
				}
				break;
			}
		}
		br.close();
	}

	/**
	 * to rename the factions//types
	 */
	HashMap<String, String> renamed = new HashMap<>();
	{
	}

	String rename(String original) {
		return renamed.getOrDefault(original, original);
	}

}
