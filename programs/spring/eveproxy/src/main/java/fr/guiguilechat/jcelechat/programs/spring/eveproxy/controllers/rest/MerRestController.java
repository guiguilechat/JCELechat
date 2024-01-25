package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService.KillStats;
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

	static String formatInstantAsYearWeek(Instant instant) {
		OffsetDateTime offsetted = instant.atOffset(ZoneOffset.UTC);
		return new StringBuilder()
				.append(offsetted.get(ChronoField.YEAR))
				.append('w')
				.append(offsetted.get(ChronoField.ALIGNED_WEEK_OF_YEAR))
				.toString();
	}

	static final DateTimeFormatter FORMATTER_YM = DateTimeFormatter.ofPattern("YYYY-MM");

	// bug : does not work for some specific timestamp. see corresponding test class
	static String formatInstantAsYearMonth2(Instant instant) {
		return FORMATTER_YM.format(instant.atOffset(ZoneOffset.UTC));
	}

	static record PeriodKillStats(String period, long numberKills, double totalMIskLost, double averageMIskLost,
			double medianMIskLost) {

		public PeriodKillStats(KillStats source, boolean month) {
			this(month ? formatInstantAsYearMonth(source.period()) : formatInstantAsYearWeek(source.period()),
					source.nbKills(),
					source.totalIskLost() / 1000000,
					source.totalIskLost() / 1000000 / source.nbKills(),
					source.medianIskLost() / 1000000);
		}

	}

	static record TypesKillsStats(MonthlyKillsSelection filters, List<PeriodKillStats> stats) {

		TypesKillsStats(List<Integer> types, String timeSort, List<KillStats> periodStats, boolean month) {
			this(new MonthlyKillsSelection(types, timeSort),
					periodStats.stream().map(s -> new PeriodKillStats(s, month)).toList());
		}
	}

	@GetMapping("/kills/byVictimTypeId/{typeId}")
	public ResponseEntity<?> statsByVictimType(@PathVariable int typeId, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> time,
			@RequestParam Optional<String> by) {
		List<Integer> types = List.of(typeId);
		return listStats(types, time, accept, by);
	}


	@GetMapping("/kills/byVictimGroupId/{groupId}")
	public ResponseEntity<?> statsByVictimGroup(@PathVariable int groupId, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> time, @RequestParam Optional<String> by) {
		List<Integer> types = typeService.byGroupId(groupId).stream().map(Type::getTypeId).toList();
		return listStats(types, time, accept, by);
	}

	ResponseEntity<?> listStats(List<Integer> types, Optional<String> time, Optional<String> accept,
			Optional<String> by) {
		String timeOrder = time.orElse("desc");
		boolean monthly = !"week".equalsIgnoreCase(by.orElse("month"));
		List<KillStats> stats = monthly ? killService.monthlyStats(types) : killService.weeklyStats(types);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypesKillsStats(types, timeOrder, stats, monthly),
				accept);
	}

	@GetMapping("/kills/byVictimTypeId/{typeId}/chart")
	public void chartStatsByVictimType(@PathVariable int typeId, HttpServletResponse response,
			@RequestParam Optional<String> by) throws IOException {
		List<Integer> types = List.of(typeId);
		Type type = typeService.byId(typeId).orElse(null);
		String title = "kills of type " + (type == null ? typeId : type.getName());
		addChartResponse(response, types, by, title);
	}

	@GetMapping("/kills/byVictimGroupId/{groupId}/chart")
	public void chartStatsByVictimGroup(@PathVariable int groupId, HttpServletResponse response,
			@RequestParam Optional<String> by) throws IOException {
		List<Integer> types = typeService.byGroupId(groupId).stream().map(Type::getTypeId).toList();
		Group group = groupService.byId(groupId).orElse(null);
		String title = "kills of group " + (group == null ? groupId : group.getName());
		addChartResponse(response, types, by, title);
	}

	void addChartResponse(HttpServletResponse response, List<Integer> types, Optional<String> by, String title)
			throws IOException {
		boolean monthly = !"week".equalsIgnoreCase(by.orElse("month"));
		List<KillStats> stats = monthly ? killService.monthlyStats(types) : killService.weeklyStats(types);
		JFreeChart chart = drawChart(stats, title, monthly);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(2000, 1000));
	}

	static JFreeChart drawChart(List<KillStats> stats, String title, boolean monthly) {
		TimeSeries medianValueSeries = new TimeSeries("median value");
		TimeSeries avgValueSeries = new TimeSeries("average value");
		TimeSeries minValueSeries = new TimeSeries("min value");
		TimeSeries qttySeries = new TimeSeries("daily kills");
		for (KillStats stat : stats) {
			OffsetDateTime dat = stat.period().atOffset(ZoneOffset.UTC);
			RegularTimePeriod period = monthly ? new Month(dat.getMonthValue(), dat.getYear())
					: new Week(dat.get(ChronoField.ALIGNED_WEEK_OF_YEAR), dat.getYear());
			medianValueSeries.add(period, stat.medianIskLost() / 1000000);
			avgValueSeries.add(period, stat.totalIskLost() / 1000000 / stat.nbKills());
			minValueSeries.add(period, stat.minIskLost() / 1000000);
			int periodDays = monthly ? (int) ChronoUnit.DAYS.between(dat, dat.plusMonths(1)) : 7;
			qttySeries.add(period, 1.0 / periodDays * stat.nbKills());
		}

		XYPlot plot = new XYPlot();
		DateAxis timeAxis = new DateAxis(null);
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
			NumberAxis qttyAxis = new NumberAxis("avg kills per day");
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
