package fr.guiguilechat.eveonline.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.DataBase;
import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.Agent;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class EvaluateL4Agents {

	public static void main(String[] args) {
		EvaluateL4Agents el4a = new EvaluateL4Agents();
		el4a.corpEvaluator = new LPCorpEvaluator(el4a.db).cached(new ESIMarket(10000002))::analyseCorporationOffers;
		el4a.systemEvaluator = new SysBurnerEvaluator(7, el4a.db)::evaluate;
		Agent[] agents = el4a.getPossibleAgents().toArray(Agent[]::new);
		HashMap<String, Double> agentInterest = new HashMap<>();
		HashMap<String, Agent> agentIdx = new HashMap<>();
		for (Agent a : agents) {
			double interest = el4a.evaluateAgent(a);
			if (interest > 0) {
				agentInterest.put(a.name, interest);
				agentIdx.put(a.name, a);
			}
		}
		ArrayList<Entry<String, Double>> evals = new ArrayList<>(agentInterest.entrySet());
		Collections.sort(evals, (e1, e2) -> (int) Math.signum(e2.getValue() - e1.getValue()));
		for (Entry<String, Double> e : evals) {
			System.out.println(e.getKey() + " : " + e.getValue() + " in " + agentIdx.get(e.getKey()).location);
		}
	}

	protected DataBase db = new YamlDatabase();

	public Stream<Agent> getPossibleAgents() {
		return db.getAgents().values().parallelStream()
				.filter(a -> "Security".equals(a.division) && "BasicAgent".equals(a.agentType) && 4 == a.level)
				.filter(a -> isHSSystem(a.system));
	}

	public boolean isHSSystem(String sysname) {
		sysname = sysname.replaceAll(" ", "");
		try {
			return db.getLocations().get(sysname).minSec > 0.45;
		} catch (Exception e) {
			System.err.println("can't find system " + sysname);
			return false;
		}
	}

	// evaluate the interest of a system
	protected ToDoubleFunction<String> systemEvaluator;

	// evaluate the isk/lp value of a corpo
	protected ToDoubleFunction<String> corpEvaluator;

	protected double evaluateAgent(Agent agent) {
		double corpval = corpEvaluator.applyAsDouble(agent.corporation);
		double sysval = systemEvaluator.applyAsDouble(agent.system);
		// System.err.println("agent " + agent.name + " corp=" + corpval + " sys=" +
		// sysval);
		return corpval * sysval;
	}

}
