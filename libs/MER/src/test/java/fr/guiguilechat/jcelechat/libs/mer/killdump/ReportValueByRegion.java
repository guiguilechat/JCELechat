package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;

public class ReportValueByRegion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("valueByRegion", null, List.of(Value.SUM), SystemFilters.AS, SystemFilters.HS, SystemFilters.JS,
				SystemFilters.LS, SystemFilters.NS, SystemFilters.PS, SystemFilters.WS);
	}
}
