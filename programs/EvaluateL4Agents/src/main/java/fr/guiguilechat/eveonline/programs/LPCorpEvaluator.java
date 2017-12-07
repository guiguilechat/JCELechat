package fr.guiguilechat.eveonline.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.EveDatabase;
import fr.guiguilechat.eveonline.model.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.model.database.yaml.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.model.esi.raw.market.Markets;


/**
 * evaluate ratio isk/lp of a corp
 * <p>
 * two functions :
 * <ul>
 * <li>{@link #findCorpBestLPOffer(Markets, String)} get the best ratio of
 * isk/LP for a given corp name</li>
 * <li>{@link #analyseOffers(Markets, String, double)} list the offers of a
 * corporation name by decreasing order.</li>
 * </ul>
 * </p>
 * <p>
 * for complex operations, the values should be cached. for this reason, use
 * {@link #cached(Markets)} to get a caching version. the caching version
 * store the values
 * </p>
 */
public class LPCorpEvaluator {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LPCorpEvaluator.class);

	public static class OfferAnalysis {

		public LPOffer offer;
		public double iskPerLPSOBO;
		public double iskPerLPBOSO;
		public double iskPerLPAVG;
		public String offerCorp;

	}


	public final EveDatabase db;

	protected Markets market = null;

	// adjust sales by removing this taxe
	protected double saleTax = 0.01;

	// adjust placement of BO or SO by removing this tax
	protected double brokerFee = 0.02;

	protected int amountLP = 1000000;

	private HashMap<String, List<OfferAnalysis>> cachedLists = new HashMap<>();

	public void clearCache() {
		cachedLists.clear();
		if (market != null) {
			market.clearCache();
		}
	}

	public LPCorpEvaluator() {
		this(new YamlDatabase());
	}

	/**
	 *
	 * @param db
	 *          the database to get the lp offers from.
	 */
	public LPCorpEvaluator(EveDatabase db) {
		this(db, null);
	}

	/**
	 *
	 * @param db
	 *          the database to get the lp offers from.
	 */
	public LPCorpEvaluator(EveDatabase db, Markets market) {
		this.db = db;
		this.market = market;
	}


	public LPCorpEvaluator withLPAmount(int amount) {
		amountLP = amount;
		cachedLists.clear();
		return this;
	}

	public LPCorpEvaluator withSaleTax(double tax) {
		saleTax = tax;
		cachedLists.clear();
		return this;
	}

	public LPCorpEvaluator withBrokerFee(double fee) {
		brokerFee = fee;
		cachedLists.clear();
		return this;
	}

	public LPCorpEvaluator withMarket(String region) {
		market = db.ESIRegion(region);
		cachedLists.clear();
		return this;
	}

	public List<OfferAnalysis> analyseCorpOffers(String corpName) {
		List<OfferAnalysis> ret = null;
		synchronized (cachedLists) {
			if (cachedLists.containsKey(corpName)) {
				ret = cachedLists.get(corpName);
			} else {
				ret = new ArrayList<>();
				cachedLists.put(corpName, ret);
			}
		}
		// we ensure the list will not be empty. if no data can be retrieved, we
		// had null
		synchronized (ret) {
			if (ret.isEmpty()) {
				logger.debug("evaluating offers for corp " + corpName);
				ret.addAll(analyseOffers(market, corpName));
				for (OfferAnalysis r : ret) {
					logger.trace(" " + r.offer.offer_name + " : " + r.iskPerLPSOBO);
				}
				if (ret.isEmpty()) {
					ret.add(null);
				}
			}
		}
		if (!ret.isEmpty() && ret.get(0) == null) {
			return Collections.emptyList();
		}
		return ret;
	}

	protected ArrayList<LPOffer> listCorpOffers(String corpName) {
		ArrayList<LPOffer> lpos = new ArrayList<>(db.getLPOffers());
		lpos.removeIf(offer -> !corpName.equals(offer.corporation) || offer.requirements.lp == 0);
		return lpos;
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
	protected List<OfferAnalysis> analyseOffers(Markets market, String corpName) {
		Collection<LPOffer> lpos = listCorpOffers(corpName);
		HashSet<Integer> allIDs = new HashSet<>();

		for (LPOffer lpo : lpos) {
			allIDs.add(lpo.product.type_id);
			for (ItemRef e : lpo.requirements.items) {
				allIDs.add(e.type_id);
			}
		}

		List<OfferAnalysis> offers = lpos.parallelStream().map(lp -> analyse(lp, market))
				.filter(oa -> oa != null && oa.iskPerLPBOSO > 0)
				.collect(Collectors.toList());

		try {
			Collections.sort(offers, (oa1, oa2) -> (int) Math.signum(oa2.iskPerLPSOBO - oa1.iskPerLPSOBO));
		} catch (Exception e) {
			System.err.println(Arrays.asList(offers));
			throw new UnsupportedOperationException(e);
		}
		groupOrders(offers);
		return offers;
	}

	/**
	 * make lp offer analysis on a given market.
	 *
	 * @param o
	 *          the lp offer
	 * @param market
	 *          the market for BO/SO
	 * @return a new offer analysis which contains the data analysis.
	 */
	protected OfferAnalysis analyse(LPOffer o, Markets market) {
		OfferAnalysis ret = new OfferAnalysis();
		ret.offer = o;
		ret.offerCorp = o.corporation;
		int mult = (int) Math.ceil(1.0 * amountLP / o.requirements.lp);

		double prodBO = market.getBO(o.product.type_id, o.product.quantity * mult) * (100.0 - saleTax) / 100;
		double prodSO = market.getSO(o.product.type_id, 1) * (o.product.quantity * mult) * (100.0 - saleTax - brokerFee)
				/ 100;
		if (prodSO == Double.POSITIVE_INFINITY) {
			prodSO = 0;
		}
		double prodAVG = market.priceAverage(o.product.type_id) * o.product.quantity * mult * (100.0 - saleTax) / 100;

		double reqSO = o.requirements.isk * mult;
		double reqBO = o.requirements.isk * mult;
		double reqAVG = o.requirements.isk * mult;
		reqSO += o.requirements.items.parallelStream().mapToDouble(rq -> market.getSO(rq.type_id, rq.quantity * mult))
				.sum();
		reqBO += o.requirements.items.parallelStream()
				.mapToDouble(rq -> market.getBO(rq.type_id, 1) * rq.quantity * mult * (100.0 - brokerFee) / 100)
				.sum();
		reqAVG += o.requirements.items.parallelStream()
				.mapToDouble(rq -> market.priceAverage(rq.type_id) * rq.quantity * mult).sum();
		ret.iskPerLPSOBO = (prodBO - reqSO) / o.requirements.lp / mult;
		ret.iskPerLPBOSO = (prodSO - reqBO) / o.requirements.lp / mult;
		ret.iskPerLPAVG = (prodAVG - reqAVG) / o.requirements.lp / mult;
		if (ret.iskPerLPBOSO == Double.POSITIVE_INFINITY) {
			System.err.println("offer " + o.offer_name + " has boso " + ret.iskPerLPBOSO + " reqBO=" + reqBO + " prodSO="
					+ prodSO + " mult=" + mult);
		}
		return ret;
	}

	/**
	 * group the offers if they have same item, return , ands lp requirements
	 *
	 * @param offers
	 */
	public static void groupOrders(List<OfferAnalysis> offers) {
		OfferAnalysis previous = null;
		for (Iterator<OfferAnalysis> it = offers.iterator(); it.hasNext();) {
			OfferAnalysis oa = it.next();
			if (previous != null && previous.iskPerLPSOBO == oa.iskPerLPSOBO
					&& previous.offer.offer_name.equals(oa.offer.offer_name)
					&& previous.offer.requirements.lp == oa.offer.requirements.lp
					&& previous.offer.requirements.isk == oa.offer.requirements.isk) {
				it.remove();
				previous.offerCorp = previous.offerCorp + ", " + oa.offer.corporation;
			} else {
				previous = oa;
			}
		}
	}
}
