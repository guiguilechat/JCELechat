package fr.guiguilechat.eveonline.programs.gui.panes.tools.inventer;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.database.EveDatabase.InventionDecryptor;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint.Material;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint.Skill;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.model.esi.ESIMarket;
import fr.guiguilechat.eveonline.programs.gui.Settings.InventionParams;

/**
 * evaluate the gain per hour of a copy-invent-manufacture cycle for each bpo,
 * with each decryptor.
 *
 */
public class ShowInventionLowestCost {

	/**
	 * evaluation of a bpo, a target bpc, and a decryptor. Market region, skills
	 * and tax data are not stored.
	 *
	 *
	 */
	public static class InvProdData {
		public String bpoName;
		public String productName;
		public double cycleProductBO;
		public double cycleProductSO;
		public String decryptor;
		public double copyCostSO;
		public double copyCostBO;
		public long copyTime;
		public double inventionCostSO;
		public double inventionCostBO;
		public long inventionTime;
		public double inventionProbability;
		public double manufacturingCostSO;
		public double manufacturingCostBO;
		public long manufacturingTime;
		/**
		 * average number of items we product with one line of
		 * copy-invent-manufacture
		 */
		public double cycleAvgProd;
		// aggregated values
		/** average price of producing one unit, with items prices being SO */
		public double cycleCostSO;
		/** average price of producing one unit, with items prices being BO */
		public double cycleCostBO;
		/** average time to produce one unit */
		public double avgCycleTime;

		public double BOSOph;
		public double SOBOph;
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
		params.nbHours = 48;

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
			System.out
			.println(
					d.productName + "\t" + d.decryptor + "\t" + formatPrice(d.SOBOph) + "sobo/h\t" + formatPrice(d.BOSOph)
					+ "boso/h\t" + formatPrice(d.cycleCostSO) + "inso\t" + formatPrice(d.cycleCostBO) + "inbo\t"
					+ formatPrice(d.cycleProductBO) + "outbo\t" + formatPrice(d.cycleProductSO) + "outso\t"
					+ (int) (d.inventionProbability * 100) + "%invent\t" + 0.01 * (int) (d.cycleAvgProd * 100)
					+ "item/cycle\t " + formatDurationSeconds((long) d.avgCycleTime) + "/cycle");
		}
	}

	/** evaluate the cost of producing a T2 item form a T1 bpo */
	public static List<InvProdData> evalCostInventionProd(Blueprint bpo, Material selectedbpc,
			Map<String, Integer> skills, YamlDatabase db, InventionParams params, boolean onlybest) {
		ESIMarket market = db.ESIRegion(params.marketRegion);
		// if no decryptor specified, use no decryptor.

		List<InventionDecryptor> decryptors = db.decryptors().stream()
				.filter(d -> params.decryptor == null || d.name.contains(params.decryptor)).collect(Collectors.toList());
		// the bpc selected
		Blueprint bpc = db.getBlueprints().get(selectedbpc.name);
		Material product = bpc.manufacturing.products.get(0);
		if (debug) {
			System.err.println("inventing " + product.name + " from " + bpo.name);
		}
		int prodID = db.getId(product.name);
		// if we put orders of product at present SO, what money we gain per product
		// ?
		double productSO = market.getSO(prodID, 1) * (1.0 - 0.01 * params.sellTax - 0.01 * params.brokerFee);
		if (productSO == Double.POSITIVE_INFINITY) {
			return Collections.emptyList();
		}
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
		double copyCostSO = bpoEIV * 0.02 * (1.0 + 0.01 * params.copyIndex) * (1.0 + 0.01 * params.copyTax)
				+ bpo.copying.materials.parallelStream().mapToDouble(m -> market.getSO(db.getId(m.name), m.quantity)).sum();
		double copyCostBO = bpoEIV * 0.02 * (1.0 + 0.01 * params.copyIndex) * (1.0 + 0.01 * params.copyTax)
				+ bpo.copying.materials.parallelStream().mapToDouble(m -> market.getBO(db.getId(m.name), 1) * m.quantity).sum()
				* (1.0 + 0.01 * params.brokerFee);

		long copyTime = (long) (bpo.copying.time
				// science skill reduces by 5%
				* (1.0 - 0.05 * skills.getOrDefault("Science", 0))
				// advanced indus skill reduces by 3%
				* (1.0 - 0.03 * skills.getOrDefault("Advanced Industry", 0)));
		if (debug) {
			System.err.println(" copy time " + formatDurationSeconds(copyTime));
		}

		List<InvProdData> ret = decryptors.parallelStream()
				.map(decryptor -> {
					if (debug) {
						System.err.println(" " + decryptor.name);
					}
					InvProdData data = new InvProdData();
					data.bpoName = bpo.name;
					data.productName = product.name;

					data.copyCostSO = copyCostSO;
					data.copyCostBO = copyCostBO;
					data.copyTime = copyTime;

					int inventedRuns = selectedbpc.quantity + decryptor.maxrun;
					int me = 2 + decryptor.me;
					int te= 4+decryptor.te;
					int ingSkills = bpo.invention.skills.stream().map(s -> s.name).filter(s -> !s.contains("Encryption"))
							.mapToInt(n -> skills.getOrDefault(n, 0)).sum();
					int encSkill = bpo.invention.skills.stream().map(s -> s.name).filter(s -> s.contains("Encryption"))
							.mapToInt(n -> skills.getOrDefault(n, 0)).sum();

					// that is the probability to success for each run.
					data.inventionProbability = bpo.invention.products.get(0).probability
							* (1.0 + ingSkills * 1.0 / 30 + encSkill * 1.0 / 40)
							* decryptor.probmult;
					if (debug) {
						System.err.println(" bpc result : " + inventedRuns + "runs, " + me
								+ "ME, " + te + "TE, "
								+ 0.1 * (int) (data.inventionProbability * 1000) + "%invention");
					}


					// get the avg time to invent a bpc
					data.inventionTime = (long) (bpo.invention.time
							// advanced industry reduces by 3%
							* (1.0 - 0.03 * skills.getOrDefault("Advanced Industry", 0)));
					if (debug) {
						System.err.println(" invention time " + formatDurationSeconds(data.inventionTime));
					}

					// get the avg time to produce an item during a cycle.
					data.manufacturingTime = (long) (bpc.manufacturing.time * (1.0 - 0.01 * te)
							* (1.0 - 0.04 * skills.getOrDefault("Industry", 0))
							* (1.0 - 0.03 * skills.getOrDefault("Advanced Industry", 0)));
					for (Skill s : bpc.manufacturing.skills) {
						if (!s.name.contains("Industry")) {
							data.manufacturingTime *= 1.0 - 0.01 * skills.getOrDefault(s.name, 0);
						}
					}
					// if the invention failed, no reason to manufacture
					data.manufacturingTime *= data.inventionProbability;
					if (debug) {
						System.err.println(" manufacturing time " + formatDurationSeconds(data.manufacturingTime));
					}

					data.cycleAvgProd = inventedRuns * bpc.manufacturing.products.get(0).quantity * data.inventionProbability;
					data.avgCycleTime = Math.max(data.copyTime + data.inventionTime, data.manufacturingTime);

					// since we do several cycles at once, we buy more items so we need to
					// factor them in the buy orders. research and prod jobs are run in
					// parallel so we get the highst research/manufacture time
					int requiredCycles = (int) Math
							.ceil(params.nbHours * 3600 / data.avgCycleTime);
					if (debug) {
						System.err.println(" period is " + requiredCycles + " cycles, cycle time is "
								+ formatDurationSeconds((long) data.avgCycleTime));
					}

					// estimate the ceil number off items solds, then average it back to
					// the real number of items sold
					// eg if we sell 0.1 item, the ceil is 1 but the real value is 10% of
					// the BO of 1.
					long qttysold = (long) Math.ceil(data.cycleAvgProd * requiredCycles);
					data.cycleProductBO = market.getBO(prodID, qttysold) * (1.0 - 0.01 * params.sellTax) * data.cycleAvgProd
							/ qttysold;
					data.cycleProductSO = productSO * data.cycleAvgProd;
					if (debug) {
						System.err.println(
								" productso=" + productSO + " itemsPerCycle=" + data.cycleAvgProd +
								" cycleSO=" + data.cycleProductSO);
						System.err.println(
								" a cycle produces " + data.cycleAvgProd + " items, all cycles products are sold at "
										+ formatPrice(data.cycleProductBO)
										+ "isk/cycle avg BO or " + formatPrice(data.cycleProductSO) +
								"isk/cycle SO");
					}

					// if we buy all items at SO : no tax, no broker
					data.inventionCostSO = bpoEIV * 0.02 * (1.0 + 0.01 * params.inventIndex) * (1.0 + 0.01 * params.inventTax)
							// add the required materials cost
							+ bpo.invention.materials.parallelStream()
							.mapToDouble(m -> market.getSO(db.getId(m.name), m.quantity * requiredCycles)).sum() / requiredCycles
							// add the decryptor cost
							+ (decryptor.id != 0 ? market.getSO(decryptor.id, requiredCycles) : 0.0) / requiredCycles;
					if (debug) {
						System.err.println(
								" invention cost SO for all cycles averages " +
										formatPrice(data.inventionCostSO) + " isk/cycle");
					}
					// we place BO offers : broker fee.
					data.inventionCostBO =
							//Installation cost
							bpoEIV * 0.02 * (1.0 + 0.01 * params.inventIndex) * (1.0 + 0.01 * params.inventTax)
							// add the required materials cost and the decryptor cost
							+ (bpo.invention.materials.parallelStream().mapToDouble(m -> market.getBO(db.getId(m.name), 1) * m.quantity)
									.sum()
									+ (decryptor.id != 0 ? market.getBO(decryptor.id, 1) : 0.0)) * (1.0 + 0.01 * params.brokerFee);
					if (debug)
					{
						System.err.println(" invention cost BO for a cycle is " +
								formatPrice(data.inventionCostBO) + " isk/cycle");
					}

					// now we manufacture the invented bpc.
					// we have [inventionprobability] bpc each with run runs. we compute the
					// cost and production of one bpc fully run.
					double runsPerCycle = data.inventionProbability * inventedRuns;
					data.manufacturingCostSO =
							// installation cost
							bpcEIV * 0.01 * params.manufactureIndex * (1.0 + 0.01 * params.manufactureTax) * runsPerCycle
							// material cost
							+ bpc.manufacturing.materials.parallelStream().mapToDouble(m -> {
								double cycleQtty = m.quantity * runsPerCycle;
								long qttyBO = (long) Math
										.ceil((m.quantity == 1 ? 1 : 1.0 - 0.01 * me) * cycleQtty * requiredCycles);
								return market.getSO(db.getId(m.name), qttyBO) * cycleQtty / qttyBO;
							})
							.sum();
					if (debug) {
						System.err.println(" manufacturing cost SO for all cycles averages " + formatPrice(data.manufacturingCostSO)
						+ " isk/cycle");
					}

					data.manufacturingCostBO =
							// installation cost
							bpcEIV * 0.01 * params.manufactureIndex * (1.0 + 0.01 * params.manufactureTax) * runsPerCycle
							// material cost
							+ bpc.manufacturing.materials.parallelStream()
							.mapToDouble(m -> market.getBO(db.getId(m.name), 1) * (m.quantity == 1 ? 1 : 1.0 - 0.01 * me)
									* m.quantity * runsPerCycle)
							.sum() * (1.0 + 0.01 * params.brokerFee);
					if (debug) {
						System.err.println(
								" manufacturing cost BO for a cycle is " + formatPrice(data.manufacturingCostBO) + " isk/cycle");
					}

					data.cycleCostSO = data.copyCostSO + data.inventionCostSO + data.manufacturingCostSO;
					data.cycleCostBO = data.copyCostBO + data.inventionCostBO + data.manufacturingCostBO;
					data.BOSOph = (data.cycleProductSO - data.cycleCostBO) * 3600 / data.avgCycleTime;
					data.SOBOph = (data.cycleProductBO - data.cycleCostSO) * 3600 / data.avgCycleTime;
					data.decryptor = decryptor.name();
					return data;
				}).collect(Collectors.toList());
		if (onlybest) {
			double bestsobo = ret.stream().mapToDouble(ipd -> ipd.SOBOph).max().getAsDouble();
			double bestboso = ret.stream().mapToDouble(ipd -> ipd.BOSOph).max().getAsDouble();
			ret.removeIf(ipd -> ipd.SOBOph != bestsobo || ipd.BOSOph != bestboso);
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
}

