package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.KDReport;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.SystemFilters;

public class ReportValueByRegion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("valueByRegion", NPCFilter.NONPC, List.of(Value.SUM), SystemFilters.AS, SystemFilters.HS,
				SystemFilters.JS,
				SystemFilters.LS, SystemFilters.NS, SystemFilters.PS, SystemFilters.WS);
	}
}
