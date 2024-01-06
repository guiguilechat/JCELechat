package fr.guiguilechat.jcelechat.programs.lootstats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;

public class LootParser {

	private static final Logger logger = LoggerFactory.getLogger(LootParser.class);

	public LootParser() {
	}

	public ArrayList<LootEntry> loadDirectory(File lootDir) {
		ArrayList<LootEntry> ret = new ArrayList<>();
		for (File lootFile : lootDir.listFiles()) {
			try {
				loadFile(lootFile, ret);
			} catch (Exception e) {
				logger.debug("while loading file " + lootFile, e);
			}
		}
		return ret;
	}

	protected HashSet<String> undecoded = new HashSet<>();

	public void loadFile(File lootFile, ArrayList<LootEntry> list) throws IOException, ParseException {
		boolean isFilenamePrinted = false;
		// logger.debug("name is " + lootFile.getName());
		LocalDate date = LocalDate.parse(lootFile.getName().split("\\.")[0], DateTimeFormatter.ISO_LOCAL_DATE);
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
							EveType t = TypeIndex.getTypes(tokens[0]).get(0);
							if (t == null) {
								if (undecoded.add(tokens[0])) {
									if (!isFilenamePrinted) {
										logger.debug("in file " + lootFile.getAbsolutePath());
										isFilenamePrinted = true;
									}
									logger.debug("can't decode " + tokens[0]);
								}
							} else {
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
