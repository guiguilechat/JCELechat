package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class ShowLPStore {

	public static class OfferAnalysis {

		public LPOffer offer;
		public double iskPerLP;

	}

	public static void main(String[] args) {
		YamlDatabase db = new YamlDatabase();
		double markettax = 0.02;
		int minLP = 100000;
		// EveCentral central = new EveCentral(EveCentral.JITA_SYSTEM,
		// EveCentral.DODIXIE_SYSTEM, EveCentral.HEK_SYSTEM,
		// EveCentral.RENS_SYSTEM, EveCentral.AMARR_SYSTEM);
		ESIMarket market = new ESIMarket(10000002);
		ArrayList<OfferAnalysis> offers = new ArrayList<>();
		HashSet<String> noHSCorps = new HashSet<>(Arrays.asList("Archangels", "Blood Raiders", "Dominations",
				"Frostline Laboratories", "Guardian Angels", "Guristas", "Guristas Production", "Intaki Bank",
				"Intaki Commerce", "Intaki Space Police", "Intaki Syndicate", "Mordu's Legion", "ORE Technologies",
				"Outer Ring Development", "Outer Ring Excavations", "Outer Ring Prospecting", "Salvation Angels",
				"Serpentis Corporation", "Serpentis Inquest"));

		ArrayList<LPOffer> lpos = db.getLPOffers();
		System.err.println("lp offers loaded");

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
		market.cacheSOs(allSOIDs.stream().mapToInt(i -> i).toArray());
		System.err.println("market cached");

		for (LPOffer lpo : lpos) {
			if (lpo.requirements.lp > 0 && !noHSCorps.contains(lpo.corporation)) {
				OfferAnalysis ana = analyse(lpo, db, market, markettax, minLP);
				offers.add(ana);
			}
		}
		Collections.sort(offers, (oa1, oa2) -> (int) Math.signum(oa2.iskPerLP - oa1.iskPerLP));
		for (OfferAnalysis oa : offers) {
			System.out.println(oa.offer.corporation + " , " + oa.offer.offer_name + " : " + oa.iskPerLP);
		}
	}

	public static OfferAnalysis analyse(LPOffer o, DataBase db, ESIMarket market, double markettax, int minLP) {
		OfferAnalysis ret = new OfferAnalysis();
		ret.offer = o;
		int mult = (int) Math.ceil(1.0 * minLP / o.requirements.lp);

		double prodBO = market.getBO(o.product.type_id, o.product.quantity * mult) * (1 - markettax);
		double reqSO = 0;
		for (ItemRef rq : o.requirements.items) {
			reqSO += market.getSO(rq.type_id, rq.quantity * mult) * (1 + markettax);
		}
		ret.iskPerLP = (prodBO - reqSO - o.requirements.isk * mult) / o.requirements.lp / mult;
		return ret;
	}

}
