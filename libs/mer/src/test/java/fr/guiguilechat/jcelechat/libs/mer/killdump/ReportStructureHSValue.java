package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.CatFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.NPCFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.TypeFilter;

public class ReportStructureHSValue {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		KDReport.makeReport("structureHSValue", NPCFilter.NONPC.and(SystemFilters.HS).and(CatFilter.STRUCTURE),
				List.of(Value.SUM), TypeFilter.ASTRAHUS, TypeFilter.ATHANOR, TypeFilter.AZBEL, TypeFilter.FORTIZAR,
				TypeFilter.KEEPSTAR, TypeFilter.RAITARU, TypeFilter.SOTIYO, TypeFilter.TATARA);
	}
}
