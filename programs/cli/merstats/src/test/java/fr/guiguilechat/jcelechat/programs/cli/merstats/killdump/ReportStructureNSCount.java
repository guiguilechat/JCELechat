package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.CatFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters.TypeFilter;

public class ReportStructureNSCount {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("structureNSCount", NPCFilter.NONPC.and(SystemFilters.NS).and(CatFilter.STRUCTURE),
				List.of(Count.AGG), TypeFilter.ASTRAHUS, TypeFilter.ATHANOR, TypeFilter.AZBEL, TypeFilter.FORTIZAR,
				TypeFilter.KEEPSTAR, TypeFilter.RAITARU, TypeFilter.SOTIYO, TypeFilter.TATARA);
	}
}
