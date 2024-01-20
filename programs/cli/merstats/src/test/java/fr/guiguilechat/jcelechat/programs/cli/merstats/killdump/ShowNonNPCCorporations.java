package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.KDParser;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;

public class ShowNonNPCCorporations {

	public static void main(String[] args) {
		Map<Integer, Integer> atkCorpCount = new HashMap<>();
		Map<Integer, Integer> defCorpCount = new HashMap<>();

		KDParser.INSTANCE.stream().forEach(kde -> {
			int atkCorp = kde.killerCorporationID();
			if (NPCFilter.NPC_CORPORATIONIDS.contains(atkCorp)) {
				atkCorpCount.put(atkCorp, 1 + atkCorpCount.getOrDefault(atkCorp, 0));
			}

			int defCorp = kde.victimCorporationID();
			if (NPCFilter.NPC_CORPORATIONIDS.contains(defCorp)) {
				defCorpCount.put(defCorp, 1 + defCorpCount.getOrDefault(defCorp, 0));
			}

		});

		System.out.println("attacking corp");
		for (Entry<Integer, Integer> e : atkCorpCount.entrySet()) {
			System.out.println("" + e.getKey() + "\t" + e.getValue());
		}
		System.out.println();
		System.out.println("defending corp");
		for (Entry<Integer, Integer> e : defCorpCount.entrySet()) {
			System.out.println("" + e.getKey() + "\t" + e.getValue());
		}

	}

}
