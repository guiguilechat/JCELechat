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
import fr.guiguilechat.jcelechat.utils.SkillUtils;

/**
 * All the skills required to manufacture something. sorted by required total SP
 * increasing
 *
 * @author
 *
 */
public class ShowManufSkillsAll {

	public static void main(String[] args) {
		Map<Skill, Integer> skillReq = new HashMap<>();
		for (Blueprint bp : Blueprint.yaml().load().values()) {
			if (bp.manufacturing == null || bp.manufacturing.skills.isEmpty()) {
				continue;
			}
			for (Entry<TypeRef<Skill>, Integer> e : bp.manufacturing.skills.entrySet()) {
				skillReq.put(e.getKey().type(), Math.max(skillReq.getOrDefault(e.getKey().type(), 0), e.getValue()));
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
