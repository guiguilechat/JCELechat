package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class ShowLPStore {

	public static class OfferAnalysis {

		public LPOffer offer;
		public double iskPerLP;
		public String offerGroup;

	}

	public static void main(String[] args) {
		YamlDatabase db = new YamlDatabase();

		// adjust BO by removing this taxe
		double markettax = 0.02;

		// only keep orders with isk/lp >= this value.
		double minReturn = 1400;
		int minLP = 500000;
		LinkedHashMap<String, ESIMarket> markets = new LinkedHashMap<>();
		markets.put("Jita", new ESIMarket(10000002));
		markets.put("Amarr", new ESIMarket(10000043));
		markets.put("Rens", new ESIMarket(10000030));
		markets.put("Dodixie", new ESIMarket(10000032));
		markets.put("Hek", new ESIMarket(10000042));

		HashSet<String> noHSCorps = new HashSet<>(Arrays.asList("Archangels", "Blood Raiders", "Dominations",
				"Frostline Laboratories", "Guardian Angels", "Guristas", "Guristas Production", "Intaki Bank",
				"Intaki Commerce", "Intaki Space Police", "Intaki Syndicate", "Mordu's Legion", "ORE Technologies",
				"Outer Ring Development", "Outer Ring Excavations", "Outer Ring Prospecting", "Salvation Angels",
				"Serpentis Corporation", "Serpentis Inquest", "The Sanctuary", "True Creations", "True Power"));

		ArrayList<LPOffer> lpos = db.getLPOffers();
		// lpos.removeIf(lp -> !lp.offer_name.contains("Mid-grade Nomad Omega"));
		System.err.println("lp offers loaded");

		for (Entry<String, ESIMarket> me : markets.entrySet()) {

			ArrayList<OfferAnalysis> offers = new ArrayList<>();
			System.out.println("");
			System.out.println(me.getKey() + " : " + me.getValue().region);
			ESIMarket market = me.getValue();
			HashSet<Integer> allBOIDs = new HashSet<>();
			HashSet<Integer> allSOIDs = new HashSet<>();
			for (LPOffer lpo : lpos) {
				if (lpo.requirements.lp > 0 && !noHSCorps.contains(lpo.corporation)) {
					allBOIDs.add(lpo.product.type_id);
					for (ItemRef e : lpo.requirements.items) {
						allSOIDs.add(e.type_id);
					}
				}
			}

			market.cacheBOs(allBOIDs.stream().mapToInt(i -> i).toArray());


			for (LPOffer lpo : lpos) {
				if (lpo.requirements.lp > 0 && !noHSCorps.contains(lpo.corporation)) {
					OfferAnalysis ana = analyse(lpo, db, market, markettax, minLP, minReturn);
					if (ana != null) {
						offers.add(ana);
					}
				}
			}
			Collections.sort(offers, (oa1, oa2) -> (int) Math.signum(oa2.iskPerLP - oa1.iskPerLP));
			groupOrders(offers);
			for (OfferAnalysis oa : offers) {
				System.out.println(oa.offer.offer_name + " ( " + oa.offer.requirements.lp + " lp ): " + oa.iskPerLP
						+ " isk/LP ; "
						+ oa.offerGroup);
			}
		}
	}

	public static OfferAnalysis analyse(LPOffer o, DataBase db, ESIMarket market, double markettax, int minLP,
			double minRatio) {
		// System.err.println(" analyse " + o.offer_name + " on market " +
		// market.region);
		OfferAnalysis ret = new OfferAnalysis();
		ret.offer = o;
		ret.offerGroup = o.corporation;
		int mult = (int) Math.ceil(1.0 * minLP / o.requirements.lp);

		double prodBO = market.getBO(o.product.type_id, o.product.quantity * mult) * (1 - markettax);
		// if the BO-cost / lp is too low, it wont get bigger when taking SO into
		// account.
		if ((prodBO - o.requirements.isk * mult) / o.requirements.lp / mult < minRatio) {
			return null;
		}
		// System.err.println(o.offer_name + " * " + o.product.quantity + " * " +
		// mult);
		// System.err.println(" prodBO=" + prodBO);
		double reqSO = o.requirements.isk * mult;
		market.cacheSOs(o.requirements.items.stream().mapToInt(ir -> ir.type_id).toArray());
		for (ItemRef rq : o.requirements.items) {
			double itemprice = market.getSO(rq.type_id, rq.quantity * mult);
			// System.err.println(" item " + rq.type_id + " costs " + itemprice + "
			// for " + rq.quantity * mult + " SO (avg "
			// + itemprice / (rq.quantity * mult) + " per item)");
			reqSO += itemprice;
		}
		// System.err.println(" investSO=" + reqSO);
		ret.iskPerLP = (prodBO - reqSO) / o.requirements.lp / mult;
		// System.err.println(" interest=" + ret.iskPerLP);
		return ret.iskPerLP >= minRatio ? ret : null;
	}

	public static void groupOrders(ArrayList<OfferAnalysis> offers) {
		OfferAnalysis previous = null;
		for (Iterator<OfferAnalysis> it = offers.iterator(); it.hasNext();) {
			OfferAnalysis oa = it.next();
			if (previous != null && previous.iskPerLP == oa.iskPerLP
					&& previous.offer.offer_name.equals(oa.offer.offer_name)) {
				it.remove();
				previous.offerGroup = previous.offerGroup + ", " + oa.offer.corporation;
			} else {
				previous = oa;
			}
		}
	}

}
