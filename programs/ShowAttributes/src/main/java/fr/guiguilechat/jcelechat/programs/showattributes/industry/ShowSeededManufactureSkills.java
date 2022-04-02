package fr.guiguilechat.jcelechat.programs.showattributes.industry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;

public class ShowSeededManufactureSkills {

	public static void main(String[] args) {
		Map<String, Integer> skillReq = new HashMap<>();
		for (Blueprint bp : Blueprint.load().values()) {
			if (!bp.seeded || bp.manufacturing == null || bp.manufacturing.skills.isEmpty()) {
				continue;
			}
			for (Entry<TypeRef<Skill>, Integer> e : bp.manufacturing.skills.entrySet()) {
				String name = e.getKey().name();
				skillReq.put(name, Math.max(skillReq.getOrDefault(name, 0), e.getValue()));
			}
			if (bp.invention != null) {
				for (MaterialProd<?> product : bp.invention.products) {
					Blueprint invented = Blueprint.of(product.id);
					if (invented.manufacturing == null || invented.manufacturing.skills.isEmpty()) {
						continue;
					}
					for (Entry<TypeRef<Skill>, Integer> e : invented.manufacturing.skills.entrySet()) {
						String name = e.getKey().name();
						skillReq.put(name, Math.max(skillReq.getOrDefault(name, 0), e.getValue()));
					}

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
