package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.GroupFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.TypeFilter;

public class ReportCountIndusHS {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("countIndusHS", SystemFilters.HS_TYPED.and(NPCFilter.NONPC), List.of(Count.AGG),
				TypeFilter.VENTURE,
				GroupFilter.MINING_BARGE, GroupFilter.EXHUMER, TypeFilter.ORCA, GroupFilter.FREIGHTER,
				GroupFilter.JUMP_FREIGHTER);
	}

}