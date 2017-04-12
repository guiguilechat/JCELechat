package fr.guiguilechat.eveonline.database;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class ShowMinePrice {

	public static class AsteroidData {
		Asteroid type;
		double bo;
		double so;
		String name;
		public double boPerVolume() {
			return bo / type.volume;
		}
	}

	public static final DecimalFormat secFormat = new DecimalFormat("#.#");

	public static void main(String[] args) {
		DataBase db = new YamlDatabase();
		LinkedHashMap<String, Integer> hubs = new LinkedHashMap<>();
		hubs.put("Jita", EveCentral.JITA_SYSTEM);
		hubs.put("Amarr", EveCentral.AMARR_SYSTEM);
		hubs.put("Rens", EveCentral.RENS_SYSTEM);
		hubs.put("Dodixie", EveCentral.DODIXIE_SYSTEM);
		hubs.put("Hek", EveCentral.HEK_SYSTEM);

		LinkedHashMap<String, Predicate<Asteroid>> typefilters = new LinkedHashMap<>();
		// group 465=ice
		typefilters.put("ore", a -> !a.groupName.equals("Ice"));
		typefilters.put("ice", a -> a.groupName.equals("Ice"));

		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);

		new Yaml(options).dump(makePrices(db, typefilters, hubs), new PrintWriter(System.out));

	}

	public static AsteroidData getAsteroidData(Asteroid astero, String name, EveCentral market) {
		AsteroidData data = new AsteroidData();
		data.name = name;
		data.type = astero;
		data.bo = market.getBO(astero.id);
		data.so = market.getSO(astero.id);
		return data;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> makePrices(
			DataBase db, Map<String, Predicate<Asteroid>> typefilters, Map<String, Integer> marketLimits) {
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> ret = new LinkedHashMap<>();
		for (Entry<String, Integer> e : marketLimits.entrySet()) {
			ret.put(e.getKey(), makePrices(db, typefilters, e.getValue()));
		}
		return ret;
	}

	public static LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>> makePrices(DataBase db,
			Map<String, Predicate<Asteroid>> typefilters, int marketlimit) {
		LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>> ret = new LinkedHashMap<>();
		for (Entry<String, Predicate<Asteroid>> e : typefilters.entrySet()) {
			ret.put(e.getKey(), makePrices(db, e.getValue(), marketlimit));
		}
		return ret;
	}

	/**
	 * make the asteroid data for give database, filter on asteroid, and market
	 * limit for central
	 *
	 * @param db
	 * @param typefilter
	 * @param marketlimit
	 * @return
	 */
	public static LinkedHashMap<Float, LinkedHashMap<String, Double>> makePrices(DataBase db,
			Predicate<Asteroid> typefilter,
			int marketlimit) {
		LinkedHashMap<Float, LinkedHashMap<String, Double>> ret = new LinkedHashMap<>();
		int[] typeIDs = db.getAsteroids().entrySet().stream().filter(e -> typefilter.test(e.getValue()))
				.mapToInt(e -> e.getValue().id).toArray();
		db.central(marketlimit).cache(typeIDs);
		AsteroidData[] datas2 = db.getAsteroids().entrySet().stream().filter(e -> typefilter.test(e.getValue()))
				.map(e -> getAsteroidData(e.getValue(), e.getKey(), db.central(marketlimit)))
				.filter(ad -> ad.type.maxSecurity > -1).toArray(AsteroidData[]::new);
		Arrays.sort(datas2, (d1, d2) -> (int) Math.signum(d2.boPerVolume() - d1.boPerVolume()));
		Arrays.stream(datas2).map(d -> (Double) d.type.maxSecurity).distinct()
		.sorted((f1, f2) -> (int) Math.signum(f2 - f1))
		.filter(i -> i > -1).forEach(systemSec -> {
			LinkedHashMap<String, Double> values = new LinkedHashMap<>();
			ret.put(systemSec.floatValue(), values);
			for (AsteroidData data : datas2) {
				if (data.type.maxSecurity == systemSec) {
					values.put(data.name, data.boPerVolume());
				}
			}
		});
		return ret;

	}

}
