package fr.guiguilechat.eveonline.programms.lootstats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.yaml.MetaInf;
import fr.guiguilechat.eveonline.database.yaml.Type;

public class LootParser {

	private static final Logger logger = LoggerFactory.getLogger(LootParser.class);

	private EveDatabase evedb;

	public LootParser(EveDatabase evedb) {
		this.evedb = evedb;
	}

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

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	protected HashSet<String> undecoded = new HashSet<>();

	public void loadFile(File lootFile, ArrayList<LootEntry> list) throws IOException, ParseException {
		Date date = df.parse(lootFile.getName().split("\\.")[0]);
		// 0 = wait for new entry
		// 1 = writing an entry
		int state = 0;
		String line = null;
		LootEntry entry = null;
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(lootFile));
		while ((line = br.readLine()) != null) {
			// System.err.println(line);
			try {
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
						Type t = evedb.getTypeByName(tokens[0]);
						if (t == null) {
							MetaInf m = evedb.getMetaInfs().get(tokens[0]);
							if (m != null) {
								t = new Type();
								t.catName = "";
								t.groupName = "";
								t.name = tokens[0];
								t.id = m.id;
								t.volume = m.volume;
							} else if (undecoded.add(tokens[0])) {
								logger.debug("can't decode " + tokens[0]);
							}
						}
						if (t != null) {
							Integer nb = entry.loots.get(t.id);
							if (tokens[1].length() > 0) {
								nb = Integer.parseInt(tokens[1].replaceAll("[^\\d]", "")) + (nb == null ? 0 : nb);
							} else {
								nb = 1;
							}
							entry.loots.put(t.id, nb);
							// System.err.println("" + id + "->" + nb);
						}
					}
					break;
				}
			} catch (Exception e) {
				System.err.println("error line " + line + "\n " + lootFile.getName() + " state=" + state);
				throw e;
			}
		}
		br.close();
	}

	/**
	 * to rename the factions//types
	 */
	private HashMap<String, String> renamed = new HashMap<>();
	{
		// add here elements to rename to another. eg renamed.put("Angels",
		// "angel");
	}

	String rename(String original) {
		return renamed.getOrDefault(original, original);
	}

}
