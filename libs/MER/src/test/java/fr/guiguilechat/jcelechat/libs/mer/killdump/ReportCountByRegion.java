package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;

public class ReportCountByRegion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("countByRegion", NPCFilter.NONPC, List.of(Count.AGG), SystemFilters.AS, SystemFilters.HS,
				SystemFilters.JS,
				SystemFilters.LS, SystemFilters.NS, SystemFilters.PS, SystemFilters.WS);
	}
}
