package fr.guiguilechat.jcelechat.programs.showattributes.industry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.exports.industry.Blueprint;
import fr.guiguilechat.jcelechat.libs.exports.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import fr.guiguilechat.jcelechat.utils.SkillUtils;

/**
 *
 * @author
 *
 */
public class ShowManufSkillsSeeded {

	public static void main(String[] args) {
		Map<Skill, Integer> skillReq = new HashMap<>();
		for (Blueprint bp : Blueprint.storage().load().values()) {
			if (!bp.seeded || bp.manufacturing == null || bp.manufacturing.skills.isEmpty()) {
				continue;
			}
			for (Entry<TypeRef<Skill>, Integer> e : bp.manufacturing.skills.entrySet()) {
				Skill skill = e.getKey().type();
				skillReq.put(skill, Math.max(skillReq.getOrDefault(skill, 0), e.getValue()));
			}
			if (bp.invention != null) {
				for (MaterialProd<?> product : bp.invention.products) {
					Blueprint invented = Blueprint.of(product.id);
					if (invented.manufacturing == null || invented.manufacturing.skills.isEmpty()) {
						continue;
					}
					for (Entry<TypeRef<Skill>, Integer> e : invented.manufacturing.skills.entrySet()) {
						Skill skill = e.getKey().type();
						skillReq.put(skill, Math.max(skillReq.getOrDefault(skill, 0), e.getValue()));
					}

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
