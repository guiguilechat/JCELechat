package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.GroupFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.TypeFilter;

public class ReportIndusHSValue {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("IndusHSvalue", SystemFilters.HS_TYPED.and(NPCFilter.NONPC), List.of(Value.SUM),
				TypeFilter.VENTURE,
				GroupFilter.MINING_BARGE, GroupFilter.EXHUMER, TypeFilter.ORCA, GroupFilter.FREIGHTER,
				GroupFilter.JUMP_FREIGHTER);
	}

}
