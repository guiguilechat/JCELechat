package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService.MonthlyStats;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import jakarta.servlet.http.HttpServletResponse;

// @Slf4j
@RestController
@RequestMapping("/api/mer")
public class MerRestController {

	@Autowired
	private KillService killService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private GroupService groupService;

	static record MonthlyKillsSelection(
			List<Integer> types, String timeSort) {
	}

	static String formatInstantAsYearMonth(Instant instant) {
		OffsetDateTime offsetted = instant.atOffset(ZoneOffset.UTC);
		return new StringBuilder()
				.append(offsetted.get(ChronoField.YEAR))
				.append('-')
				.append(offsetted.get(ChronoField.MONTH_OF_YEAR))
				.toString();
	}

	static final DateTimeFormatter FORMATTER_YM = DateTimeFormatter.ofPattern("YYYY-MM");

	// bug : does not work for some specific timestamp. see corresponding test class
	static String formatInstantAsYearMonth2(Instant instant) {
		return FORMATTER_YM.format(instant.atOffset(ZoneOffset.UTC));
	}

	static record MonthKillStats(String month, long numberKills, double totalMIskLost, double averageMIskLost,
			double medianMIskLost) {

		public MonthKillStats(MonthlyStats source) {
			this(formatInstantAsYearMonth(source.month()),
					source.nbKills(),
					source.totalIskLost() / 1000000,
					source.totalIskLost() / 1000000 / source.nbKills(),
					source.medianIskLost() / 1000000);
		}

	}

	static record TypeMonthlyKills(MonthlyKillsSelection filters, List<MonthKillStats> stats) {

		TypeMonthlyKills(List<Integer> types, String timeSort, List<MonthlyStats> monthlyStats) {
			this(new MonthlyKillsSelection(types, timeSort), monthlyStats.stream().map(MonthKillStats::new).toList());
		}
	}

	@GetMapping("/monthlykills/byVictimTypeId/{typeId}")
	public ResponseEntity<?> monthlyStatsByVictimType(@PathVariable int typeId, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> time) {
		String timeOrder = time.orElse("desc");
		List<Integer> types = List.of(typeId);
		List<MonthlyStats> stats = killService.monthlyStats(types);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypeMonthlyKills(types, timeOrder, stats),
				accept);
	}

	@GetMapping("/monthlykills/byVictimTypeId/{typeId}/chart")
	public void chartStatsByVictimType(@PathVariable int typeId, HttpServletResponse response) throws IOException {
		List<Integer> types = List.of(typeId);
		List<MonthlyStats> stats = killService.monthlyStats(types);
		Type type = typeService.byId(typeId).orElse(null);
		JFreeChart chart = drawChart(stats, "kills of type " + (type == null ? typeId : type.getName()));
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(2000, 1000));
	}

	@GetMapping("/monthlykills/byVictimGroupId/{groupId}")
	public ResponseEntity<?> monthlyStatsByVictimGroup(@PathVariable int groupId, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> time) {
		String timeOrder = time.orElse("desc");
		List<Integer> types = typeService.byGroupId(groupId).stream().map(Type::getTypeId).toList();
		List<MonthlyStats> stats = killService.monthlyStats(types);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypeMonthlyKills(types, timeOrder, stats),
				accept);
	}

	@GetMapping("/monthlykills/byVictimGroupId/{groupId}/chart")
	public void chartStatsByVictimGroup(@PathVariable int groupId, HttpServletResponse response) throws IOException {
		List<Integer> types = typeService.byGroupId(groupId).stream().map(Type::getTypeId).toList();
		List<MonthlyStats> stats = killService.monthlyStats(types);
		Group group = groupService.byId(groupId).orElse(null);
		JFreeChart chart = drawChart(stats, "kills of group " + (group == null ? groupId : group.getName()));
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(2000, 1000));
	}

	static JFreeChart drawChart(List<MonthlyStats> stats, String title) {
		TimeSeries medianValueSeries = new TimeSeries("median value");
		TimeSeries avgValueSeries = new TimeSeries("average value");
		TimeSeries minValueSeries = new TimeSeries("min value");
		TimeSeries qttySeries = new TimeSeries("number");
		for (MonthlyStats stat : stats) {
			OffsetDateTime dat = stat.month().atOffset(ZoneOffset.UTC);
			Month mth = new Month(dat.getMonthValue(), dat.getYear());
			medianValueSeries.add(mth, stat.medianIskLost() / 1000000);
			avgValueSeries.add(mth, stat.totalIskLost() / 1000000 / stat.nbKills());
			minValueSeries.add(mth, stat.minIskLost() / 1000000);
			qttySeries.add(mth, stat.nbKills());
		}

		XYPlot plot = new XYPlot();
		ValueAxis timeAxis = new DateAxis(null);
		timeAxis.setLowerMargin(0.02);
		timeAxis.setUpperMargin(0.02);
		plot.setDomainAxis(timeAxis);

		{
			NumberAxis valueAxis = new NumberAxis("M isk");
			valueAxis.setAutoRangeIncludesZero(true);
			plot.setRangeAxis(0, valueAxis);
			TimeSeriesCollection tsc = new TimeSeriesCollection(avgValueSeries);
			tsc.addSeries(medianValueSeries);
// tsc.addSeries(minValueSeries);
			plot.setDataset(0, tsc);
			XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(true,
					true);
			plot.setRenderer(0, renderer0);
			plot.mapDatasetToRangeAxis(0, 0);
		}

		{
			NumberAxis qttyAxis = new NumberAxis("qtty");
			plot.setRangeAxis(1, qttyAxis);
			TimeSeriesCollection qttyColl = new TimeSeriesCollection(qttySeries);
			plot.setDataset(1, qttyColl);
			XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(true,
					true);
			plot.setRenderer(1, renderer1);
			plot.mapDatasetToRangeAxis(1, 1);
		}

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		chart.setBackgroundPaint(Color.WHITE);

		return chart;
	}

}
