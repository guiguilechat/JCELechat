package fr.guiguilechat.jcelechat.programs.showattributes.industry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;

/**
 * list the skills required for invention, by decreasing impact/SP, and prints
 * that lists as a skill plan
 *
 * @author
 *
 */
public class ShowInventionSkillsAll {

	public static void main(String[] args) {
		Map<String, Double> skillScore = new HashMap<>();
		for (Blueprint bp : Blueprint.yaml().load().values()) {
			if (bp.invention == null || bp.invention.skills.isEmpty()) {
				continue;
			}
			double mult = 1.0 / bp.invention.skills.size();
			for (TypeRef<Skill> sk : bp.invention.skills.keySet()) {
				skillScore.put(sk.name(), skillScore.getOrDefault(sk.name(), 0.0) + mult / sk.type().skilltimeconstant);
			}
		}
		ArrayList<Entry<String, Double>> sorting = new ArrayList<>(skillScore.entrySet());
		Collections.sort(sorting, Comparator.comparing(Entry::getValue));
		Collections.reverse(sorting);
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Double> e : sorting) {
			sb.append(e.getKey()).append(" 5").append("\n");
		}
		System.out.println(sb.toString());
	}

}
