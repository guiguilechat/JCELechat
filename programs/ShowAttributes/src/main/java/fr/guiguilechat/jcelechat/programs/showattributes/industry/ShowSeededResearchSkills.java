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
 * Make a skill requirement list for researching seeded BPOs (material r
 *
 * @author
 *
 */
public class ShowSeededResearchSkills {

	public static void main(String[] args) {
		Map<String, Integer> skillReq = new HashMap<>();
		// hardcoded 3 skills that reduce the time
		// skillReq.put("Metallurgy", 5);
		// skillReq.put("Research", 5);
		// skillReq.put("Advanced Industry", 5);
		for (Blueprint bp : Blueprint.load().values()) {
			if (!bp.seeded) {
				continue;
			}
			if (bp.research_material != null) {
				for (Entry<TypeRef<Skill>, Integer> e : bp.research_material.skills.entrySet()) {
					String name = e.getKey().name();
					skillReq.put(name, Math.max(skillReq.getOrDefault(name, 0), e.getValue()));
				}
			}
			if (bp.research_time != null) {
				for (Entry<TypeRef<Skill>, Integer> e : bp.research_time.skills.entrySet()) {
					String name = e.getKey().name();
					skillReq.put(name, Math.max(skillReq.getOrDefault(name, 0), e.getValue()));
				}
			}
		}

		ArrayList<Entry<String, Integer>> sorting = new ArrayList<>(skillReq.entrySet());
		Collections.sort(sorting, Comparator.comparing(Entry::getValue));
		for (Entry<String, Integer> e : sorting) {
			System.out.println(e.getKey() + " " + e.getValue());
		}

	}

}
