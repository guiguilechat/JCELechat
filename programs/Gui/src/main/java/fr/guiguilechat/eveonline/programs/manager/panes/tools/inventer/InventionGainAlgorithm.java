package fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint.Material;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint.Skill;
import fr.guiguilechat.eveonline.model.esi.raw.market.Markets;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.manager.Settings.InventionParams;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer.InventerPane.StructBonus;

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
	public static class InvProdData {
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
		public double cycleTime;

		public double SOBOph;
		public double cycleMargin;
	}

	protected static boolean debug = false;

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 50;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		YamlDatabase db = new YamlDatabase();
		LinkedHashMap<String, Blueprint> bps = db.getBlueprints();
		InventionParams params = new InventionParams();
		params.marketRegion = "TheForge";
		params.copyIndex = 2;
		params.copyTax = 10;
		params.inventIndex = 2;
		params.inventTax = 10;
		params.manufactureIndex = 2;
		params.manufactureTax = 10;
		params.sellTax = 1;
		params.brokerFee = 2;

		Map<String, Integer> skills = new HashMap<>();
		// skills.put("Molecular Engineering", 3);
		// skills.put("Nanite Engineering", 4);
		// skills.put("Minmatar Encryption Methods", 3);
		// skills.put("Advanced Industry", 4);
		// skills.put("Amarr Starship Engineering", 3);
		// skills.put("High Energy Physics", 3);
		// skills.put("Amarr Encryption Methods", 4);

		if (debug) {
			System.err.println("iterating, skills are" + skills);
		}

		List<InvProdData> datas = bps.values().stream()
				// comment for sequential calls (easier debuging)
				.parallel()
				// only keep bpo that are found in the itemid table and with invention
				.filter(bp -> bp != null && bp.invention != null && !bp.invention.products.isEmpty() && bp.copying != null)
				// .filter(bp -> bp.name.contains("Apocalypse"))
				// then analyze the bpos that have invention
				.flatMap(bpo -> bpo.invention.products.stream()
						.flatMap(mat -> evalCostInventionProd(bpo, mat, skills, db, params, !debug).stream()))
				.collect(Collectors.toList());
		datas.sort((ipd1, ipd2) -> (int) Math.signum(ipd1.SOBOph - ipd2.SOBOph));
		for (InvProdData d : datas) {
			System.out.println(d.productName + "\t" + d.decryptor + "\t" + formatPrice(d.SOBOph) + "sobo/h\t"
					+ formatPrice(d.cycleCostSO) + "inso\t" + formatPrice(d.cycleProductBO) + "outbo\t"
					+ (int) (d.inventionProbability * 100) + "%invent\t" + 0.01 * (int) (d.cycleAvgProd * 100) + "item/cycle\t "
					+ formatDurationSeconds((long) d.cycleTime) + "/cycle");
		}
	}

	/** evaluate the cost of producing a T2 item form a T1 bpo */
	public static List<InvProdData> evalCostInventionProd(Blueprint bpo, Material selectedbpc,
			Map<String, Integer> skills, YamlDatabase db, InventionParams params, boolean onlybest) {
		// the bpc selected
		Blueprint bpc = db.getBlueprints().get(selectedbpc.name);
		if (!haveReqSkills(skills, bpc.manufacturing.skills)) {
			return Collections.emptyList();
		}
		Markets market = db.ESIRegion(params.marketRegion);
		Material product = bpc.manufacturing.products.get(0);
		if (debug) {
			System.err.println("inventing " + product.name + " from " + bpo.name);
		}
		int prodID = db.getId(product.name);

		StructBonus copyStruct = InventerPane.StructBonus.valueOf(params.copystruct);
		StructBonus inventStruct = InventerPane.StructBonus.valueOf(params.inventstruct);
		StructBonus manufStruct = InventerPane.StructBonus.valueOf(params.manufstruct);

		// the Estimated Item Value of the bpo. used for copying and manufacturing
		double bpoEIV = bpo.manufacturing == null || bpo.manufacturing.materials == null ? 0
				: bpo.manufacturing.materials.parallelStream()
				.mapToDouble(mat -> mat.quantity * db.ESIBasePrices().getAdjusted(db.getId(mat.name))).sum();
		// Estimated Item Value of the bpc. when manufacturing, used as a base for
		// tax.
		double bpcEIV = bpc.manufacturing == null || bpc.manufacturing.materials == null ? 0
				: bpc.manufacturing.materials.parallelStream()
				.mapToDouble(mat -> mat.quantity * db.ESIBasePrices().getAdjusted(db.getId(mat.name))).sum();

		// first we need to copy the bpo into a bpc.

		// copycost=eiv*nb_runs*runspercopy*0.02*(100+copyindex)/100*(100+stationtax)/100
		// since we run for ONE invention job, we copy on bpc with one run.
		double copyInstalation = bpoEIV * 0.02 * (1.0 + 0.01 * params.copyIndex) * (1.0 + 0.01 * params.copyTax)
				* (1.0 - 0.01 * copyStruct.cost);
		double copyME = 1.0 - 0.01 * copyStruct.me;
		double copyCostSO = copyInstalation + bpo.copying.materials.parallelStream()
		.mapToDouble(m -> market.getSO(db.getId(m.name),
				m.quantity == 1 ? 1 : (int) Math.ceil(m.quantity * copyME)))
		.sum();

		long copyTime = (long) (bpo.copying.time
				// struct bonus
				* (1.0 - 0.01 * copyStruct.te)
				// science skill reduces by 5%
				* (1.0 - 0.05 * skills.getOrDefault("Science", 0))
				// advanced indus skill reduces by 3%
				* (1.0 - 0.03 * skills.getOrDefault("Advanced Industry", 0)));

		List<InvProdData> ret = db.decryptors().parallelStream().map(decryptor -> {
			InvProdData data = new InvProdData();
			data.bpoName = bpo.name;
			data.productName = product.name;
			data.copyCostSO = copyCostSO;
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

			// get the avg time to produce an item during a cycle.
			data.manufacturingTime = (long) (bpc.manufacturing.time * (1.0 - 0.01 * inventedTE)
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


			// estimate the ceil number off items solds, then average it back to
			// the real number of items sold
			// eg if we sell 0.1 item, the ceil is 1 but the real value is 10% of
			// the BO of 1.
			long qttysold = (long) Math.ceil(data.cycleAvgProd);
			data.cycleProductBO = market.getBO(prodID, qttysold) * (1.0 - 0.01 * params.sellTax)
					// qttysold is cycleavgprod rounded up. scale back
					* data.cycleAvgProd / qttysold;

			double inventionInstall = bpoEIV * 0.02
					// struct bonus
					* (1.0 - 0.01 * inventStruct.cost)
					// taxes
					* (1.0 + 0.01 * params.inventIndex) * (1.0 + 0.01 * params.inventTax);
			// if we buy all items at SO : no tax, no broker
			data.inventionCostSO = inventionInstall
					// add the required materials cost
					+ bpo.invention.materials.parallelStream()
					.mapToDouble(m -> market.getSO(db.getId(m.name), m.quantity)).sum()
					// add the decryptor cost
					+ (decryptor.id != 0 ? market.getSO(decryptor.id, 1) : 0.0);

			// now we manufacture the invented bpc.
			// we compute the production cost of one invented bpc.
			double manufInstall = bpcEIV
					// struct bonus
					* (1.0 - 0.01 * manufStruct.cost) * 0.01 * params.manufactureIndex * (1.0 + 0.01 * params.manufactureTax)
					* data.inventedRuns;
			data.manufacturingCostSO = manufInstall
					// material cost
					+ bpc.manufacturing.materials.parallelStream().mapToDouble(m -> {
						double bpcQtty = m.quantity * data.inventedRuns;
						long qttyBO = (long) Math
								.ceil((m.quantity == 1 ? 1 : 1.0 - 0.01 * inventedME) * bpcQtty);
						return market.getSO(db.getId(m.name), qttyBO) * bpcQtty / qttyBO;
					}).sum();

			data.cycleCostSO = data.copyCostSO + data.inventionCostSO + data.manufacturingCostSO * data.inventionProbability;
			data.SOBOph = (data.cycleProductBO - data.cycleCostSO) * 3600 / data.cycleTime;
			data.cycleMargin = (data.cycleProductBO - data.cycleCostSO) / data.cycleProductBO;
			return data;
		}).collect(Collectors.toList());
		if (onlybest) {
			double bestsobo = ret.stream().mapToDouble(ipd -> ipd.SOBOph).max().getAsDouble();
			double bestMargin = ret.stream().mapToDouble(ipd -> ipd.cycleMargin).max().getAsDouble();
			ret.removeIf(ipd -> ipd.SOBOph != bestsobo && ipd.cycleMargin != bestMargin);
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
				System.err.println("missing " + s.name + " " + s.level);
				return false;
			}
		}
		return true;
	}
}
