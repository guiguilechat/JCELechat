package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer;

import java.awt.Color;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService.KillStats;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.dto.NamedTypelist;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills.AggregPeriod;
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

	final private KillService killService;

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
	    @RequestParam Optional<String> aggregate) {
		TypeFiltering typeFilter = TypeFiltering.of(filterBy);
		NamedTypelist resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param " + filter + " should be a number");
		}
		String timeOrder = order.orElse("desc");
		AggregPeriod ap = AggregPeriod.by(aggregate);
		List<KillStats> stats = ap.stats(resolved.typeIds(), killService);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypesKillsStats(resolved.typeIds(), timeOrder, stats, ap),
				accept);
	}

	@Operation(summary = "type kills chart", description = "create a chart of the kills of an type")
	@Transactional
	@GetMapping("/{filterBy}/{filter}/chart")
	public void chartStatsByVictimType(
	    @PathVariable @Parameter(description = "what is the filter : gi, gn, ti, tn for Group/Type Id/Name") String filterBy,
	    @PathVariable @Parameter(description = "id or name to filter") String filter,
			HttpServletResponse response,
	    @RequestParam @Parameter(description = "period to aggregate over : day, week, month(default), year") Optional<String> aggregate,
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
		AggregPeriod ap = AggregPeriod.by(aggregate);
		List<KillStats> stats = typeFilter == TypeFiltering.ERROR ? Collections.emptyList()
				: ap.stats(resolved.typeIds(), killService);
		JFreeChart chart = drawChart(stats,
		    "daily kills of " + resolved.name() + ", aggregated by " + ap.name().toLowerCase(),
		    ap);
		RestControllerHelper.addResponseJFreeChart(response, chart, accept);
	}

	static JFreeChart drawChart(List<KillStats> stats, String title, AggregPeriod by) {
		TimeSeries medianValueSeries = new TimeSeries("median value");
		TimeSeries avgValueSeries = new TimeSeries("average value");
		TimeSeries minValueSeries = new TimeSeries("min value");
		TimeSeries qttySeries = new TimeSeries("daily kills");
		for (KillStats stat : stats) {
			OffsetDateTime odt = stat.periodStart().atOffset(ZoneOffset.UTC);
			RegularTimePeriod period = by.of(odt);
			medianValueSeries.add(period, stat.medianIskLost() / 1000000);
			avgValueSeries.add(period, stat.totalIskLost() / 1000000 / stat.nbKills());
			minValueSeries.add(period, stat.minIskLost() / 1000000);
			int periodDays = by.periodDays(odt);
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
			XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(false,
					true);
			plot.setRenderer(0, renderer0);
			plot.mapDatasetToRangeAxis(0, 0);
		}

		{
			NumberAxis qttyAxis = new NumberAxis("avg kills per day");
			plot.setRangeAxis(1, qttyAxis);
			TimeSeriesCollection qttyColl = new TimeSeriesCollection(qttySeries);
			plot.setDataset(1, qttyColl);
			XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false,
					true);
			plot.setRenderer(1, renderer1);
			plot.mapDatasetToRangeAxis(1, 1);
		}

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
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
			@RequestParam Optional<String> period) {
		TypeFiltering typeFilter = TypeFiltering.of(filterBy);
		NamedTypelist resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param " + filter + " should be a number");
		}
		String timeOrder = time.orElse("desc").toLowerCase();
		AggregPeriod aggregPeriod = AggregPeriod.by(period);
		Map<Integer, Type> typeId2Type = typeService.byId(resolved.typeIds()).stream()
		    .collect(Collectors.toMap(Type::getId, t -> t));
		Map<String, TypesKillsStats> statsByType = resolved.typeIds().parallelStream().collect(
		    Collectors.toMap(
		        typeId -> typeId2Type.get(typeId).name(),
						typeId -> new TypesKillsStats(List.of(typeId), timeOrder, aggregPeriod.stats(List.of(typeId), killService), aggregPeriod)));
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
	    @RequestParam @Parameter(description = "period to aggregate over : day, week, month(default), year") Optional<String> aggregate,
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
		AggregPeriod ap = AggregPeriod.by(aggregate);
		Map<Integer, Type> typeId2Type = typeService.byId(resolved.typeIds()).stream()
		    .collect(Collectors.toMap(Type::getId, t -> t));
		Map<String, List<KillStats>> statsByType = resolved.typeIds().parallelStream().collect(
		    Collectors.toMap(
		        typeId -> typeId2Type.get(typeId).name(),
						typeId -> ap.stats(List.of(typeId), killService)));
		JFreeChart chart = drawChart(statsByType,
		    det.legend + " for " + resolved.name() + ", aggregated by " + ap.name().toLowerCase(), ap,
				det);
		RestControllerHelper.addResponseJFreeChart(response, chart, accept);
	}

	private JFreeChart drawChart(Map<String, List<KillStats>> statsByType, String title, AggregPeriod by,
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
					OffsetDateTime dat = stat.periodStart().atOffset(ZoneOffset.UTC);
					RegularTimePeriod period = by.of(dat);
					totalIskLostSeries.add(period, det.extract(stat, by));
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
