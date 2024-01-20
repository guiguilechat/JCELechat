package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.SystemFilters;

public class ReportTotalByRegionCount {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("totalByRegionCount", NPCFilter.NONPC, List.of(Count.AGG), SystemFilters.AS, SystemFilters.HS,
				SystemFilters.JS,
				SystemFilters.LS, SystemFilters.NS, SystemFilters.PS, SystemFilters.WS);
	}
}
