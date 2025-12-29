package fr.guiguilechat.jcelechat.programs.showattributes.industry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.exports.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import fr.guiguilechat.jcelechat.utils.SkillUtils;

/**
 * Make a skill requirement list for researching seeded BPOs (material research
 * and time research)
 *
 * @author
 *
 */
public class ShowResearchSkillsSeeded {

	public static void main(String[] args) {
		Map<Skill, Integer> skillReq = new HashMap<>();
		for (Blueprint bp : Blueprint.storage().load().values()) {
			if (!bp.seeded) {
				continue;
			}
			if (bp.research_material != null) {
				for (Entry<TypeRef<Skill>, Integer> e : bp.research_material.skills.entrySet()) {
					Skill name = e.getKey().type();
					skillReq.put(name, Math.max(skillReq.getOrDefault(name, 0), e.getValue()));
				}
			}
			if (bp.research_time != null) {
				for (Entry<TypeRef<Skill>, Integer> e : bp.research_time.skills.entrySet()) {
					Skill name = e.getKey().type();
					skillReq.put(name, Math.max(skillReq.getOrDefault(name, 0), e.getValue()));
				}
			}
		}

		ArrayList<Entry<Skill, Integer>> sorting = new ArrayList<>(skillReq.entrySet());
		Collections.sort(sorting,
				Comparator.comparing(e -> SkillUtils.totalSP(e.getKey(), e.getValue()) + SkillUtils.totalSPInject(e.getKey())));
		for (Entry<Skill, Integer> e : sorting) {
			System.out.println(e.getKey().name + " " + e.getValue());
		}

	}

}
