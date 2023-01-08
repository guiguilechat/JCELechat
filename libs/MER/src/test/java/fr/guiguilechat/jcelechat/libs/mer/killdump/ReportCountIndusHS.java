package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.io.StringWriter;
import java.util.List;

import org.jfree.data.time.TimeTableXYDataset;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.GroupFilter;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.TypeFilter;

public class ReportCountIndusHS {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		StringWriter sw = new StringWriter();
		TimeTableXYDataset dataset = new TimeTableXYDataset();
		KDReport.generate(sw, dataset, SystemFilters.HS_TYPED,
				List.of(Value.SUM),
				TypeFilter.VENTURE, GroupFilter.MINING_BARGE, GroupFilter.EXHUMER, TypeFilter.ORCA,
				GroupFilter.FREIGHTER, GroupFilter.JUMP_FREIGHTER);
		KDReport.writeCSV("reports/valueIndusHS.csv", sw);
		KDReport.writeAreaGraph("reports/valueIndusHSStacked.png", dataset);
		KDReport.writeLineGraph("reports/valueIndusHSLine.png", dataset);
	}

}
