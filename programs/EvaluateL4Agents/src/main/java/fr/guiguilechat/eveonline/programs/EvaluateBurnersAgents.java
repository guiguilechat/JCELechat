package fr.guiguilechat.eveonline.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.EveDatabase;
import fr.guiguilechat.eveonline.model.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.model.database.yaml.Agent;
import fr.guiguilechat.eveonline.model.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.LPCorpEvaluator.MarketLPEvaluator;
import fr.guiguilechat.eveonline.programs.LPCorpEvaluator.OfferAnalysis;
import fr.guiguilechat.eveonline.programs.SysBurnerEvaluator.SystemData;

public class EvaluateBurnersAgents {

	private static final Logger logger = LoggerFactory.getLogger(EvaluateBurnersAgents.class);

	public static class LocalizedLPOffer extends LPOffer {

		public Agent agent;

		public double sobogain = 0;

		public double bosogain = 0;

		public double avggain = 0;

		public LocalizedLPOffer(LPOffer offer, Agent agent) {
			product = offer.product;
			corporation = offer.corporation;
			offer_name = offer.offer_name;
			requirements = offer.requirements;
			this.agent = agent;
		}
	}

	public static void main(String[] args) {
		// number of concurrent threads in the parallel pool
		int parrallelism = Runtime.getRuntime().availableProcessors() * 100;

		EvaluateBurnersAgents el4a = new EvaluateBurnersAgents();
		LPCorpEvaluator ceval = new LPCorpEvaluator(el4a.db).withLPAmount(1000000);

		for (String arg : args) {
			if (arg.startsWith("corp=")) {
				el4a.allowCorporations(arg.substring("corp=".length()).split(","));
			} else if (arg.startsWith("region=")) {
				el4a.marketRegion = arg.substring("region=".length());
			} else if (arg.startsWith("minl=")) {
				el4a.minLevel = Integer.parseInt(arg.substring("minl=".length()));
			} else if (arg.startsWith("maxl=")) {
				el4a.maxLevel = Integer.parseInt(arg.substring("minl=".length()));
			} else if (arg.startsWith("parallel=")) {
				parrallelism = Integer.parseInt(arg.substring("parallel=".length()));
			} else if (arg.startsWith("lp=")) {
				ceval.withLPAmount(Integer.parseInt(arg.substring("lp=".length())));
			}
		}

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		ESIMarket market = el4a.getMarket();
		el4a.corpEvaluator = ceval.cached(market);
		el4a.systemEvaluator = new SysBurnerEvaluator(10, el4a.db);
		List<LocalizedLPOffer> offers = el4a.getPossibleAgents().parallel().flatMap(a -> el4a.evaluateOffers(a).stream())
				.collect(Collectors.toList());
		logger.debug("retrieved " + market.nbCachedBOs() + " BOs and " + market.nbCachedSOs() + " SOs");
		Collections.sort(offers, (e1, e2) -> (int) Math.signum(e2.sobogain - e1.sobogain));
		System.out.println("\nagent ; offer ; corporation ; location ; sobogain ; bosogain ; avggain");
		for (LocalizedLPOffer e : offers) {
			if (e.sobogain > 150) {
				System.out.println(e.agent.name + " ; " + e.offer_name + " ; " + e.agent.corporation + " ; " + e.agent.location
						+ " ; " + e.sobogain + " ; " + e.bosogain + " ; " + e.avggain);
			}
		}
	}

	protected EveDatabase db = new YamlDatabase();

	public String marketRegion = "TheForge";

	public ESIMarket getMarket() {
		return new ESIMarket(db.getLocation(marketRegion).locationID);
	}

	public int minLevel = 4;

	public int maxLevel = 4;

	public boolean onlyHS = true;

	protected Pattern[] allowedCorporations = null;

	public void allowCorporations(String... corpNames) {
		if (corpNames == null || corpNames.length == 0) {
			allowedCorporations = null;
		}
		allowedCorporations = Stream.of(corpNames).map(n -> Pattern.compile(".*" + n.toLowerCase() + ".*"))
				.toArray(Pattern[]::new);
	}

	public boolean acceptCorp(String corpName) {
		if (allowedCorporations == null || allowedCorporations.length == 0) {
			return true;
		}
		if (corpName == null) {
			return false;
		}
		for (String sub : corpName.toLowerCase().split(" ")) {
			for (Pattern p : allowedCorporations) {
				if (p.matcher(sub).matches()) {
					return true;
				}
			}
		}
		logger.trace("corporation " + corpName + " not accepted");
		return false;
	}

	public Stream<Agent> getPossibleAgents() {
		Stream<Agent> ret = db.getAgents().values().parallelStream()
				.filter(a -> "Security".equals(a.division) && "BasicAgent".equals(a.agentType));
		ret = ret.filter(a -> a.level >= minLevel && a.level <= maxLevel);
		if (onlyHS) {
			ret = ret.filter(a -> isHSSystem(a.system));
		}
		ret = ret.filter(a -> acceptCorp(a.corporation));
		return ret;
	}

	public boolean isHSSystem(String sysname) {
		try {
			return db.getLocation(sysname).minSec > 0.45;
		} catch (Exception e) {
			System.err.println("can't find system " + sysname + " " + e);
			return false;
		}
	}

	// evaluate the interest of a system
	protected SysBurnerEvaluator systemEvaluator;

	// evaluate the k isk/lp value of a corpo
	protected MarketLPEvaluator corpEvaluator;

	protected List<LocalizedLPOffer> evaluateOffers(Agent agent) {
		SystemData sysEval = systemEvaluator.evaluate(agent.system);
		double freqHS = sysEval.freqHS;
		double avgDist = sysEval.avgDist;
		double secBonus = sysEval.bonusSys;
		double nbPerHour = 60 / (4 + avgDist * 2);

		ArrayList<LocalizedLPOffer> ret = new ArrayList<>();
		List<OfferAnalysis> offers = corpEvaluator.analyseCorpOffers(agent.corporation);
		for (OfferAnalysis oa : offers) {
			LocalizedLPOffer llo = new LocalizedLPOffer(oa.offer, agent);
			llo.sobogain = freqHS * freqHS * nbPerHour * (5 + (3.0 + oa.iskPerLPSOBO * 8.3 / 1000) * secBonus);
			llo.bosogain = freqHS * freqHS * nbPerHour * (5 + (3.0 + oa.iskPerLPBOSO * 8.3 / 1000) * secBonus);
			llo.avggain = freqHS * freqHS * nbPerHour * (5 + (3.0 + oa.iskPerLPAVG * 8.3 / 1000) * secBonus);
			ret.add(llo);
		}

		return ret;
	}

}
