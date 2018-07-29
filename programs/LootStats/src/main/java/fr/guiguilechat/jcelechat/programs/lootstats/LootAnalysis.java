package fr.guiguilechat.jcelechat.programs.lootstats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.IntToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import fr.guiguilechat.jcelechat.esi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaInf;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.jcelechat.model.sde.yaml.YAMLTools;

/**
 * analysis of a series of item drops
 *
 */
public class LootAnalysis {

	private static final Logger logger = LoggerFactory.getLogger(LootAnalysis.class);

	/** number of drop entries in this analysis */
	public int entries = 0;

	public ArrayList<String> bps = new ArrayList<>();

	/**
	 * average BO of the loot in Jita
	 */
	public double avgBO = 0;

	public double bpcFreq = 0;

	public String desc = null;

	public static LootAnalysis analyse(Stream<LootEntry> entries, IntToDoubleFunction cost) {
		LootAnalysis ret = new LootAnalysis();
		HashMap<Integer, Integer> totalDrop = new HashMap<>();
		HashSet<String> bps = new HashSet<>();
		entries.forEach(e -> {
			ArrayList<Object> containsBP = new ArrayList<>();
			e.loots.forEach((id, nb) -> {
				totalDrop.put(id, totalDrop.getOrDefault(id, 0) + nb);
				Item t = MetaInf.getItem(id);
				if (t != null) {
					if (t.getCategory().equals(Blueprint.class)) {
						containsBP.add(null);
						bps.add(t.name);
					}
				} else {
					// logger.error("can't find type for id " + id);
				}
			});
			ret.entries++;
			if (!containsBP.isEmpty()) {
				ret.bpcFreq++;
			}
		});
		if (ret.entries > 0) {
			ret.bpcFreq /= ret.entries;
			ret.avgBO = totalDrop.entrySet().parallelStream().mapToDouble(e -> e.getValue() * cost.applyAsDouble(e.getKey()))
					.sum()
					/ ret.entries;
			ret.bps.addAll(bps);
			return ret;
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		LootParser bp = new LootParser();
		RegionalMarket em = ESIAccess.INSTANCE.markets.getMarket(Region.load().get("TheForge").id);
		File srcDir = new File("src/main/resources");
		srcDir.mkdirs();
		int parrallelism = Runtime.getRuntime().availableProcessors() * 10;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		Stream.of(args).forEach(dirname -> {
			logger.debug("analyzing " + dirname);
			File dir = new File(dirname);
			File outDir = new File(srcDir, dir.getName());
			outDir.mkdirs();
			ArrayList<LootEntry> al = bp.loadDirectory(dir);
			String[] types = al.stream().map(le -> le.type).distinct().toArray(String[]::new);
			String[] races = al.stream().map(le -> le.race).distinct().toArray(String[]::new);
			Map<String, LootAnalysis> grouped = Stream.of(types).parallel().flatMap(type -> {
				return Stream.of(races).parallel().map(race -> {
					LootAnalysis la = analyse(al.stream().filter(le -> type.equals(le.type) && race.equals(le.race)),
							i -> em.getBO(i, 1).get());
					if (la != null) {
						la.desc = type + " " + race;
					}
					return la;
				});
			}).filter(la -> la != null).collect(Collectors.toMap(la -> la.desc, la -> la));

			LinkedHashMap<String, LootAnalysis> grouped2 = new LinkedHashMap<>();
			grouped.entrySet().stream().sorted((e1, e2) -> (int) Math.signum(e2.getValue().avgBO - e1.getValue().avgBO))
			.forEachOrdered(e -> grouped2.put(e.getKey(), e.getValue()));

			try {
				makeYaml().dump(grouped2, new FileWriter(new File(outDir, "result.txt")));
			} catch (IOException e3) {
				logger.warn("while writing analyzis of " + dirname, e3);
			}

		});
	}

	public static Yaml makeYaml() {
		Yaml ret = new Yaml(new SafeConstructor(), new CleanRepresenter(), YAMLTools.blockDumper());
		return ret;
	}

}
