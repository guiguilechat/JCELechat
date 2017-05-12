package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class ShowLPStore {

	public static class OfferAnalysis{

		public LPOffer offer;
		public double iskPerLP;

	}

	public static void main(String[] args) {
		YamlDatabase db = new YamlDatabase();
		double markettax = 0.02;
		EveCentral market = new EveCentral(EveCentral.JITA_SYSTEM, EveCentral.DODIXIE_SYSTEM, EveCentral.HEK_SYSTEM,
				EveCentral.RENS_SYSTEM, EveCentral.AMARR_SYSTEM);
		ArrayList<OfferAnalysis> offers = new ArrayList<>();

		HashSet<Integer> allIDs = new HashSet<>();
		ArrayList<LPOffer> lpos = db.getLPOffers();
		System.err.println("lp offers loaded");

		for (LPOffer lpo : lpos) {
			allIDs.add(lpo.product.type_id);
			for (ItemRef e : lpo.requirements.items) {
				allIDs.add(e.type_id);
			}
		}

		market.cache(allIDs.stream().mapToInt(i -> i).toArray());
		System.err.println("market cached");

		for (LPOffer lpo : lpos) {
			if (lpo.requirements.lp > 0) {
				OfferAnalysis ana = analyse(lpo, db, market, markettax);
				offers.add(ana);
			}
		}
		Collections.sort(offers, (oa1, oa2) -> (int) Math.signum(oa1.iskPerLP - oa2.iskPerLP));
		for (OfferAnalysis oa : offers) {
			System.err.println(oa.offer.corporation + " , " + oa.offer.offer_name + " : " + oa.iskPerLP);
		}
	}

	public static OfferAnalysis analyse(LPOffer o, DataBase db, EveCentral market, double markettax) {
		OfferAnalysis ret = new OfferAnalysis();
		ret.offer = o;

		double prodBO = market.getBO(o.product.type_id) * o.product.quantity * (1 - markettax);
		double reqSO = 0;
		for (ItemRef rq : o.requirements.items) {
			reqSO += market.getSO(rq.type_id) * rq.quantity * (1 + markettax);
		}
		ret.iskPerLP = (prodBO - reqSO - o.requirements.isk) / o.requirements.lp;
		return ret;
	}

}
