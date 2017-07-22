package fr.guiguilechat.eveonline.evaluateRigs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import fr.guiguilechat.eveonline.database.EveCentral;
import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.esi.ESIMarket.ItemMedianData;
import fr.guiguilechat.eveonline.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.database.yaml.Blueprint.Material;
import fr.guiguilechat.eveonline.database.yaml.MetaInf;
import fr.guiguilechat.eveonline.database.yaml.Module;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class EvaluateRigs {

	private static final DecimalFormat priceFormatter = new DecimalFormat("#0.0");

	/**
	 *
	 * @param value
	 * @return
	 */
	private static String formatPrice(double value) {
		if (value >= 1e12) {
			return priceFormatter.format(value / 1e12) + "T";
		} else if (value >= 1e9) {
			return priceFormatter.format(value / 1e9) + "G";
		} else if (value >= 1e6) {
			return priceFormatter.format(value / 1e6) + "M";
		} else if (value >= 1e3) {
			return priceFormatter.format(value / 1e3) + "k";
		} else {
			return priceFormatter.format(value);
		}
	}

	public static class ProdData {
		public String objectName;
		public double totalGain;
		public double craftimport;
		public double prodTax;
		public double sellValue;
		public long volume;
	}

	public static void main(String[] args) {
		String sellRegion = "TheForge";// to black
		String importRegion = "TheForge";
		double courrierColTax = 1.02;// tax multiplier to jita SO for importing
		double courrierVolPrice = 390;// price to import an item of volume 1
		int bpME = 10;// BP ME value.
		double strucME = 1;// percent of me engineering bonus
		double structRigME = 4.2;// percent of ME added through rigs
		double multME = 1.0 * (100 - bpME) / 100 * (100 - strucME) / 100 * (100 - structRigME) / 100;

		double systemCostIndex = 4.0;// craft system index
		double manufactureTax = 0;// craft compmlex tax
		double sellStationTax = 2.5;// tax in sell station%
		double brokerTax = 2;// broker %
		double taxMult = 0.01 * (100 + sellStationTax + brokerTax);

		// statistical analysis parameter
		int nbPeriods = 24, periodLength = 7;

		EveDatabase db = new YamlDatabase();

		for (String param : args) {
			if (param.startsWith("region=")) {
				sellRegion = param.substring("region=".length());
			}
			if (param.startsWith("import=")) {
				importRegion = param.substring("import=".length());
			}
			if (param.startsWith("totalME=")) {
				multME = Double.parseDouble(param.substring("totalME=".length()));
			}
			if (param.startsWith("prodidx=")) {
				systemCostIndex = Double.parseDouble(param.substring("prodidx=".length()));
			}
			if (param.startsWith("courriervol=")) {
				courrierVolPrice = Double.parseDouble(param.substring("courriervol=".length()));
			}
			if (param.startsWith("courriercol=")) {
				courrierColTax = Double.parseDouble(param.substring("courriercol=".length()));
			}
		}

		EveCentral importCentral = db.central(importRegion);
		ESIMarket localESI = db.ESIRegion(sellRegion);

		Module[] rigsT1 = db.getModules().values().stream().filter(m -> m.isRig() && m.metaLvl < 5)
				.toArray(Module[]::new);
		ArrayList<ProdData> datal = new ArrayList<>();
		for (Module m : rigsT1) {
			ProdData pd = makeCraftData(m, db, importCentral, localESI, taxMult, multME, systemCostIndex, manufactureTax,
					courrierColTax, courrierVolPrice, nbPeriods, periodLength);
			if (pd != null && pd.totalGain > 0) {
				datal.add(pd);
			}
		}
		Collections.sort(datal, (p1, p2) -> (int) Math.signum(p2.totalGain - p1.totalGain));
		for (ProdData d : datal) {
			System.out.println(formatPrice(d.totalGain) + "\t" + d.objectName+"\tcraftimport=" + formatPrice(d.craftimport) + "\tprodcost=" + formatPrice(d.prodTax)
			+ "\tsell=" + formatPrice(d.sellValue) + "\tvolume=" + d.volume);
		}
	}

	/**
	 *
	 * @param m
	 *          the module we want to build
	 * @param db
	 *          the database to get item cached in
	 * @param importCentral
	 *          eve central to get prices from which to import items by jf
	 * @param localESI
	 *          esi data to make statistical sell data from
	 * @param taxMult
	 *          the effect of taxes on the SO prices (1-taxes/100)
	 * @param multME
	 *          the actual ME effect (so 1-ME/100). should consider the
	 *          rigs/structure bonuses as well
	 * @param systemCostIndex
	 *          the cost index of the system where we craft the items
	 * @param manufactureTax
	 *          manufacture tax of the structure where we craft
	 * @param importTax
	 *          tax of the jf, related to multiplier to jita SO. should be
	 *          (1+taxPct/100)
	 * @param importVolPrice
	 *          price of the jf for each m3 we need
	 * @param nbPeriods
	 *          periods to consider for analysis of item sell price/quantity
	 * @param periodLength
	 *          number of days to consider for analysis of item sell
	 *          price/quantity
	 * @return
	 */
	public static ProdData makeCraftData(Module m, EveDatabase db, EveCentral importCentral,
			ESIMarket localESI, double taxMult, double multME, double systemCostIndex, double manufactureTax,
			double importTax,
			double importVolPrice, int nbPeriods, int periodLength) {
		MetaInf mi = db.getMetaInfs().get(m.name);
		if (mi == null || mi.productOf.size() == 0) {
			System.err.println("incorrect meta info for " + m.name);
			System.err.println("existing meta inf for " + db.getMetaInfs().keySet());
			if (mi != null) {
				System.err.println("too many bps : " + mi.productOf);
			} else {
				System.err.println("can't find its id");
			}
			System.exit(0);
			return null;
		}

		// evaluate the cost of importing the material and crafting the rig
		String bpName = mi.productOf.get(0);
		System.err.println("evaluate bp " + bpName);
		Blueprint bp = db.getBlueprints().get(bpName);
		int nbMats = bp.manufacturing.materials.size();
		int[] matIDs = new int[nbMats];
		double[] matVol = new double[nbMats];
		double[] matQtty = new double[nbMats];
		for (int i = 0; i < bp.manufacturing.materials.size(); i++) {
			Material mat = bp.manufacturing.materials.get(i);
			MetaInf mi2 = db.getMetaInfs().get(mat.name);
			matIDs[i] = mi2.id;
			matQtty[i] = mat.quantity >= 2 ? multME * mat.quantity : mat.quantity;
			matVol[i] = mi2.volume;
		}
		importCentral.cache(matIDs);
		double materialsImportCost = 0;
		for (int i = 0; i < bp.manufacturing.materials.size(); i++) {
			materialsImportCost += importTax * matQtty[i] * importCentral.getBO(matIDs[i])
					+ matVol[i] * matQtty[i] * importVolPrice;
		}
		double prodTax = db.ESIBasePrices().getAdjusted(m.id) * systemCostIndex / 100 * (1.0 + manufactureTax / 100);

		ItemMedianData pond = localESI.getPonderatedMedianMonthlyAverage(m.id, nbPeriods, periodLength);
		long totalOrders = pond.qtty / periodLength;
		double avgHistoryPrice = pond.average;
		// double centralPrice = Math.max(localCentral.getSO(m.id),
		// localCentral.getBO(m.id));
		// price to import from jita
		double importValue = importTax * importCentral.getSO(mi.id) + mi.volume * importVolPrice;
		double sellValue = Math.min(avgHistoryPrice, importValue) * taxMult;

		if (sellValue > materialsImportCost) {
			double expectedProfit = totalOrders * (sellValue - materialsImportCost - prodTax);
			System.err.println("  expecting " + expectedProfit + " profit");
			ProdData data = new ProdData();
			data.craftimport = materialsImportCost;
			data.objectName = m.name;
			data.prodTax = prodTax;
			data.sellValue = sellValue;
			data.totalGain = expectedProfit;
			data.volume = totalOrders;
			return data;
		}
		return null;

	}

}
