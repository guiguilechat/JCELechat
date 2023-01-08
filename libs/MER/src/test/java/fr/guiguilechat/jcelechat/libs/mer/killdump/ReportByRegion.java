package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;

public class ReportByRegion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out
				.println(KDReport.generate(null, List.of(Count.AGG, Count.PCT, Value.SUM, Value.PCT), SystemFilters.HS,
						SystemFilters.LS, SystemFilters.NS, SystemFilters.WS));
	}
}
