package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.GroupFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.TypeFilter;

public class ReportIndusKills {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out
				.println(KDReport.generate(SystemFilters.HS_TYPED,
						List.of(Count.TOTAL, Count.PCT, Value.SUM, Value.AVG, Value.MAX),
				new TypeFilter(32880), new GroupFilter(463), new GroupFilter(543), new TypeFilter(28606),
				new GroupFilter(513), new GroupFilter(902), new GroupFilter(31)));
	}

}
