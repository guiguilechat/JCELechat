package fr.guiguilechat.eveonline.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.ToDoubleFunction;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.Agent;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class EvaluateL4Agents {

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

		el4a.corpEvaluator = new LPCorpEvaluator(el4a.db).cached(el4a.getMarket())::analyseCorporationOffers;
		el4a.systemEvaluator = new SysBurnerEvaluator(10, el4a.db);
		Agent[] agents = el4a.getPossibleAgents().toArray(Agent[]::new);
		HashMap<Agent, Double> agentInterest = new HashMap<>();
		for (Agent a : agents) {
			double interest = el4a.evaluateAgent(a);
			if (interest > 0) {
				agentInterest.put(a, interest);
			}
		}
		ArrayList<Entry<Agent, Double>> evals = new ArrayList<>(agentInterest.entrySet());
		Collections.sort(evals, (e1, e2) -> (int) Math.signum(e2.getValue() - e1.getValue()));
		for (Entry<Agent, Double> e : evals) {
			System.out.println(e.getKey().name + " : " + e.getValue() + " in " + e.getKey().location);
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
	protected ToDoubleFunction<String> corpEvaluator;

	/**
	 * get the number of M isk/hour we can get from an agent with its burners
	 *
	 * @param agent
	 * @return
	 */
	protected double evaluateAgent(Agent agent) {
		double corpval = corpEvaluator.applyAsDouble(agent.corporation);

		double freqHS = systemEvaluator.freqHS(agent.system);
		double avgDist = systemEvaluator.avgDist(agent.system);
		double secBonus = systemEvaluator.secBonus(agent.system);
		double nbPerHour = 60 / (3 + avgDist * 2);
		double ret = freqHS * freqHS * nbPerHour * (5 + (3.0 + corpval * 8.3 / 1000) * secBonus);
		return ret;
	}

}
