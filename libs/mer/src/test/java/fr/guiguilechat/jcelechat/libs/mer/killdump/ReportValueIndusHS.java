package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.GroupFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.TypeFilter;

public class ReportValueIndusHS {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("valueIndusHS", SystemFilters.HS_TYPED.and(NPCFilter.NONPC), List.of(Value.SUM),
				TypeFilter.VENTURE,
				GroupFilter.MINING_BARGE, GroupFilter.EXHUMER, TypeFilter.ORCA, GroupFilter.FREIGHTER,
				GroupFilter.JUMP_FREIGHTER);
	}

}
