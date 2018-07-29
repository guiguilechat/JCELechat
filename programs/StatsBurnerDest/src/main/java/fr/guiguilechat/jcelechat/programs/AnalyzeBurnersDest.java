package fr.guiguilechat.jcelechat.programs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.locations.Constellation;
import fr.guiguilechat.jcelechat.model.sde.locations.Distances;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class AnalyzeBurnersDest {

	protected String[] types = new String[] { "aangel", "ablood", "aguristas", "aserpentis", "asansha",
			"bangel", "bblood", "bguristas", "bserpentis", "tenyo", "thawk", "tjaguar", "tvengeance" };
	protected String[] aliases = new String[] { "aang", "abld", "agur", "aser", "asan", "bang", "bbld", "bgur", "bser",
			"teny", "thwk", "tjag", "tven" };

	/**
	 * for each type of burner, its index in a location's numbers of missions
	 */
	protected HashMap<String, Integer> types2index = new HashMap<>();
	{
		for (int idx = 0; idx < types.length; idx++) {
			types2index.put(types[idx], idx);
			types2index.put(aliases[idx], idx);
		}
	}

	protected DecimalFormat df = new DecimalFormat("#.##");
	protected String separator = ";";
	protected String offsetTypeData = separator + separator + separator + separator + separator;

	public static void main(String[] args) throws FileNotFoundException {
		new AnalyzeBurnersDest().analyze(args);
	}

	public void analyze(String[] args) throws FileNotFoundException {
		Distances d = new Distances();
		File inDir = new File(args[0]);
		HashMap<String, Integer> types2index = new HashMap<>();
		for (int idx = 0; idx < types.length; idx++) {
			types2index.put(types[idx], idx);
		}
		File outputDir = new File("src/main/resources/");
		outputDir.mkdirs();
		LinkedHashMap<String, SolarSystem> systems = SolarSystem.load();
		LinkedHashMap<String, Constellation> constels = Constellation.load();

		for (File textFile : inDir.listFiles()) {
			if (!textFile.isFile()) {
				continue;
			}
			AnalyzeBurnersDest an = new AnalyzeBurnersDest();
			an.load(textFile);

			PrintStream ps = new PrintStream(new File(outputDir, an.system + ".csv"));
			System.out.println(an.system);

			SolarSystem origin = systems.get(system);

			ArrayList<String> possibleSystemNames = new ArrayList<>();
			ArrayList<Integer> possibleSystemCJumps = new ArrayList<>();
			ArrayList<Integer> possibleSystemJumps = new ArrayList<>();
			// for each system of the constellation
			for (String sname : constels.get(origin.constellation).systems) {
				possibleSystemNames.add(sname);
				possibleSystemCJumps.add(0);
				possibleSystemJumps.add(d.distJumps(origin, systems.get(sname)));
			}
			// for each neighbourgh constellation
			for (String cname : constels.get(origin.constellation).adjacentConstellations) {
				for (String sname : constels.get(cname).systems) {
					possibleSystemNames.add(sname);
					possibleSystemCJumps.add(1);
					possibleSystemJumps.add(d.distJumps(origin, systems.get(sname)));
				}
			}


			ps.println(an.system);
			ps.print(offsetTypeData);
			for (String name : possibleSystemNames) {
				ps.print(separator + name);
			}
			ps.println();

			int[] systemDistances = possibleSystemJumps.stream().mapToInt(i -> i).toArray();
			int[] constelDistances = possibleSystemCJumps.stream().mapToInt(i -> i).toArray();
			ps.print(offsetTypeData + "jumps:");
			for (int dst : systemDistances) {
				ps.print(separator + dst);
			}
			ps.println();


			ps.println();
			ps.println("type" + separator + "count" + separator + "avgdst" + separator + "maxdst" + separator + "constels"
					+ separator);
			for (String type : types) {
				int idx = types2index.get(type);
				printDestData(ps, type, an.dest2counts.values().stream(), t -> t[idx], systemDistances, constelDistances);
			}

			ps.println();

			ps.println("probability /1000");
			int allnb = an.dest2counts.values().stream().flatMapToInt(i -> IntStream.of(i)).sum();
			printDestData(ps, "all", an.dest2counts.values().stream(), t -> 1000 * IntStream.of(t).sum() / allnb,
					systemDistances, constelDistances);

			ps.close();
		}

	}

	/**
	 * utility to avoid code duplication. print the analysis of a type of burner
	 *
	 * @param ps
	 *          where to print data
	 * @param name
	 *          the name of the analysis
	 * @param system2typeIdxCount
	 *          table of type values for each destination
	 * @param mapper
	 *          the function to extract specific location data from the
	 *          destination table "counts".
	 */
	protected void printDestData(PrintStream ps, String name, Stream<int[]> system2typeIdxCount,
			ToIntFunction<int[]> mapper, int[] systemDistances, int[] constelDistances) {
		ps.print(name);
		int[] systemCount = system2typeIdxCount.mapToInt(mapper).toArray();
		int total = 0, totaldst = 0, maxdst = 0;
		for (int i = 0; i < systemCount.length; i++) {
			total += systemCount[i];
			if (systemCount[i] > 0) {
				maxdst = Math.max(maxdst, systemDistances[i]);
			}
			totaldst += systemCount[i] * systemDistances[i];
		}
		ps.print(separator + total);
		ps.print(separator + (total > 0 ? df.format(1.0 * totaldst / total) : 0));
		ps.print(separator);

		for (int i : systemCount) {
			ps.print(separator + i);
		}


		ps.println();
	}

	protected String system;

	LinkedHashMap<String, int[]> dest2counts = new LinkedHashMap<>();

	@SuppressWarnings("resource")
	protected void load(File textFile) throws FileNotFoundException {

		system = textFile.getName().split("\\.")[0];
		system = system.substring(0, 1).toUpperCase() + system.substring(1);

		new BufferedReader(new FileReader(textFile)).lines().filter(l -> l.length() > 0).forEach(line -> {
			String[] tokens = line.split(" ");
			String type = tokens[0];
			String dest = tokens[1];
			dest = dest.substring(0, 1).toUpperCase() + dest.substring(1);
			int[] counts = dest2counts.get(dest);
			if (counts == null) {
				counts = new int[types.length];
				dest2counts.put(dest, counts);
			}
			try {
				int idx = types2index.get(type);
				counts[idx]++;
			} catch (Exception e) {
				System.err.println("can't get idx of " + type + " indexes are " + Arrays.asList(types));
			}
		});

		// sort the map by key
		LinkedHashMap<String, int[]> sorted = new LinkedHashMap<>();
		dest2counts.entrySet().stream().sorted(Map.Entry.comparingByKey())
		.forEachOrdered(e -> sorted.put(e.getKey(), e.getValue()));
		dest2counts = sorted;
	}

}
