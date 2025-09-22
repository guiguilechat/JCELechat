package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.List;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;

public class ReportNPCPct {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("NPCPct", null, List.of(Count.PCT), NPCFilter.NPC, NPCFilter.ATKNPC, NPCFilter.DEFNPC,
				NPCFilter.ATKDEFNPC);

		// listNPCCorps();
	}

	public static void listNPCCorps() {
		StringBuilder nonNPC = new StringBuilder();
		for (Entry<Integer, EnpcCorporations> e : EnpcCorporations.LOADER.load().entrySet()) {
			if (NPCFilter.isNPCCorp(e.getValue())) {
				System.out.println("" + e.getKey() + "\t" + e.getValue().enName());
			} else {
				nonNPC.append("" + e.getKey() + "\t" + e.getValue().enName() + "\n");
			}
		}
		System.out.println();
		System.out.println(nonNPC);

	}

}
