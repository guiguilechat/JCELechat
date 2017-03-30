package fr.guiguilechat.eveonline.database;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EtypeIDs;

public class ShowMinePrice {

	public static class AsteroidData {
		EtypeIDs type;
		double bo;
		double so;
		double volume = 1;
		public float maxSecurity = -1.0f;

		public double boPerVolume() {
			return bo / volume;
		}
	}

	public static final DecimalFormat secFormat = new DecimalFormat("#.#");

	public static void main(String[] args) {
		SDEData sde = new SDEData();
		LinkedHashMap<String, Integer> hubs = new LinkedHashMap<>();
		hubs.put("Jita", EveCentral.JITA_SYSTEM);
		hubs.put("Amarr", EveCentral.AMARR_SYSTEM);
		hubs.put("Rens", EveCentral.RENS_SYSTEM);
		hubs.put("Dodixie", EveCentral.DODIXIE_SYSTEM);
		hubs.put("Hek", EveCentral.HEK_SYSTEM);

		LinkedHashMap<String, IntPredicate> typefilters = new LinkedHashMap<>();
		// group 465=ice
		typefilters.put("ore", i -> sde.getType(i).groupID != 465);
		typefilters.put("ice", i -> sde.getType(i).groupID == 465);

		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);

		new Yaml(options).dump(makePrices(sde, typefilters, hubs), new PrintWriter(System.out));

	}

	public static AsteroidData getAsteroidData(SDEData sde, int id, EveCentral market) {
		AsteroidData data = new AsteroidData();
		EtypeIDs type = sde.getType(id);
		data.type = type;
		data.volume = type.volume;
		data.bo = market.getBO(id);
		data.so = market.getSO(id);
		String desc = type.description.getOrDefault("en", "");
		if (desc.contains("Available")) {
			String availables = desc.replaceAll("\\n|\\r", "").replaceAll(".*'>", "").replaceAll("</.*", "");
			data.maxSecurity = Float.parseFloat(availables);
		} else {
			// System.err.println("skipping " + type.enName() + " as non max secu");
		}
		return data;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> makePrices(
			SDEData sde, Map<String, IntPredicate> typefilters, Map<String, Integer> marketLimits) {
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> ret = new LinkedHashMap<>();
		for (Entry<String, Integer> e : marketLimits.entrySet()) {
			ret.put(e.getKey(), makePrices(sde, typefilters, e.getValue()));
		}
		return ret;
	}

	public static LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>> makePrices(SDEData sde,
			Map<String, IntPredicate> typefilters, int marketlimit) {
		LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>> ret = new LinkedHashMap<>();
		for (Entry<String, IntPredicate> e : typefilters.entrySet()) {
			ret.put(e.getKey(), makePrices(sde, e.getValue(), marketlimit));
		}
		return ret;
	}

	public static LinkedHashMap<Float, LinkedHashMap<String, Double>> makePrices(SDEData sde, IntPredicate typefilter,
			int marketlimit) {
		LinkedHashMap<Float, LinkedHashMap<String, Double>> ret = new LinkedHashMap<>();
		int[] typeIDs = sde.getTypeIDsForCategory(25).filter(typefilter).toArray();
		sde.central(marketlimit).cache(typeIDs);
		AsteroidData[] datas2 = IntStream.of(typeIDs).mapToObj(i -> getAsteroidData(sde, i, sde.central(marketlimit)))
				.filter(ad -> ad.maxSecurity > -1).toArray(AsteroidData[]::new);
		Arrays.sort(datas2, (d1, d2) -> (int) Math.signum(d2.boPerVolume() - d1.boPerVolume()));
		Arrays.stream(datas2).map(d -> (Float) d.maxSecurity).distinct().sorted((f1, f2) -> (int) Math.signum(f2 - f1))
				.filter(i -> i > -1).forEach(systemSec -> {
			LinkedHashMap<String, Double> values = new LinkedHashMap<>();
			ret.put((float) systemSec, values);
			for (AsteroidData data : datas2) {
				if (data.maxSecurity == systemSec) {
					values.put(data.type.enName(), data.boPerVolume());
				}
			}
		});
		return ret;

	}

}
