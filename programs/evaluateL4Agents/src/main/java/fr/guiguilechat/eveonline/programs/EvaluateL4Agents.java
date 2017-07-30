package fr.guiguilechat.eveonline.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.EveDatabase;
import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.Agent;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class EvaluateL4Agents {

	public static void main(String[] args) {
		EvaluateL4Agents el4a = new EvaluateL4Agents();
		el4a.corpEvaluator = new LPCorpEvaluator(el4a.db).cached(new ESIMarket(10000002))::analyseCorporationOffers;
		el4a.systemEvaluator = new SysBurnerEvaluator(8, el4a.db);
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

	public Stream<Agent> getPossibleAgents() {
		return db.getAgents().values().parallelStream()
				.filter(a -> "Security".equals(a.division) && "BasicAgent".equals(a.agentType) && 4 == a.level)
				.filter(a -> isHSSystem(a.system));
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
		System.err.println(" " + agent.name + " freqHS" + freqHS + " avgDist" + avgDist + " secb" + secBonus + " nbperhour"
				+ nbPerHour + " corpval" + corpval);
		return ret;// ((corpval * 8.340 + 3000) * sysval + 5000) / 1000;
	}

}
