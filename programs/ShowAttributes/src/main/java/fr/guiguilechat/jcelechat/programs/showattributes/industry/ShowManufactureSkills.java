package fr.guiguilechat.jcelechat.programs.showattributes.industry;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;

public class ShowManufactureSkills {

	public static void main(String[] args) {
		Map<String, Integer> skillReq = new HashMap<>();
		for (Blueprint bp : Blueprint.load().values()) {
			if (bp.manufacturing == null || bp.manufacturing.skills.isEmpty()) {
				continue;
			}
			for (Entry<TypeRef<Skill>, Integer> e : bp.manufacturing.skills.entrySet()) {
				String name = e.getKey().name();
				skillReq.put(name, Math.max(skillReq.getOrDefault(name, 0), e.getValue()));
			}
		}

		for (Entry<String, Integer> e : skillReq.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}

}
