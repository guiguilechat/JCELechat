package fr.guiguilechat.eveonline.model.database;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.model.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.connect.modeled.Markets.RegionalMarket;

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

		LinkedHashMap<String, Predicate<Asteroid>> typefilters = new LinkedHashMap<>();
		// group 465=Ice
		typefilters.put("ore", a -> !a.groupName.equals("Ice"));
		typefilters.put("ice", a -> a.groupName.equals("Ice"));

		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);

		new Yaml(options).dump(makePrices(db, typefilters, new LinkedHashSet<>(Arrays.asList("TheForge", "Amarr"))),
				new PrintWriter(System.out));

	}

	public static OreTradeOrder getAsteroidData(Asteroid astero, String name, RegionalMarket market) {
		OreTradeOrder order = new OreTradeOrder();
		order.name = name;
		order.bo = market.getBO(astero.id, 1);
		order.so = market.getSO(astero.id, 1);
		order.extractionVolume = astero.volume;
		order.maxRawSecurity = astero.maxSecurity;
		return order;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> makePrices(
			EveDatabase db, Map<String, Predicate<Asteroid>> typefilters, LinkedHashSet<String> marketregions) {
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>>> ret = new LinkedHashMap<>();
		for (String region : marketregions) {
			ret.put(region, makePrices(db, typefilters, region));
		}
		return ret;
	}

	public static LinkedHashMap<String, LinkedHashMap<Float, LinkedHashMap<String, Double>>> makePrices(EveDatabase db,
			Map<String, Predicate<Asteroid>> typefilters, String marketlimit) {
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
	 * @param marketRegion
	 * @return
	 */
	public static LinkedHashMap<Float, LinkedHashMap<String, Double>> makePrices(EveDatabase db,
			Predicate<Asteroid> typefilter,
			String marketRegion) {
		RegionalMarket m = ESIConnection.DISCONNECTED.markets.getMarket(db.getLocation(marketRegion).locationID);
		LinkedHashMap<Float, LinkedHashMap<String, Double>> ret = new LinkedHashMap<>();
		List<OreTradeOrder> asteroiDataList = new ArrayList<>();
		for (Entry<String, Asteroid> e : db.getAsteroids().entrySet()) {
			Asteroid a = e.getValue();
			if (typefilter.test(a) && a.maxSecurity > -1) {
				asteroiDataList.add(getAsteroidData(a, e.getKey(), m));
				if (a.compressedTo != null) {
					Asteroid asteroCompressed = db.getAsteroids().get(a.compressedTo);
					OreTradeOrder orderCompressed = getAsteroidData(asteroCompressed, a.compressedTo, m);
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
