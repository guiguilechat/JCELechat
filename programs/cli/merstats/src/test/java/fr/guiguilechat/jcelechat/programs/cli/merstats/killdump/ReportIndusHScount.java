package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.GroupFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.TypeFilter;

public class ReportIndusHScount {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("IndusHScount", SystemFilters.HS_TYPED.and(NPCFilter.NONPC), List.of(Count.AGG),
				TypeFilter.VENTURE,
				GroupFilter.MINING_BARGE, GroupFilter.EXHUMER, TypeFilter.ORCA, GroupFilter.FREIGHTER,
				GroupFilter.JUMP_FREIGHTER);
	}

}
