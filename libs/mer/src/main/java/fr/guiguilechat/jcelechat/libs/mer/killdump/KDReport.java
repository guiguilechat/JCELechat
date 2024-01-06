package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.awt.BasicStroke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer2;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriod;
import org.jfree.data.time.TimeTableXYDataset;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;

/**
 * generates a report from the parsed killdumps
 */
public class KDReport {

	protected static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM");

	/**
	 * parses the killdumps, filter them, aggregates per month, then apply
	 * min/max/etc value per predicate over each aggregate
	 *
	 * @param filter
	 *          eliminates the kills before making month aggregation
	 * @param predicates
	 *          the predicates for which to create sub group per month aggregate
	 * @return the csv of the report
	 */
	@SuppressWarnings("unchecked")
	public static void generate(StringWriter sw, TimeTableXYDataset dataset, Predicate<KDEntry> filter,
			List<ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>>> aaxis, Predicate<KDEntry>... predicates) {
		CSVWriter writer = sw == null ? null
				: new CSVWriter(sw, '\t', ICSVWriter.DEFAULT_QUOTE_CHARACTER, ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
						ICSVWriter.DEFAULT_LINE_END);

		if (sw != null) {
			List<String> header = new ArrayList<>();
			header.add("date");
			header.add("total");
			if(predicates!=null) {
				for(Predicate<KDEntry> pr : predicates) {
					for (ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> axis : aaxis) {
						header.add(pr.toString() + "_" + axis.toString());
					}
				}
			}
			writer.writeNext(header.toArray(String[]::new), false);
		}

		KDParser parser = new KDParser();
		parser.byMonth(filter).entrySet().stream().sorted(Comparator.comparing(e->e.getKey())).forEach(e->{
			List<String> row = sw == null ? null : new ArrayList<>();
			List<KDEntry> values = e.getValue();
			if (row != null) {
				row.add(e.getKey());
				row.add("" + values.size());
			}
			Date dateStart = null, dateEnd = null;
			try {
				dateStart = SDF.parse(e.getKey());
				dateEnd = DateUtils.addMonths(dateStart, 1);
			} catch (ParseException e1) {
				throw new UnsupportedOperationException(e1);
			}
			TimePeriod period = new SimpleTimePeriod(dateStart, dateEnd);
			if (predicates != null) {
				for (Predicate<KDEntry> pr : predicates) {
					List<KDEntry> filtered = values.stream().filter(pr).collect(Collectors.toList());
					for (ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> axis : aaxis) {
						double val = axis.applyAsDouble(filtered, values);
						if (sw != null) {
							if (val == (int) val) {
								row.add("" + (int) val);
							} else {
								row.add(String.format("%.2f", val));
							}
						}
						if (dataset != null) {
							dataset.add(period, val, pr.toString() + "_" + axis.toString());
						}
					}
				}
			}
			if (sw != null) {
				writer.writeNext(row.toArray(String[]::new), false);
			}

		});
	}

	@SuppressWarnings("unchecked")
	public static void generate(StringWriter sw, Predicate<KDEntry> filter, Predicate<KDEntry>... predicates) {
		generate(sw, null, filter, List.of(Count.AGG, Count.PCT, Value.SUM, Value.PCT), predicates);
	}

	public static void writeCSV(String fileName, StringWriter csv) {
		File out = new File(fileName);
		out.getParentFile().mkdirs();
		try (FileWriter writer = new FileWriter(out)) {
			writer.write(csv.toString());
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public static void writeAreaGraph(String fileName, TimeTableXYDataset dataset) {
		DateAxis xAxis = new DateAxis();
		xAxis.setAutoRange(true);
		xAxis.setLowerMargin(0.0);
		xAxis.setUpperMargin(0.0);
		xAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM"));

		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRangeIncludesZero(true);
		yAxis.setAutoRange(true);
		yAxis.setLowerMargin(0.0);
		yAxis.setUpperMargin(0.0);

		StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2();
		renderer.setOutline(true);

		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setRangeAxis(yAxis); // forces recalculation of the axis range

		JFreeChart chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		try {
			File out = new File(fileName);
			out.getParentFile().mkdirs();
			ChartUtils.saveChartAsPNG(out, chart, 1600, 900);
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public static void writeLineGraph(String fileName, TimeTableXYDataset dataset) {
		ValueAxis timeAxis = new DateAxis();
		timeAxis.setLowerMargin(0.0); // reduce the default margins
		timeAxis.setUpperMargin(0.0);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRangeIncludesZero(true);
		yAxis.setAutoRange(true);
		yAxis.setLowerMargin(0.0);
		yAxis.setUpperMargin(0.0);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
		renderer.setAutoPopulateSeriesStroke(false);
		renderer.setDefaultStroke(new BasicStroke(3.0f));

		XYPlot plot = new XYPlot(dataset, timeAxis, yAxis, null);
		plot.setRenderer(renderer);

		JFreeChart chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		try {
			File out = new File(fileName);
			out.getParentFile().mkdirs();
			ChartUtils.saveChartAsPNG(out, chart, 1600, 900);
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void makeReport(String reportName, Predicate<KDEntry> filter,
			List<ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>>> eval, Predicate<KDEntry>... columns) {
		StringWriter sw = new StringWriter();
		TimeTableXYDataset dataset = new TimeTableXYDataset();
		generate(sw, dataset, filter, eval, columns);
		writeCSV("reports/" + reportName + ".csv", sw);
		writeAreaGraph("reports/" + reportName + "Stacked.png", dataset);
		writeLineGraph("reports/" + reportName + "Line.png", dataset);
		System.out.println(reportName);
	}


}
