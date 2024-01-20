package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.KDReport;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.SystemFilters;

public class ReportCountByRegion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("countByRegion", NPCFilter.NONPC, List.of(Count.AGG), SystemFilters.AS, SystemFilters.HS,
				SystemFilters.JS,
				SystemFilters.LS, SystemFilters.NS, SystemFilters.PS, SystemFilters.WS);
	}
}
