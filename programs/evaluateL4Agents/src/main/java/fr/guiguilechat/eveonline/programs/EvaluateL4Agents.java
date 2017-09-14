package fr.guiguilechat.eveonline.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.Agent;
import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.LPCorpEvaluator.MarketLPEvaluator;
import fr.guiguilechat.eveonline.programs.LPCorpEvaluator.OfferAnalysis;

public class EvaluateL4Agents {

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
		EvaluateL4Agents el4a = new EvaluateL4Agents();

		for (String arg : args) {
			if (arg.startsWith("faction=")) {
				el4a.allowCorporations(arg.substring("faction=".length()).split(","));
			} else if (arg.startsWith("region=")) {
				el4a.marketRegion = arg.substring("region=".length());
			} else if (arg.startsWith("minl=")) {
				el4a.minLevel = Integer.parseInt(arg.substring("minl=".length()));
			} else if (arg.startsWith("maxl=")) {
				el4a.maxLevel = Integer.parseInt(arg.substring("minl=".length()));
			}
		}

		el4a.corpEvaluator = new LPCorpEvaluator(el4a.db).cached(el4a.getMarket());
		el4a.systemEvaluator = new SysBurnerEvaluator(10, el4a.db);
		Agent[] agents = el4a.getPossibleAgents().toArray(Agent[]::new);
		ArrayList<LocalizedLPOffer> offers = new ArrayList<>();
		for (Agent a : agents) {
			offers.addAll(el4a.evaluateOffers(a));
		}
		Collections.sort(offers, (e1, e2) -> (int) Math.signum(e2.sobogain - e1.sobogain));
		System.out.println("agent ; offer ; corporation ; location ; sobogain ; bosogain ; avggain");
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
		for (String sub : corpName.toLowerCase().split(" ")) {
			for (Pattern p : allowedCorporations) {
				if (p.matcher(sub).matches()) {
					return true;
				}
			}
		}
		return false;
	}

	public Stream<Agent> getPossibleAgents() {
		Stream<Agent> ret = db.getAgents().values().parallelStream()
				.filter(a -> "Security".equals(a.division) && "BasicAgent".equals(a.agentType));
		ret = ret.filter(a -> a.level >= minLevel && a.level <= maxLevel);
		if (onlyHS) {
			ret = ret.filter(a -> isHSSystem(a.system));
		}
		if (allowedCorporations != null) {
			ret = ret.filter(a -> a.corporation != null && acceptCorp(a.corporation));
		}
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
		double freqHS = systemEvaluator.freqHS(agent.system);
		double avgDist = systemEvaluator.avgDist(agent.system);
		double secBonus = systemEvaluator.secBonus(agent.system);
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
