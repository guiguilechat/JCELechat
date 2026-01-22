package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer;

import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillStats;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillStatsService;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillsAggregation;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.dto.NamedTypelist;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills.KillsDetail;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills.TypesKillsStats;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// @Slf4j
@RestController
@RequestMapping("/api/mer/kills")
@RequiredArgsConstructor
public class MerKillsRestController {

	final private KillStatsService killService;

	final private TypeService typeService;

	final private GroupService groupService;

	/**
	 * @param filterBy  method to filter the killed type.
	 * @param filter
	 * @param accept
	 * @param order
	 * @param aggregate
	 * @return
	 */
	@Transactional
	@GetMapping("/{filterBy}/{filter}")
	public ResponseEntity<TypesKillsStats> statsByVictim(
			@PathVariable String filterBy,
			@PathVariable String filter,
			@RequestParam Optional<ACCEPT_TEXT> accept,
	    @RequestParam Optional<String> order,
			@RequestParam Optional<KillsAggregation> aggregate) {
		TypeFiltering typeFilter = TypeFiltering.of(filterBy);
		NamedTypelist resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param " + filter + " should be a number");
		}
		String timeOrder = order.orElse("desc");
		KillsAggregation ka = aggregate.orElse(KillsAggregation.MONTHLY);

		List<KillStats> stats = killService.stats(ka, resolved.typeIds());
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypesKillsStats(resolved.typeIds(), timeOrder, stats, ka),
				accept);
	}

	@Operation(summary = "type kills chart", description = "create a chart of the kills of an type")
	@Transactional
	@GetMapping("/type/{typeId}/chart")
	public void chartVictimTypeId(
			@PathVariable int typeId,
			HttpServletResponse response,
			@RequestParam @Parameter(description = "period to aggregate over") Optional<KillsAggregation> aggregation,
	    @RequestParam @Parameter(description = "format fo chart : png(default), jpg") Optional<String> accept)
	    throws IOException {
		Type t = typeService.ofId(typeId);
		if (t == null) {
			response.sendError(404, "can't resolve " + typeId + " to a type");
			return;
		}
		KillsAggregation ka = aggregation.orElse(KillsAggregation.MONTHLY);
		List<KillStats> stats = killService.stats(ka, List.of(typeId));
		JFreeChart chart = drawChart(stats,
				"kills of " + t.toString() + ", aggregated " + ka.legend(),
				ka);
		RestControllerHelper.addResponseJFreeChart(response, chart, accept);
	}

	public URI chartUri(int typeId, KillsAggregation aggreg, String accept) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "chartVictimTypeId",
						typeId,
						null,
						Optional.ofNullable(aggreg),
						Optional.ofNullable(accept))
				.build()
				.toUri();
	}

	static RegularTimePeriod period(KillsAggregation ka, Instant start) {
		OffsetDateTime odt = start.atOffset(ZoneOffset.UTC);
		return switch (ka) {
		case DAILY -> new Day(odt.getDayOfMonth(), odt.getMonthValue(), odt.getYear());
		case MONTHLY -> new Month(odt.getMonthValue(), odt.getYear());
		case WEEKLY -> new Week(odt.get(ChronoField.ALIGNED_WEEK_OF_YEAR), odt.getYear());
		case YEARLY -> new Year(odt.getYear());
		default -> throw new NullPointerException("case " + ka + " not hanlded");
		};
	}

	static JFreeChart drawChart(List<KillStats> stats, String title, KillsAggregation ka) {
		Color bgCol = new Color(23, 23, 23);
		Color textColor = new Color(150, 160, 150);
		Color qttyCol = new Color(20, 80, 110);
		Color avgFitValCol = new Color(180, 40, 20);
		Color minDestroyedCol = new Color(152, 60, 60);

		TimeSeries avgValueSeries = new TimeSeries("average value");
		TimeSeries minValueSeries = new TimeSeries("min destroyed");
		TimeSeries qttySeries = new TimeSeries("daily kills");

		List<KillStats> sortedStats = new ArrayList<>(stats);
		Collections.sort(sortedStats, Comparator.comparing(KillStats::periodStart));
		KillStats lastStat = null;
		for (KillStats stat : sortedStats) {
			if (lastStat != null && lastStat.periodEnd().isBefore(stat.periodStart())) {
				// a period was missing : add it
				RegularTimePeriod period = period(ka, lastStat.periodEnd());
				avgValueSeries.add(period, lastStat.avgFitPrice() / 1000000);
				minValueSeries.add(period, lastStat.minIskDestroyed().doubleValue() / 1000000);
				qttySeries.add(period, 0.0);
			}
			RegularTimePeriod period = period(ka, stat.periodStart());
			// need addOrUpdate because they can overlap with filler values.
			avgValueSeries.addOrUpdate(period, stat.avgFitPrice() / 1000000);
			minValueSeries.addOrUpdate(period, stat.minIskDestroyed().doubleValue() / 1000000);
			qttySeries.addOrUpdate(period, stat.killPerDay());
			lastStat = stat;
		}
		// need to add an additional period to show the last one
		RegularTimePeriod period = period(ka, lastStat.periodEnd());
		avgValueSeries.add(period, avgValueSeries.getValue(avgValueSeries.getItemCount() - 1));
		minValueSeries.add(period, minValueSeries.getValue(minValueSeries.getItemCount() - 1));
		qttySeries.add(period, qttySeries.getValue(qttySeries.getItemCount() - 1));

		XYPlot plot = new XYPlot();
		plot.setBackgroundPaint(bgCol);
		plot.setDomainGridlinePaint(textColor);
		plot.setRangeGridlinePaint(textColor);

		PeriodAxis xAxis = new PeriodAxis(null);
		xAxis.setLowerMargin(0.02);
		xAxis.setUpperMargin(0.02);
		xAxis.setMajorTickTimePeriodClass(Year.class);
		xAxis.setMinorTickTimePeriodClass(Month.class);
		xAxis.setTickLabelPaint(textColor);
		// change the text color of the domain labels
		PeriodAxisLabelInfo[] lis = Stream.of(xAxis.getLabelInfo())
				.map(li -> new PeriodAxisLabelInfo(
						li.getPeriodClass(),
						li.getDateFormat(),
						li.getPadding(),
						li.getLabelFont(),
						textColor,
						li.getDrawDividers(),
						li.getDividerStroke(),
						li.getDividerPaint()))
				.toArray(PeriodAxisLabelInfo[]::new);
		xAxis.setLabelInfo(lis);
		xAxis.setAxisLinePaint(textColor);
		plot.setDomainAxis(xAxis);

		// price axis
		{
			NumberAxis priceAxis = new NumberAxis("price (M)");
			priceAxis.setTickLabelPaint(textColor);
			priceAxis.setLabelPaint(textColor);
			plot.setRangeAxis(0, priceAxis);
			XYStepRenderer renderer1 = new XYStepRenderer();
			renderer1.setSeriesPaint(0, avgFitValCol);
			renderer1.setSeriesPaint(1, minDestroyedCol);
			TimeSeriesCollection priceCollection = new TimeSeriesCollection(avgValueSeries);
			priceCollection.addSeries(minValueSeries);
			plot.setDataset(0, priceCollection);
			plot.setRenderer(0, renderer1);
			plot.mapDatasetToRangeAxis(0, 0);
		}

		// quantity axis
		{
			NumberAxis qttyAxis = new NumberAxis("kills");
			qttyAxis.setTickLabelPaint(textColor);
			qttyAxis.setLabelPaint(textColor);

			plot.setRangeAxis(1, qttyAxis);
			TimeSeriesCollection qttyColl = new TimeSeriesCollection(qttySeries);
			plot.setDataset(1, qttyColl);
			XYStepRenderer renderer0 = new XYStepRenderer();
			renderer0.setSeriesPaint(0, qttyCol);
			plot.setRenderer(1, renderer0);
			plot.mapDatasetToRangeAxis(1, 1);
		}

		JFreeChart chart = new JFreeChart(
				title,
				JFreeChart.DEFAULT_TITLE_FONT,
				plot,
				true);
		chart.setBackgroundPaint(bgCol);
		chart.getLegend().setBackgroundPaint(bgCol);
		chart.getTitle().setPaint(textColor);
		chart.getLegend().setItemPaint(textColor);

		return chart;
	}

	@Transactional
	@GetMapping("/{filterBy}/{filter}/detail")
	public ResponseEntity<List<Entry<String, TypesKillsStats>>> statsByVictimTypeDetailed(
	    @PathVariable @Parameter(description = "what is the filter : gi, gn, ti, tn for Group/Type Id/Name") String filterBy,
	    @PathVariable @Parameter(description = "id or name to filter") String filter,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "ordering : asc or desc(default)") Optional<String> time,
			@RequestParam Optional<KillsAggregation> period) {
		TypeFiltering typeFilter = TypeFiltering.of(filterBy);
		NamedTypelist resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param " + filter + " should be a number");
		}
		String timeOrder = time.orElse("desc").toLowerCase();
		KillsAggregation ka = period.orElse(KillsAggregation.MONTHLY);
		Map<Integer, Type> typeId2Type = typeService.ofId(resolved.typeIds()).stream()
		    .collect(Collectors.toMap(Type::getId, t -> t));
		Map<String, TypesKillsStats> statsByType = resolved.typeIds().parallelStream().collect(
		    Collectors.toMap(
		        typeId -> typeId2Type.get(typeId).name(),
						typeId -> new TypesKillsStats(List.of(typeId), timeOrder,
								killService.stats(ka, List.of(typeId)), ka)));
		if (timeOrder.equals("asc")) {
			for (TypesKillsStats stats : statsByType.values()) {
				Collections.reverse(stats.stats());
			}
		}
		return RestControllerHelper.makeResponse(
				statsByType.entrySet().stream().filter(e -> !e.getValue().stats().isEmpty())
						.sorted(Comparator.comparing(Entry::getKey)).toList(),
				accept);
	}

	@Operation(summary = "type kills details chart", description = "create a chart of the details kills of an type")
	@Transactional
	@GetMapping("/{filterBy}/{filter}/chart/{detail}")
	public void chartStatsByVictimTypeDetailed(
	    @PathVariable @Parameter(description = "what is the filter : gi, gn, ti, tn for Group/Type Id/Name") String filterBy,
	    @PathVariable @Parameter(description = "id or name to filter") String filter,
	    @PathVariable @Parameter(description = "detail to chart : avg/median/min (M isk value killed per day), kills (avg single daily kills), lost(value destroyed per day) ") String detail,
			HttpServletResponse response,
			@RequestParam @Parameter(description = "period to aggregate over : day, week, month(default), year") Optional<KillsAggregation> aggregate,
	    @RequestParam @Parameter(description = "format fo chart : png(default), jpg") Optional<String> accept)
	    throws IOException {
		TypeFiltering typeFilter = TypeFiltering.of(filterBy);
		NamedTypelist resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			response.sendError(400, "param " + filter + " should be a number");
			return;
		}
		KillsDetail det = KillsDetail.of(detail);
		KillsAggregation ka = aggregate.orElse(KillsAggregation.MONTHLY);
		Map<Integer, Type> typeId2Type = typeService.ofId(resolved.typeIds()).stream()
		    .collect(Collectors.toMap(Type::getId, t -> t));
		Map<String, List<KillStats>> statsByType = resolved.typeIds().parallelStream().collect(
		    Collectors.toMap(
		        typeId -> typeId2Type.get(typeId).name(),
						typeId -> killService.stats(ka, List.of(typeId))));
		JFreeChart chart = drawChart(statsByType,
				det.legend + " for " + resolved.name() + ", aggregated " + ka.legend(), ka,
				det);
		RestControllerHelper.addResponseJFreeChart(response, chart, accept);
	}

	static JFreeChart drawChart(Map<String, List<KillStats>> statsByType, String title,
			KillsAggregation ka,
			KillsDetail det) {
		XYPlot plot = new XYPlot();
		DateAxis timeAxis = new DateAxis(null);
		timeAxis.setLowerMargin(0.02);
		timeAxis.setUpperMargin(0.02);
		plot.setDomainAxis(timeAxis);

		List<Entry<String, List<KillStats>>> sortedStatsList = statsByType.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getKey)).toList();

		{
			NumberAxis valueAxis = new NumberAxis(det.legend);
			plot.setRangeAxis(0, valueAxis);
			TimeSeriesCollection tsc = new TimeSeriesCollection();
			for (Entry<String, List<KillStats>> e : sortedStatsList) {
				if (e.getValue().isEmpty()) {
					continue;
				}
				TimeSeries totalIskLostSeries = new TimeSeries(e.getKey());
				for (KillStats stat : e.getValue()) {
					RegularTimePeriod period = period(ka, stat.periodStart());
					totalIskLostSeries.add(period, det.extract(stat));
				}
				tsc.addSeries(totalIskLostSeries);
			}
			plot.setDataset(0, tsc);
			XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(false,
					true);
			plot.setRenderer(0, renderer0);
			plot.mapDatasetToRangeAxis(0, 0);
		}

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		chart.setBackgroundPaint(Color.WHITE);

		return chart;
	}

}
