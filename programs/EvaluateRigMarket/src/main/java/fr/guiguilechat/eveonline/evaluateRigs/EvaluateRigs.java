package fr.guiguilechat.eveonline.evaluateRigs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.DataBase;
import fr.guiguilechat.eveonline.database.ESIRegion;
import fr.guiguilechat.eveonline.database.EveCentral;
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
		public double buyback;
		public double prodcost;
		public double sellValue;
		public int volume;
	}

	public static void main(String[] args) {

		String region = "PureBlind";// to black
		double buyback_efficiency = 0.9;// price buyback gives for jita prices
		int me = 10;
		double systemCostIndex = 4.0;// percentage as system cost
		double manufactureTax = 0;// percentage of system cost added as manufacture
		// index
		double sellTax = 2;// tax %
		double brokerTax = 3;// broker %

		DataBase db = new YamlDatabase();

		EveCentral forgeCentral = db.central("TheForge");
		EveCentral localCentral = db.central(region);
		ESIRegion localESI = db.ESIRegion(region);

		Module[] rigsT1 = db.getModules().values().stream().filter(m -> m.isRig() && m.techLevel() == 1)
				.toArray(Module[]::new);
		int[] rigsT1IDs = Stream.of(rigsT1).mapToInt(m -> m.id).toArray();
		localCentral.cache(rigsT1IDs);
		ArrayList<ProdData> datal = new ArrayList<>();
		for (Module m : rigsT1) {
			MetaInf mi = db.getMetaInfs().get(m.name);
			if (mi != null && mi.productOf.size() == 1) {
				String bpName = mi.productOf.get(0);
				Blueprint bp = db.getBlueprints().get(bpName);
				int nbMats = bp.manufacturing.materials.size();
				int[] matIDs = new int[nbMats];
				double[] matQtty = new double[nbMats];
				for (int i = 0; i < bp.manufacturing.materials.size(); i++) {
					Material mat = bp.manufacturing.materials.get(i);
					matIDs[i] = db.getMetaInfs().get(mat.name).id;
					matQtty[i] = mat.quantity >= 2 ? 0.01 * (100.0 - me) * mat.quantity : mat.quantity;
				}
				forgeCentral.cache(matIDs);
				double materialsBuyback = 0;
				for (int i = 0; i < bp.manufacturing.materials.size(); i++) {
					materialsBuyback += buyback_efficiency * matQtty[i] * forgeCentral.getBO(matIDs[i]);
				}

				double prodCost = db.ESIMarket().getAdjusted(m.id) * systemCostIndex / 100 * (1.0 + manufactureTax / 100);
				int totalOrders = localESI.getTotalOrders(m.id);
				double totalPrice = localESI.getTotalPrice(m.id);
				double avgHistoryPrice = totalOrders == 0 ? 0.0 : totalPrice / totalOrders;
				double centralPrice = Math.max(localCentral.getSO(m.id), localCentral.getBO(m.id));
				double sellValue = Math.min(avgHistoryPrice, centralPrice) * 0.01 * (100 - sellTax - brokerTax);

				if (sellValue > materialsBuyback) {
					double expectedProfit = totalOrders * (sellValue - materialsBuyback - prodCost);
					ProdData data = new ProdData();
					data.buyback = materialsBuyback;
					data.objectName = m.name;
					data.prodcost = prodCost;
					data.sellValue = sellValue;
					data.totalGain = expectedProfit;
					data.volume = totalOrders;
					datal.add(data);
				}
			} else {
				System.err.println("incorrect meta info for " + m.name);
				if (mi != null) {
					System.err.println("to many bps : " + mi.productOf);
				} else {
					System.err.println("can't find its id");
				}
				System.err.println("existing meta inf for " + db.getMetaInfs().keySet());
				System.exit(0);
			}
		}
		Collections.sort(datal, (p1, p2) -> (int) Math.signum(p2.totalGain - p1.totalGain));
		for (ProdData d : datal) {
			System.err.println(formatPrice(d.totalGain) + "\t" + d.objectName);
			System.err.println("\t\tmaterialbuyback=" + formatPrice(d.buyback) + " prodcost=" + formatPrice(d.prodcost)
			+ " sell=" + formatPrice(d.sellValue) + " volume=" + d.volume);
		}
	}

}
