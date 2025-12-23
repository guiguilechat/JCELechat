package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer;

import java.awt.Color;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
	@GetMapping("/{filterBy}/{filter}/chart")
	public void chartStatsByVictimType(
	    @PathVariable @Parameter(description = "what is the filter : gi, gn, ti, tn for Group/Type Id/Name") String filterBy,
	    @PathVariable @Parameter(description = "id or name to filter") String filter,
			HttpServletResponse response,
			@RequestParam @Parameter(description = "period to aggregate over") Optional<KillsAggregation> aggregation,
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
		KillsAggregation ka = aggregation.orElse(KillsAggregation.MONTHLY);
		List<KillStats> stats = typeFilter == TypeFiltering.ERROR ? Collections.emptyList()
				: killService.stats(ka, resolved.typeIds());
		System.err.println("first kill stats is " + stats.get(0));
		JFreeChart chart = drawChart(stats,
				"daily kills of " + resolved.name() + ", aggregated " + ka.legend(),
				ka);
		RestControllerHelper.addResponseJFreeChart(response, chart, accept);
	}

	static RegularTimePeriod period(KillsAggregation ka, Instant start) {
		OffsetDateTime odt = start.atOffset(ZoneOffset.UTC);
		return switch (ka) {
		case DAYLY -> new Day(odt.getDayOfMonth(), odt.getMonthValue(), odt.getYear());
		case MONTHLY -> new Month(odt.getMonthValue(), odt.getYear());
		case WEEKLY -> new Week(odt.get(ChronoField.ALIGNED_WEEK_OF_YEAR), odt.getYear());
		case YEARLY -> new Year(odt.getYear());
		default -> throw new NullPointerException("case " + ka + " not hanlded");
		};

	}

	static JFreeChart drawChart(List<KillStats> stats, String title, KillsAggregation ka) {
		TimeSeries avgValueSeries = new TimeSeries("average value");
		TimeSeries minValueSeries = new TimeSeries("min destroyed");
		TimeSeries qttySeries = new TimeSeries("daily kills");
		for (KillStats stat : stats) {
			RegularTimePeriod period = period(ka, stat.periodStart());
			avgValueSeries.add(period, stat.avgFitPrice() / 1000000);
			minValueSeries.add(period, stat.minIskDestroyed().doubleValue() / 1000000);
			qttySeries.add(period, stat.killPerDay());
		}

		XYPlot plot = new XYPlot();
		DateAxis timeAxis = new DateAxis(null);
		timeAxis.setLowerMargin(0.02);
		timeAxis.setUpperMargin(0.02);
		plot.setDomainAxis(timeAxis);

		{
			NumberAxis qttyAxis = new NumberAxis("kills");
			plot.setRangeAxis(0, qttyAxis);
			TimeSeriesCollection qttyColl = new TimeSeriesCollection(qttySeries);
			plot.setDataset(0, qttyColl);
			XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(false,
					true);
			plot.setRenderer(0, renderer0);
			plot.mapDatasetToRangeAxis(0, 0);
		}

		{
			NumberAxis priceAxis = new NumberAxis("price (M)");
			plot.setRangeAxis(1, priceAxis);
			XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false,
					true);

			TimeSeriesCollection priceCollection = new TimeSeriesCollection(avgValueSeries);
			priceCollection.addSeries(minValueSeries);
			plot.setDataset(1, priceCollection);
			plot.setRenderer(1, renderer1);
			plot.mapDatasetToRangeAxis(1, 1);
		}

		JFreeChart chart = new JFreeChart(
				title,
				JFreeChart.DEFAULT_TITLE_FONT,
				plot,
				true);
		chart.setBackgroundPaint(Color.WHITE);

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
		Map<Integer, Type> typeId2Type = typeService.byId(resolved.typeIds()).stream()
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
		Map<Integer, Type> typeId2Type = typeService.byId(resolved.typeIds()).stream()
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

	private JFreeChart drawChart(Map<String, List<KillStats>> statsByType, String title,
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
