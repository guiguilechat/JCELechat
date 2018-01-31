package fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint.Material;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint.Skill;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.model.esi.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets.RegionalMarket;
import fr.guiguilechat.eveonline.programs.manager.Settings.InventionParams;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer.InventerToolPane.StructBonus;

/**
 * evaluate the gain per hour of a copy-invent-manufacture cycle for each bpo,
 * with each decryptor.
 *
 */
public class InventionGainAlgorithm {

	/**
	 * evaluation of a bpo, a target bpc, and a decryptor. Market region, skills
	 * and tax data are not stored.
	 *
	 *
	 */
	public static class InventionProdData {
		public String bpoName;
		public String productName;
		public String decryptor;
		public double copyCostSO;
		public long copyTime;
		public double inventionCostSO;
		public long inventionTime;
		public int inventedRuns;
		public double inventionProbability;
		public double manufacturingCostSO;
		public long manufacturingTime;
		public double cycleProductBO;
		/**
		 * average number of items we product with one line of
		 * copy-invent-manufacture
		 */
		public double cycleAvgProd;
		// aggregated values
		/** average price of producing one unit, with items prices being SO */
		public double cycleCostSO;
		/** average time to produce one unit */
		public long cycleTime;

		public double SOBOph;
		public double cycleMargin;
		public double itemCost;

		public int maxCycles;
	}

	/** evaluate the cost of producing a T2 item form a T1 bpo */
	public static List<InventionProdData> evalCostInventionProd(Blueprint bpo, Material selectedbpc,
			Map<String, Integer> skills, YamlDatabase db, InventionParams params) {
		// the bpc selected
		Blueprint bpc = db.getBlueprints().get(selectedbpc.name);
		if (!haveReqSkills(skills, bpc.manufacturing.skills)) {
			return Collections.emptyList();
		}
		RegionalMarket market = ESIConnection.DISCONNECTED.markets
				.getMarket(db.getLocation(params.marketRegion).locationID);
		Material product = bpc.manufacturing.products.get(0);
		int prodID = db.getId(product.name);

		StructBonus copyStruct = InventerToolPane.StructBonus.valueOf(params.copystruct);
		StructBonus inventStruct = InventerToolPane.StructBonus.valueOf(params.inventstruct);
		StructBonus manufStruct = InventerToolPane.StructBonus.valueOf(params.manufstruct);

		// the Estimated Item Value of the bpo. used for copying and manufacturing
		double bpoEIV = bpo.manufacturing == null || bpo.manufacturing.materials == null ? 0
				: bpo.manufacturing.materials.parallelStream()
				.mapToDouble(mat -> mat.quantity * ESIConnection.DISCONNECTED.markets.getAdjusted(db.getId(mat.name)))
				.sum();
		// Estimated Item Value of the bpc. when manufacturing, used as a base for
		// tax.
		double bpcEIV = bpc.manufacturing == null || bpc.manufacturing.materials == null ? 0
				: bpc.manufacturing.materials.parallelStream()
				.mapToDouble(mat -> mat.quantity * ESIConnection.DISCONNECTED.markets.getAdjusted(db.getId(mat.name)))
				.sum();

		// first we need to copy the bpo into a bpc.

		// copycost=eiv*nb_runs*runspercopy*0.02*(100+copyindex)/100*(100+stationtax)/100
		// since we run for ONE invention job, we copy on bpc with one run.
		double copyInstalation = bpoEIV * 0.02 * (1.0 + 0.01 * params.copyIndex) * (1.0 + 0.01 * params.copyTax)
				* (1.0 - 0.01 * copyStruct.cost);
		double copyME = 1.0 - 0.01 * copyStruct.me;

		long copyTime = (long) (bpo.copying.time
				// struct bonus
				* (1.0 - 0.01 * copyStruct.te)
				// science skill reduces by 5%
				* (1.0 - 0.05 * skills.getOrDefault("Science", 0))
				// advanced indus skill reduces by 3%
				* (1.0 - 0.03 * skills.getOrDefault("Advanced Industry", 0)));

		List<InventionProdData> ret = db.decryptors().parallelStream().map(decryptor -> {
			InventionProdData data = new InventionProdData();
			data.bpoName = bpo.name;
			data.productName = product.name;
			data.copyTime = copyTime;

			data.decryptor = decryptor.name();
			data.inventedRuns = selectedbpc.quantity + decryptor.maxrun;
			int inventedME = 2 + decryptor.me;
			int inventedTE = 4 + decryptor.te;
			int engSkills = bpo.invention.skills.stream().map(s -> s.name).filter(s -> !s.contains("Encryption"))
					.mapToInt(n -> skills.getOrDefault(n, 0)).sum();
			int encSkill = bpo.invention.skills.stream().map(s -> s.name).filter(s -> s.contains("Encryption"))
					.mapToInt(n -> skills.getOrDefault(n, 0)).sum();

			// that is the probability to success for each run.
			data.inventionProbability = bpo.invention.products.get(0).probability
					* (1.0 + engSkills * 1.0 / 30 + encSkill * 1.0 / 40) * decryptor.probmult;

			// get the avg time to invent a bpc
			data.inventionTime = (long) (bpo.invention.time
					// struct bonus
					* (1.0 - 0.01 * inventStruct.te)
					// advanced industry reduces by 3%
					* (1.0 - 0.03 * skills.getOrDefault("Advanced Industry", 0)));

			// get the avg time to produce the items during a cycle.
			data.manufacturingTime = (long) (data.inventionProbability * data.inventedRuns * bpc.manufacturing.time
					* (1.0 - 0.01 * inventedTE)
					// struct bonus
					* (1.0 - 0.01 * manufStruct.te) * (1.0 - 0.04 * skills.getOrDefault("Industry", 0))
					* (1.0 - 0.03 * skills.getOrDefault("Advanced Industry", 0)));
			for (Skill s : bpc.manufacturing.skills) {
				if (!s.name.contains("Industry")) {
					data.manufacturingTime *= 1.0 - 0.01 * skills.getOrDefault(s.name, 0);
				}
			}

			data.cycleAvgProd = data.inventedRuns * bpc.manufacturing.products.get(0).quantity * data.inventionProbability;
			data.cycleTime = data.copyTime + data.inventionTime + data.manufacturingTime;

			for (int nbCycles = 1; nbCycles <= 1000; nbCycles++) {
				// otherwise compiler complains "must be final or effectively final"
				int nbCyclesFinal = nbCycles;

				double copyCostSO = copyInstalation
						+ bpo.copying.materials.parallelStream().mapToDouble(m -> market.getSO(db.getId(m.name),
								nbCyclesFinal * (m.quantity == 1 ? 1 : (int) Math.ceil(m.quantity * copyME)))).sum() / nbCycles;

				// estimate the ceil number off items solds, then average it back to
				// the real number of items sold
				// eg if we sell 0.1 item, the ceil is 1 but the real value is 10% of
				// the BO of 1.
				int qttysold = (int) Math.ceil(data.cycleAvgProd);
				double cycleProductBO = market.getBO(prodID, qttysold * nbCycles) / nbCycles * (1.0 - 0.01 * params.sellTax)
						// qttysold is cycleavgprod rounded up. scale back
						* data.cycleAvgProd / qttysold;

				double inventionInstall = bpoEIV * 0.02
						// struct bonus
						* (1.0 - 0.01 * inventStruct.cost)
						// taxes
						* (1.0 + 0.01 * params.inventIndex) * (1.0 + 0.01 * params.inventTax);
				// if we buy all items at SO : no tax, no broker
				double inventionCostSO = inventionInstall
						// add the required materials cost
						+ bpo.invention.materials.parallelStream()
						.mapToDouble(m -> market.getSO(db.getId(m.name), nbCyclesFinal * m.quantity)).sum() / nbCycles
						// add the decryptor cost
						+ (decryptor.id != 0 ? market.getSO(decryptor.id, nbCycles) : 0.0) / nbCycles;

				// now we manufacture the invented bpc.
				// we compute the production cost of one invented bpc.
				double manufInstall = bpcEIV
						// struct bonus
						* (1.0 - 0.01 * manufStruct.cost) * 0.01 * params.manufactureIndex * (1.0 + 0.01 * params.manufactureTax)
						* data.inventedRuns;
				double manufacturingCostSO = manufInstall
						// material cost
						+ bpc.manufacturing.materials.parallelStream().mapToDouble(m -> {
							double bpcQtty = m.quantity * data.inventedRuns * nbCyclesFinal;
							int qttyBO = (int) Math.ceil((m.quantity == 1 ? 1 : 1.0 - 0.01 * inventedME) * bpcQtty);
							return market.getSO(db.getId(m.name), qttyBO) * bpcQtty / qttyBO / nbCyclesFinal;
						}).sum();

				double cycleCostSO = copyCostSO + inventionCostSO + manufacturingCostSO * data.inventionProbability;
				double SOBOph = (cycleProductBO - cycleCostSO) * 3600 / Math.max(params.minActionHours * 3600,
						Math.max(data.copyTime + data.inventionTime, data.manufacturingTime));
				double cycleMargin = (cycleProductBO - cycleCostSO) / cycleProductBO;
				if (nbCycles == 1) {
					data.copyCostSO = copyCostSO;
					data.cycleProductBO = cycleProductBO;
					data.inventionCostSO = inventionCostSO;
					data.manufacturingCostSO = manufacturingCostSO;
					data.cycleCostSO = cycleCostSO;
					data.SOBOph = SOBOph;
					data.cycleMargin = cycleMargin;
					data.maxCycles = nbCycles;
					data.itemCost = data.cycleCostSO / data.cycleAvgProd;
				} else {
					if (SOBOph > data.SOBOph * (100.0 - params.maxCycleReduction) / 100) {
						data.maxCycles = nbCycles;
					} else {
						break;
					}
				}
			}
			return data;
		}).collect(Collectors.toList());
		ToDoubleFunction<InventionProdData> gainFunction = null;
		switch (params.target) {
		case MARGIN:
			gainFunction = ipd -> ipd.cycleMargin;
			break;
		case ITEMCOST:
			gainFunction = ipd -> -ipd.itemCost;
			break;
		case SOBO:
			gainFunction = ipd -> ipd.SOBOph;
			break;
		case NONE:
			break;
		}
		if (gainFunction != null) {
			ToDoubleFunction<InventionProdData> f = gainFunction;
			double bestGain = ret.stream().mapToDouble(gainFunction).max().getAsDouble();
			ret.removeIf(ipd -> f.applyAsDouble(ipd) != bestGain);
		}
		return ret;
	}

	static final long[] unitSuffixValue = { 1000000000000l, 1000000000l, 1000000l, 1000l };
	static final String[] unitSuffix = { "T", "B", "M", "k" };

	public static String formatPrice(double value) {
		if (value == Double.MAX_VALUE || value == Double.MIN_VALUE || value == Double.POSITIVE_INFINITY
				|| value == Double.NEGATIVE_INFINITY) {
			return "" + value;
		}
		double prefix = value;
		String suffix = null;
		for (int i = 0; i < unitSuffix.length && suffix == null; i++) {
			if (value >= unitSuffixValue[i]) {
				prefix = value / unitSuffixValue[i];
				suffix = unitSuffix[i];
			}
		}
		if (suffix == null) {
			suffix = "";
		}
		String rets = "" + prefix;
		return (rets.length() > 4 ? rets.substring(0, 4) : rets) + suffix;
	}

	public static String formatDurationSeconds(long seconds) {
		if (seconds == 0) {
			return "0s";
		}
		LinkedList<String> strings = new LinkedList<>();
		if (seconds % 60 != 0) {
			strings.add(0, "" + seconds % 60 + "s");
		}
		seconds /= 60;
		if (seconds > 0) {
			if (seconds % 60 != 0) {
				strings.add(0, "" + seconds % 60 + "m");
			}
			seconds /= 60;
			if (seconds > 0) {
				if (seconds % 24 != 0) {
					strings.add(0, "" + seconds % 24 + "h");
				}
				seconds /= 24;
				if (seconds > 0) {
					strings.add(0, "" + seconds + "D");
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String s : strings) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * check if a list of required skills are available to a list of existing
	 * skills
	 */
	public static boolean haveReqSkills(Map<String, Integer> myskills, ArrayList<Skill> reqSkills) {
		for (Skill s : reqSkills) {
			if (myskills.getOrDefault(s.name, 0) < s.level) {
				return false;
			}
		}
		return true;
	}
}
