package fr.guiguilechat.eveonline.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

/**
 * evaluate ratio isk/lp of a corp
 * <p>
 * two functions :
 * <ul>
 * <li>{@link #findCorpBestLPOffer(ESIMarket, String)} get the best ratio of
 * isk/LP for a given corp name</li>
 * <li>{@link #analyseOffers(ESIMarket, String, double)} list the offers of a
 * corporation name by decreasing order.</li>
 * </ul>
 * </p>
 * <p>
 * for complex operations, the values should be cached. for this reason, use
 * {@link #cached(ESIMarket)} to get a caching version. the caching version
 * store the values
 * </p>
 */
public class LPCorpEvaluator {

	public static class OfferAnalysis {

		public LPOffer offer;
		public double iskPerLP;
		public String offerGroup;

	}

	// adjust BO by removing this taxe
	double markettax = 0.02;

	// only keep orders with isk/lp >= this value.
	double minISKLPRatio = 1000;

	int amountLP = 1000000;


	public final EveDatabase db;

	public LPCorpEvaluator() {
		this(new YamlDatabase());
	}

	/**
	 *
	 * @param db
	 *          the database to get the lp offers from.
	 */
	public LPCorpEvaluator(EveDatabase db) {
		this.db = db;
	}

	public ArrayList<LPOffer> listCorpOffers(String corpName) {
		ArrayList<LPOffer> lpos = new ArrayList<>(db.getLPOffers());
		lpos.removeIf(offer -> !corpName.equals(offer.corporation) || offer.requirements.lp == 0);
		return lpos;
	}

	public List<OfferAnalysis> analyseOffers(ESIMarket market, String corpName, double minimumIskPerLP) {
		return analyseOffers(market, listCorpOffers(corpName), minimumIskPerLP);
	}

	/**
	 * analyze a list of lp offers and sort them by decreasing isk/lp
	 *
	 * @param market
	 *          the market to consider for BO/SO
	 * @param lpos
	 *          lp offers
	 * @return a new list of the corresponding offer analyses.
	 */
	public List<OfferAnalysis> analyseOffers(ESIMarket market, Collection<LPOffer> lpos, double minimumIskPerLP) {

		HashSet<Integer> allBOIDs = new HashSet<>();
		HashSet<Integer> allSOIDs = new HashSet<>();

		for (LPOffer lpo : lpos) {
			allBOIDs.add(lpo.product.type_id);
			for (ItemRef e : lpo.requirements.items) {
				allSOIDs.add(e.type_id);
			}
		}
		market.cacheBOs(allBOIDs.stream().mapToInt(i -> i).toArray());

		List<OfferAnalysis> offers = lpos.parallelStream().map(lp -> analyse(lp, market, minimumIskPerLP))
				.filter(oa -> oa != null)
				.collect(Collectors.toList());

		try {
			Collections.sort(offers, (oa1, oa2) -> (int) Math.signum(oa2.iskPerLP - oa1.iskPerLP));
		} catch (Exception e) {
			System.err.println(Arrays.asList(offers));
			throw new UnsupportedOperationException(e);
		}
		groupOrders(offers);
		return offers;
	}

	public static void main(String[] args) {
		LPCorpEvaluator eval = new LPCorpEvaluator();
		int streamthreads = Runtime.getRuntime().availableProcessors() * 100;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + streamthreads);

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

		ArrayList<LPOffer> lpos = eval.db.getLPOffers();
		lpos.removeIf(lp -> lp.requirements.lp <= 0 || noHSCorps.contains(lp.corporation));
		System.err.println("lp offers loaded");

		for (Entry<String, ESIMarket> me : markets.entrySet()) {
			System.out.println("");
			System.out.println(me.getKey() + " :");
			List<OfferAnalysis> offers = eval.analyseOffers(me.getValue(), lpos, 1000);
			for (OfferAnalysis oa : offers) {
				System.out.println(oa.offer.offer_name + " ( " + oa.offer.requirements.lp + " lp ): " + oa.iskPerLP
						+ " isk/LP ; " + oa.offerGroup);
			}
		}
	}

	/**
	 * make lp offer analysis on a given market.
	 *
	 * @param o
	 *          the lp offer
	 * @param market
	 *          the market for BO/SO
	 * @param minimumIskPerLP
	 *          the minimum isk per LP to consider this offer.
	 * @return a new offernaalysis which contains the data analysis. return null
	 *         if the order interest is < minimumIskPerLP
	 */
	public OfferAnalysis analyse(LPOffer o, ESIMarket market, double minimumIskPerLP) {
		OfferAnalysis ret = new OfferAnalysis();
		ret.offer = o;
		ret.offerGroup = o.corporation;
		int mult = (int) Math.ceil(1.0 * amountLP / o.requirements.lp);

		double prodBO = market.getBO(o.product.type_id, o.product.quantity * mult) * (1 - markettax);
		// if the BO-cost / lp is too low, it wont get bigger when taking SO into
		// account.
		if ((prodBO - o.requirements.isk * mult) / o.requirements.lp / mult < minimumIskPerLP) {
			return null;
		}
		double reqSO = o.requirements.isk * mult;
		market.cacheSOs(o.requirements.items.stream().mapToInt(ir -> ir.type_id).toArray());

		reqSO += o.requirements.items.parallelStream().mapToDouble(rq -> market.getSO(rq.type_id, rq.quantity * mult))
				.sum();
		ret.iskPerLP = (prodBO - reqSO) / o.requirements.lp / mult;
		return ret.iskPerLP >= minimumIskPerLP ? ret : null;
	}

	/**
	 * group the offers if they have same item, return , ands/lp requirements
	 *
	 * @param offers
	 */
	public static void groupOrders(List<OfferAnalysis> offers) {
		OfferAnalysis previous = null;
		for (Iterator<OfferAnalysis> it = offers.iterator(); it.hasNext();) {
			OfferAnalysis oa = it.next();
			if (previous != null && previous.iskPerLP == oa.iskPerLP
					&& previous.offer.offer_name.equals(oa.offer.offer_name)
					&& previous.offer.requirements.lp == oa.offer.requirements.lp
					&& previous.offer.requirements.isk == oa.offer.requirements.isk) {
				it.remove();
				previous.offerGroup = previous.offerGroup + ", " + oa.offer.corporation;
			} else {
				previous = oa;
			}
		}
	}

	/**
	 * cached data for a given market
	 *
	 */
	public class MarketLPEvaluator {

		protected final ESIMarket market;

		public MarketLPEvaluator(ESIMarket market) {
			this.market = market;
		}

		private HashMap<String, List<OfferAnalysis>> cachedLists = new HashMap<>();

		public List<OfferAnalysis> analyseCorpOffers(String corpName) {
			if (cachedLists.containsKey(corpName)) {
				return cachedLists.get(corpName);
			}
			List<OfferAnalysis> ret = analyseOffers(market, corpName, minISKLPRatio);
			cachedLists.put(corpName, ret);
			return ret;
		}

	}

	public MarketLPEvaluator cached(ESIMarket market) {
		return new MarketLPEvaluator(market);
	}
}
