import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.locations.Distances;
import fr.guiguilechat.eveonline.database.yaml.Location;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class AnalyzeBurnersDest {

	protected static final String[] types = new String[] { "aangel", "ablood", "aguristas", "aserpentis", "asansha",
			"bangel", "bblood", "bguristas", "bserpentis", "tenyo", "thawk", "tjaguar", "tvengeance" };
	protected static final HashMap<String, Integer> types2index = new HashMap<>();
	static {
		for (int idx = 0; idx < types.length; idx++) {
			types2index.put(types[idx], idx);
		}
	}

	protected static String separator = ";";
	protected static String offsetTypeData = separator + separator + separator + separator;

	public static void main(String[] args) throws FileNotFoundException {

		YamlDatabase db = new YamlDatabase();
		Distances d = new Distances(db);
		File inDir = new File(args[0]);
		HashMap<String, Integer> types2index = new HashMap<>();
		for (int idx = 0; idx < types.length; idx++) {
			types2index.put(types[idx], idx);
		}
		File outputDir = new File("src/main/resources/");
		outputDir.mkdirs();

		for (File textFile : inDir.listFiles()) {
			if (!textFile.isFile()) {
				continue;
			}
			AnalyzeBurnersDest an = new AnalyzeBurnersDest();
			an.load(textFile);
			System.err.println(an.system);
			Location l = d.db.getLocations().get(an.system);

			PrintStream ps = new PrintStream(new File(outputDir, an.system + ".csv"));

			ArrayList<String> dests = new ArrayList<>(an.dest2counts.keySet());
			Collections.sort(dests);
			int[] systemDistances = dests.stream().mapToInt(n -> d.distJumps(n, an.system)).toArray();

			ps.print(offsetTypeData);
			for (String name : dests) {
				ps.print(separator + name);
			}
			ps.println();

			ps.print(offsetTypeData + "jumps:");
			for (int dst : systemDistances) {
				ps.print(separator + dst);
			}
			ps.println();

			ps.println();
			ps.println("type" + separator + "count" + separator + "avgdst" + separator + "maxdst" + separator);
			for (String type : types) {
				int idx = types2index.get(type);
				printDestData(ps, type, an.dest2counts.values().stream(), t -> t[idx], systemDistances);
			}

			ps.println();

			printDestData(ps, "agent", an.dest2counts.values().stream(), t -> IntStream.of(t).skip(0).limit(5).sum(),
					systemDistances);
			printDestData(ps, "base", an.dest2counts.values().stream(), t -> IntStream.of(t).skip(5).limit(4).sum(),
					systemDistances);
			printDestData(ps, "team", an.dest2counts.values().stream(), t -> IntStream.of(t).skip(9).limit(4).sum(),
					systemDistances);
			printDestData(ps, "all", an.dest2counts.values().stream(), t -> IntStream.of(t).sum(), systemDistances);

			ps.println();
			ps.println("maxjumps" + separator + "avgsysdst");
			ps.println("5" + separator + d.avgDistWithin(l, 5));
			ps.println("6" + separator + d.avgDistWithin(l, 6));
			ps.println("7" + separator + d.avgDistWithin(l, 7));
			ps.println("8" + separator + d.avgDistWithin(l, 8));

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
	protected static void printDestData(PrintStream ps, String name, Stream<int[]> system2typeIdxCount,
			ToIntFunction<int[]> mapper, int[] systemDistances) {
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
		ps.print(separator + totaldst * 1.0 / total);
		ps.print(separator + maxdst);
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
	}

}
