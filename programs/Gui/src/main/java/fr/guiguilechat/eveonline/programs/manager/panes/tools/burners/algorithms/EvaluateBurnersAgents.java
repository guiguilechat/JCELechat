package fr.guiguilechat.eveonline.programs.manager.panes.tools.burners.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.EveDatabase;
import fr.guiguilechat.eveonline.model.database.yaml.Agent;
import fr.guiguilechat.eveonline.model.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.manager.Settings.MissionStats;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.burners.algorithms.LPCorpEvaluator.OfferAnalysis;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.burners.algorithms.SysBurnerEvaluator.SystemData;

public class EvaluateBurnersAgents {

	private static final Logger logger = LoggerFactory.getLogger(EvaluateBurnersAgents.class);

	// evaluate the interest of a system
	public SysBurnerEvaluator systemEvaluator;

	// evaluate the k isk/lp value of a corpo
	public LPCorpEvaluator corpEvaluator;

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

	public EvaluateBurnersAgents() {
		this(new YamlDatabase());
	}

	public EvaluateBurnersAgents(EveDatabase db) {
		this.db = db;
		systemEvaluator = new SysBurnerEvaluator(10, db);
		corpEvaluator = new LPCorpEvaluator(db);
		if (db == null) {
			throw new NullPointerException();
		}
	}

	public Collection<MissionStats> missions = new ArrayList<>();

	protected EveDatabase db;

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
				.filter(a -> "Security".equals(a.division) && "BasicAgent".equals(a.agentType) && isHSSystem(a.system)
						&& a.level == 4);
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

	protected Stream<LocalizedLPOffer> evaluateOffers(Agent agent) {
		MissionStats[] missions = this.missions.stream().filter(MissionStats::isActive).toArray(MissionStats[]::new);
		if (missions.length == 0) {
			return Stream.empty();
		}
		SystemData sysEval = systemEvaluator.evaluate(agent.system);
		double freqHS = sysEval.freqHS;
		double nbPerHour = 3600.0 / Stream.of(missions).mapToDouble(m ->
		m.timetokill_s
		//time to dock, repair, move items, cancel offers.
		+60
		// time to travel, per jump (distance). we assume 20AU per system
		+ 2 * (m.isBurner() ? sysEval.burnerAvgDist : sysEval.constelAvgDist)
		* (m.systemTravel(20.0) + 7 + m.align_time_s)
		// time to make the last warp. we assume 10AU for initial warp
		+ 2 * m.systemTravel(10.0)).sum() * missions.length;
		double isk_static = Stream.of(missions).mapToDouble(m -> m.isk_cstt).sum() / missions.length;
		double isk_indexed = Stream.of(missions).mapToDouble(m -> m.isk_indexed).sum() / missions.length;
		double lp = Stream.of(missions).mapToDouble(m -> m.lp).sum() / missions.length;
		// System.err.println("agent in " + agent.system + " average burnerjumps=" +
		// sysEval.burnerAvgDist + " nbperhour="
		// + nbPerHour + " isk_static=" + isk_static
		// + " isk_indexed=" + isk_indexed + " lp=" + lp);
		double secBonus = sysEval.bonusTrueSec;
		List<OfferAnalysis> offers = corpEvaluator.analyseCorpOffers(agent.corporation);
		return offers.stream().map(oa -> {
			LocalizedLPOffer llo = new LocalizedLPOffer(oa.offer, agent);
			llo.sobogain = freqHS * freqHS * nbPerHour * (isk_static + (isk_indexed + oa.iskPerLPSOBO * lp) * secBonus);
			llo.bosogain = freqHS * freqHS * nbPerHour * (isk_static + (isk_indexed + oa.iskPerLPBOSO * lp) * secBonus);
			llo.avggain = freqHS * freqHS * nbPerHour * (isk_static + (isk_indexed + oa.iskPerLPAVG * lp) * secBonus);
			return llo;
		});
	}

	public Stream<LocalizedLPOffer> streamOffers() {
		return getPossibleAgents().parallel().flatMap(a -> evaluateOffers(a));
	}

}
