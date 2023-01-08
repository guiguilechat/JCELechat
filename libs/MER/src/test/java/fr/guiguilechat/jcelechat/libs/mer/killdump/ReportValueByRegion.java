package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.io.StringWriter;
import java.util.List;

import org.jfree.data.time.TimeTableXYDataset;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;
import fr.guiguilechat.jcelechat.libs.mer.killdump.filters.SystemFilters;

public class ReportValueByRegion {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		StringWriter sw = new StringWriter();
		TimeTableXYDataset dataset = new TimeTableXYDataset();
		KDReport.generate(sw, dataset, null, List.of(Value.SUM), SystemFilters.AS, SystemFilters.HS, SystemFilters.JS,
				SystemFilters.LS, SystemFilters.NS, SystemFilters.PS, SystemFilters.WS);
		KDReport.writeCSV("reports/valueByRegion.csv", sw);
		KDReport.writeAreaGraph("reports/valueByRegionStacked.png", dataset);
		KDReport.writeLineGraph("reports/valueByRegionLine.png", dataset);
	}
}
