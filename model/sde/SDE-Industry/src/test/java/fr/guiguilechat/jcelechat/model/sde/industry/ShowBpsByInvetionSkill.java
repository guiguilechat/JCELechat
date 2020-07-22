package fr.guiguilechat.jcelechat.model.sde.industry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.Activity;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;

public class ShowBpsByInvetionSkill {

	public static void main(String[] args) {
		HashMap<String, Map<Integer, List<String>>> skillName2level2bps = new HashMap<>();
		for (Blueprint blueprint : Blueprint.load().values()) {
			String bpName = blueprint.name();
			if (blueprint.invention != null) {
				Activity act = blueprint.invention;
				for (Entry<TypeRef<Skill>, Integer> e : act.skills.entrySet()) {
					String skillName = e.getKey().name();
					Integer skillLvl = e.getValue();
					Map<Integer, List<String>> level2bps = skillName2level2bps.get(skillName);
					if (level2bps == null) {
						level2bps = new HashMap<>();
						skillName2level2bps.put(skillName, level2bps);
					}
					List<String> bps = level2bps.get(skillLvl);
					if (bps == null) {
						bps = new ArrayList<>();
						level2bps.put(skillLvl, bps);
					}
					bps.add(bpName);
				}
			}
		}
		for (Entry<String, Map<Integer, List<String>>> e : skillName2level2bps.entrySet()) {
			System.err.println(e.getKey());
			for (int lvl = 0; lvl < 6; lvl++) {
				List<String> bps = e.getValue().get(lvl);
				if (bps != null) {
					System.err.println("\t" + lvl);
					for (String s : bps) {
						System.err.println("\t\t" + s);
					}
				}
			}
		}
	}

}
