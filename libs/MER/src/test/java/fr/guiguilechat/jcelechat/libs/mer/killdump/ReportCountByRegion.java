package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.io.StringWriter;
import java.util.List;

import org.jfree.data.time.TimeTableXYDataset;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;

public class ReportCountByRegion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		StringWriter sw = new StringWriter();
		TimeTableXYDataset dataset = new TimeTableXYDataset();
		KDReport.generate(sw, dataset, null, List.of(Count.AGG), SystemFilters.AS, SystemFilters.HS, SystemFilters.JS,
				SystemFilters.LS, SystemFilters.NS, SystemFilters.PS, SystemFilters.WS);
		KDReport.writeCSV("reports/countByRegion.csv", sw);
		KDReport.writeAreaGraph("reports/countByRegionStacked.png", dataset);
		KDReport.writeLineGraph("reports/countByRegionLine.png", dataset);
	}
}
