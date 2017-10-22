package fr.guiguilechat.eveonline.model.database;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.model.database.EveCentral;
import fr.guiguilechat.eveonline.model.database.EveDatabase;
import fr.guiguilechat.eveonline.model.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;

public class ShowMinePrice {

	public static class OreTradeOrder {
		double bo;
		double so;
		String name;
		/** volume of raw mineral we need to have one sellable item */
		double extractionVolume;
		public double boPerVolume() {
			return bo / extractionVolume;
		}

		/**
		 * max security of the uncompressed ore. this only has meaning for
		 * compressed ore, otherwise should be equal to type.maxSecurity
		 */
		double maxRawSecurity;
	}

	public static final DecimalFormat secFormat = new DecimalFormat("#.#");

	public static void main(String[] args) {
		EveDatabase db = new YamlDatabase();
		LinkedHashMap<String, Integer> hubs = new LinkedHashMap<>();
		hubs.put("Jita", EveCentral.JITA_SYSTEM);
		hubs.put("Amarr", EveCentral.AMARR_SYSTEM);
		hubs.put("Rens", EveCentral.RENS_SYSTEM);
		hubs.put("Dodixie", EveCentral.DODIXIE_SYSTEM);
		hubs.put("Hek", EveCentral.HEK_SYSTEM);

		LinkedHashMap<String, Predicate<Asteroid>> typefilters = new LinkedHashMap<>();
		// group 465=Ice
		typefilters.put("ore", a -> !a.groupName.equals("Ice"));
		typefilters.put("ice", a -> a.groupName.equals("Ice"));

		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);

		new Yaml(options).dump(makePrices(db, typefilters, hubs), new PrintWriter(System.out));

	}

	public static OreTradeOrder getAsteroidData(Asteroid astero, String name, EveCentral market) {
		OreTradeOrder order = new OreTradeOrder();
		order.name = name;
		order.bo = market.getBO(astero.id);
		order.so = market.getSO(astero.id);
		order.extractionVolume = astero.volume;
		order.maxRawSecurity = astero.maxSecurity;
		return order;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> makePrices(
			EveDatabase db, Map<String, Predicate<Asteroid>> typefilters, Map<String, Integer> marketLimits) {
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> ret = new LinkedHashMap<>();
		for (Entry<String, Integer> e : marketLimits.entrySet()) {
			ret.put(e.getKey(), makePrices(db, typefilters, e.getValue()));
		}
		return ret;
	}

	public static LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>> makePrices(EveDatabase db,
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
	public static LinkedHashMap<Float, LinkedHashMap<String, Double>> makePrices(EveDatabase db,
			Predicate<Asteroid> typefilter,
			int marketlimit) {
		LinkedHashMap<Float, LinkedHashMap<String, Double>> ret = new LinkedHashMap<>();
		int[] typeIDs = db.getAsteroids().entrySet().stream().filter(e -> typefilter.test(e.getValue()))
				.mapToInt(e -> e.getValue().id).toArray();
		db.central(marketlimit).cache(typeIDs);
		List<OreTradeOrder> asteroiDataList = new ArrayList<>();
		for (Entry<String, Asteroid> e : db.getAsteroids().entrySet()) {
			Asteroid a = e.getValue();
			if (typefilter.test(a) && a.maxSecurity > -1) {
				asteroiDataList.add(getAsteroidData(a, e.getKey(), db.central(marketlimit)));
				if (a.compressedTo != null) {
					Asteroid asteroCompressed = db.getAsteroids().get(a.compressedTo);
					OreTradeOrder orderCompressed = getAsteroidData(asteroCompressed, a.compressedTo, db.central(marketlimit));
					// replace compressed volume by uncompressed volume
					orderCompressed.extractionVolume = asteroCompressed.compressRatio * a.volume;
					orderCompressed.maxRawSecurity = a.maxSecurity;
					asteroiDataList.add(orderCompressed);
				}
			}
		}
		Collections.sort(asteroiDataList, (d1, d2) -> (int) Math.signum(d2.boPerVolume() - d1.boPerVolume()));
		// for each maxsecurity
		asteroiDataList.stream().map(d -> (Double) d.maxRawSecurity).distinct()
		.sorted((f1, f2) -> (int) Math.signum(f2 - f1)).forEach(systemSec -> {
			LinkedHashMap<String, Double> values = new LinkedHashMap<>();
			ret.put(systemSec.floatValue(), values);
			for (OreTradeOrder data : asteroiDataList) {
				if (data.maxRawSecurity == systemSec) {
					values.put(data.name, data.boPerVolume());
				}
			}
		});
		return ret;

	}

}
