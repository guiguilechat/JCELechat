package fr.guiguilechat.eveonline.database.loot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.stream.Stream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;

import fr.guiguilechat.eveonline.database.EveCentral;
import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.sde.bsd.EdgmTypeAttributes;

public class BurnerParser {

	public static void main(String[] args) {
		BurnerParser bp = new BurnerParser();
		EveCentral central = new EveCentral(0);
		ArrayList<LootEntry> list = bp.loadDirectory(new File(args[0]));

		int[] allItemsIds = list.stream().flatMapToInt(e -> e.loots.keySet().stream().mapToInt(i -> i)).distinct()
				.toArray();
		central.cache(allItemsIds);
		HashSet<Integer> allFactionItems = new HashSet<>();
		int metaLevelAttribute = 633;
		System.err.println("factionloot:");
		for (int i : allItemsIds) {
			HashMap<Integer, EdgmTypeAttributes> mmap = bp.sde.getTypeAttributes().get(i);
			if (mmap != null) {
				EdgmTypeAttributes metal = mmap.get(metaLevelAttribute);
				if (metal != null && metal.valueInt > 4) {
					System.err.println("  " + bp.sde.getType(i).enName() + " : "
							+ list.stream().mapToInt(le -> le.loots.getOrDefault(i, 0)).sum());
					allFactionItems.add(i);
				}
			}
		}
		IntToDoubleFunction cost = central::getBO;
		LinkedHashMap<String, Double> catValue = new LinkedHashMap<>();
		LinkedHashMap<String, Integer> catNumber = new LinkedHashMap<>();

		HashMap<String, Double> catToAvgBO = translateLootsToCat(list.stream(), cost, catValue, catNumber);
		ArrayList<Entry<String, Double>> sorted = new ArrayList<>(catToAvgBO.entrySet());
		Collections.sort(sorted, (e1, e2) -> catNumber.get(e2.getKey()) - catNumber.get(e1.getKey()));
		System.err.println("\ndrops:");
		for (Entry<String, Double> e : sorted) {
			System.err.println("  " + e.getKey() + ": " + catNumber.get(e.getKey()));
		}

		Collections.sort(sorted, (e1, e2) -> (int) Math.signum(e2.getValue() - e1.getValue()));
		System.err.println("\navgValueM:");
		DecimalFormat df = new DecimalFormat("#.##");
		for (Entry<String, Double> e : sorted) {
			System.err.println("  " + e.getKey() + ": " + df.format(e.getValue() / 1000000));
		}

		catValue.clear();
		catNumber.clear();
		cost = i -> allFactionItems.contains(i) ? 1 : 0;
		catToAvgBO = translateLootsToCat(list.stream(), cost, catValue, catNumber);
		sorted = new ArrayList<>(catToAvgBO.entrySet());
		sorted.removeIf(e -> catNumber.get(e.getKey()) < 10);
		Collections.sort(sorted, (e1, e2) -> (int) Math.signum(e2.getValue() - e1.getValue()));
		System.err.println("\nfactiondroppct:");
		df = new DecimalFormat("#.#");
		for (Entry<String, Double> e : sorted) {
			System.err
			.println("  " + e.getKey() + ": " + df.format(e.getValue() * 100));
		}


		makeChart(list.stream(), central::getBO, le -> new String[] { "" + le.sec }, "ss value per week", Week::new);
		makeChart(list.stream(), central::getBO, le -> new String[] { "all", "" + le.type }, "type value per week",
				Week::new);
		makeChart(list.stream(), central::getBO, le -> new String[] { "all", "" + le.type }, " type value per month",
				Month::new);
		makeChart(list.stream(), central::getBO,
				le -> new String[] { "all", le.type.substring(0, 1) + "-" + le.race.substring(0, 2) },
				"type-race value per week", Week::new);
	}

	public static HashMap<String, Double> translateLootsToCat(Stream<LootEntry> list, IntToDoubleFunction cost,
			LinkedHashMap<String, Double> catValue, LinkedHashMap<String, Integer> catNumber) {
		list.forEach(le -> {
			double lootBO = 0;
			for (Entry<Integer, Integer> e : le.loots.entrySet()) {
				lootBO += cost.applyAsDouble(e.getKey()) * e.getValue();
			}
			for (String cat : new String[] { "all", le.type + "-" + le.race + "-" + le.sec, le.type + "-" + le.race,
					"" + le.sec, le.type }) {
				catValue.put(cat, lootBO + catValue.getOrDefault(cat, 0.0));
				catNumber.put(cat, 1 + catNumber.getOrDefault(cat, 0));
			}
		});
		HashMap<String, Double> catToAvgBO = new HashMap<>();
		for (Entry<String, Double> e : catValue.entrySet()) {
			catToAvgBO.put(e.getKey(), e.getValue() / catNumber.get(e.getKey()));
		}
		return catToAvgBO;
	}

	public static double getAvgValue(Stream<LootEntry> list, IntToDoubleFunction cost) {
		double totalValue = 0.0;
		int nbloots = 0;
		for (LootEntry le : list.toArray(LootEntry[]::new)) {
			totalValue += getValue(le, cost);
			nbloots++;
		}
		return totalValue / nbloots;
	}

	public static double getValue(LootEntry le, IntToDoubleFunction cost) {
		return le.loots.entrySet().stream().mapToDouble((e) -> e.getValue() * cost.applyAsDouble(e.getKey())).sum();
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
					if (id != null) {
						Integer nb = entry.loots.get(id);
						nb = Integer.parseInt(tokens[1].replaceAll("[^\\d]", "")) + (nb == null ? 0 : nb);
						entry.loots.put(id, nb);
						// System.err.println("" + id + "->" + nb);
					} else {
						System.err.println("can't decode " + tokens[0]);
					}
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

	/**
	 * make a chart on a stream of loot entries
	 *
	 * @param loots
	 *          the loot entries
	 * @param cost
	 *          the function to get the cost of an element ID
	 * @param grouper
	 *          generates the groups a LootEntry belongs to
	 * @param name
	 *          the name of the chart
	 * @param dateGrouper
	 *          translates the dates to their period (eg {@link Week}::new)
	 */
	public static void makeChart(Stream<LootEntry> loots, IntToDoubleFunction cost,
			Function<LootEntry, String[]> grouper, String name, Function<Date, RegularTimePeriod> dateGrouper) {

		TimeSeriesCollection dataset = new TimeSeriesCollection();

		for (Entry<String, Map<RegularTimePeriod, Double>> e : makeGroupToPeriodToValues(loots, cost, grouper, dateGrouper)
				.entrySet()) {
			TimeSeries ts = new TimeSeries(e.getKey());
			dataset.addSeries(ts);
			for (Entry<RegularTimePeriod, Double> e2 : e.getValue().entrySet()) {
				ts.add(e2.getKey(), e2.getValue() / 1000000);

			}
		}
		// create the chart...

		JFreeChart chart = ChartFactory.createTimeSeriesChart("average " + name, "loot date", "value in M",
				dataset);
		((DateAxis) chart.getXYPlot().getDomainAxis()).setDateFormatOverride(new SimpleDateFormat("yyyy-w"));
		FileOutputStream out = null;
		try {
			File dir = new File("target/");
			dir.mkdirs();
			out = new FileOutputStream(new File(dir, name + ".png"));
			ChartUtilities.writeChartAsPNG(out, chart, 1400, 800);
		} catch (IOException iOException) {
		} finally {
			try {
				out.close();
			} catch (Exception ignore) {
			}
		}
	}

	public static Map<String, Map<RegularTimePeriod, Double>> makeGroupToPeriodToValues(Stream<LootEntry> loots,
			IntToDoubleFunction cost, Function<LootEntry, String[]> grouper, Function<Date, RegularTimePeriod> dateGrouper) {
		Map<String, Map<RegularTimePeriod, List<LootEntry>>> groupToDateToLoots = new HashMap<>();
		// for each week
		loots.forEach(le -> {
			RegularTimePeriod period = dateGrouper.apply(le.date);
			for (String group : grouper.apply(le)) {
				Map<RegularTimePeriod, List<LootEntry>> groupWeeks = groupToDateToLoots.get(group);
				if (groupWeeks == null) {
					groupWeeks = new HashMap<>();
					groupToDateToLoots.put(group, groupWeeks);
				}
				List<LootEntry> weekLoots = groupWeeks.get(period);
				if (weekLoots == null) {
					weekLoots = new ArrayList<>();
					groupWeeks.put(period, weekLoots);
				}
				weekLoots.add(le);
			}
		});
		Map<String, Map<RegularTimePeriod, Double>> groupToDateToValue = new HashMap<>();
		for (Entry<String, Map<RegularTimePeriod, List<LootEntry>>> e : groupToDateToLoots.entrySet()) {
			Map<RegularTimePeriod, Double> weekToValue = new HashMap<>();
			groupToDateToValue.put(e.getKey(), weekToValue);
			for (Entry<RegularTimePeriod, List<LootEntry>> e2 : e.getValue().entrySet()) {
				weekToValue.put(e2.getKey(), getAvgValue(e2.getValue().stream(), cost));
			}
		}
		return groupToDateToValue;
	}

}
