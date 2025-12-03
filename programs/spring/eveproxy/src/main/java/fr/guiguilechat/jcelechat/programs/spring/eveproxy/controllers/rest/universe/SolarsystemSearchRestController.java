package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.selectors.SystemSelectorId;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.selectors.SystemSelectorName;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemActivityType;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemStatisticsService;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.aggregate.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.aggregate.SystemDateActivity;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.ChartTheme;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe/solarsystem/search")
@RequiredArgsConstructor
public class SolarsystemSearchRestController {

	final private SolarSystemService solarSystemService;

	final private SystemStatisticsService systemStatisticsService;

	private static final int DEFAULT_DAYS = 30;

	@GetMapping("/stats/name/{selector}/chart")
	public void chartActivityName(
			@PathVariable SystemSelectorName selector,
			HttpServletResponse response,
			@RequestParam Optional<SystemActivityType> left,
			@RequestParam Optional<SystemActivityType> right,
			@RequestParam List<String> names,
			@RequestParam Optional<Integer> days,
			@RequestParam @Parameter(description = "theme to use for the chart color. Can be a number (0x123, 547 , etc) or a racial style(from the game styling)") Optional<String> theme,
			@RequestParam Optional<String> accept,
			@RequestParam Optional<DateAggregation> aggregate,
			@RequestParam Optional<Integer> w,
			@RequestParam Optional<Integer> h)
			throws IOException {

		if ((left == null || left.isEmpty()) && (right == null || right.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "need at least left or right");
		}
		List<Integer> sids = solarSystemService.selectNames(selector, names);
		Map<Integer, String> sids2Names = solarSystemService.namesForIds(sids);
		Instant since = RestControllerHelper.sinceDefault(days, DEFAULT_DAYS);
		DateAggregation aggreg = aggregate != null && aggregate.isPresent() ? aggregate.get()
				: deduceAggregation(ChronoUnit.HOURS.between(since, Instant.now()),
				sids.size());
		Map<Integer, List<SystemDateActivity>> leftValues = null;
		if (left.isPresent()) {
			leftValues = systemStatisticsService.activities(sids, left.get(), aggreg, since);
		}
		Map<Integer, List<SystemDateActivity>> rightValues = null;
		if (right.isPresent()) {
			rightValues = systemStatisticsService.activities(sids, right.get(), aggreg, since);
		}
		ChartTheme ct = ChartTheme.forName(theme);
		JFreeChart chart = drawActivityChart(sids2Names, left, leftValues, right, rightValues, ct, aggreg);
		RestControllerHelper.addResponseJFreeChart(response, chart, accept,
				w == null || w.isEmpty() ? 2000 : w.get(),
				h == null || h.isEmpty() ? 1000 : h.get());
		RestControllerHelper.setResponseTitle(response, chart.getTitle().getText());
	}

	private DateAggregation deduceAggregation(long hours, int systems) {
		long hourlyPoints = hours * systems;
		long targetPoints = 500;
		if (hourlyPoints <= targetPoints) {
			return DateAggregation.hourly;
		}
		if (hourlyPoints <= targetPoints * 24) {
			return DateAggregation.daily;
		}
		if (hourlyPoints <= targetPoints * 24 * 7) {
			return DateAggregation.weekly;
		}
		return DateAggregation.monthly;
	}

	private JFreeChart drawActivityChart(Map<Integer, String> sids2Names,
			Optional<SystemActivityType> left,
			Map<Integer, List<SystemDateActivity>> leftValues,
			Optional<SystemActivityType> right,
			Map<Integer, List<SystemDateActivity>> rightValues,
			ChartTheme theme,
			DateAggregation aggreg) {
		LinkedHashMap<Integer, String> sortedSId2Name = new LinkedHashMap<>();
		sids2Names.entrySet().stream().sorted(Comparator.comparing(Entry::getValue))
				.forEach(e -> sortedSId2Name.put(e.getKey(), e.getValue()));
		List<Entry<Integer, String>> idNameList = sortedSId2Name.entrySet().stream().toList();
		Color bgColor = theme.backGroundColor();
		Color textColor = theme.textColor();

		XYPlot plot = new XYPlot();
		plot.setBackgroundPaint(bgColor);
		DateAxis timeAxis = new DateAxis(null);
		timeAxis.setDateFormatOverride(new SimpleDateFormat("YY-MM-dd"));
		timeAxis.setTickLabelPaint(textColor);
		timeAxis.setLabelPaint(textColor);
		timeAxis.setLowerMargin(0.02);
		timeAxis.setUpperMargin(0.01);
		plot.setDomainAxis(timeAxis);

		if (left != null && left.isPresent()) {
			SystemActivityType leftActivity = left.get();
			List<Color> leftColors = theme.firstAxisColor(sids2Names.size());

			XYLineAndShapeRenderer leftRenderer = new XYLineAndShapeRenderer(false, true);
			plot.setRenderer(0, leftRenderer);
			NumberAxis leftAxis = new NumberAxis(leftActivity.name());
			leftAxis.setNumberFormatOverride(RestControllerHelper.NUMBER_FORMAT_PRICES);
			leftAxis.setTickLabelPaint(textColor);
			leftAxis.setLabelPaint(textColor);
			plot.setRangeAxis(0, leftAxis);
			TimeSeriesCollection leftCollection = new TimeSeriesCollection();
			plot.setDataset(0, leftCollection);
			plot.mapDatasetToRangeAxis(0, 0);
			for (int seriesIndex = 0; seriesIndex < idNameList.size(); seriesIndex++) {
				if (seriesIndex >= leftColors.size()) {
					continue;
				}
				int sysId = idNameList.get(seriesIndex).getKey();
				List<SystemDateActivity> values = leftValues.get(sysId);
				if (values == null) {
					continue;
				}
				String sysName = idNameList.get(seriesIndex).getValue();
				TimeSeries series = new TimeSeries(leftActivity.name() + " " + sysName);
				leftRenderer.setSeriesPaint(seriesIndex, leftColors.get(seriesIndex));
				for (SystemDateActivity value : values) {
					RegularTimePeriod period = RestControllerHelper.convert(aggreg, value.date());
					series.add(period, value.activity());
				}
				leftCollection.addSeries(series);
				leftRenderer.setSeriesLinesVisible(leftCollection.getSeriesCount() - 1, true);
				leftRenderer.setSeriesShapesVisible(leftCollection.getSeriesCount() - 1, true);
			}
		}

		if (right != null && right.isPresent()) {
			SystemActivityType rightActivity = right.get();
			List<Color> rightColors = theme.secondAxisColor(sids2Names.size());
			boolean flipDirection = left != null && left.isPresent() && idNameList.size() > 3;
			XYBarRenderer rightRenderer = new ClusteredXYBarRenderer();
			rightRenderer.setBarPainter(new StandardXYBarPainter());
			rightRenderer.setShadowVisible(false);
			rightRenderer.setMargin(0.1);
			plot.setRenderer(1, rightRenderer);
			NumberAxis rightAxis = new NumberAxis(rightActivity.name());
			rightAxis.setInverted(flipDirection);
			rightAxis.setNumberFormatOverride(RestControllerHelper.NUMBER_FORMAT_PRICES);
			rightAxis.setTickLabelPaint(textColor);
			rightAxis.setLabelPaint(textColor);
			plot.setRangeAxis(1, rightAxis);
			TimeSeriesCollection rightCollection = new TimeSeriesCollection();
			plot.setDataset(1, rightCollection);
			plot.mapDatasetToRangeAxis(1, 1);
			for (int seriesIndex = 0; seriesIndex < idNameList.size(); seriesIndex++) {
				if (seriesIndex >= rightColors.size()) {
					continue;
				}
				int sysId = idNameList.get(seriesIndex).getKey();
				List<SystemDateActivity> values = rightValues.get(sysId);
				if (values == null) {
					continue;
				}
				String sysName = idNameList.get(seriesIndex).getValue();
				TimeSeries series = new TimeSeries(rightActivity.name() + " " + sysName);
				rightRenderer.setSeriesPaint(seriesIndex, rightColors.get(seriesIndex));
				for (SystemDateActivity value : values) {
					RegularTimePeriod period = RestControllerHelper.convert(aggreg, value.date());
					series.add(period, value.activity());
				}
				rightCollection.addSeries(series);
			}
		}

		JFreeChart chart = new JFreeChart(makeChartTitle(left, right, aggreg), JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		chart.setBackgroundPaint(bgColor);
		chart.getTitle().setPaint(textColor);
		chart.getLegend().setItemPaint(textColor);
		chart.getLegend().setBackgroundPaint(bgColor);
		chart.getLegend().setPosition(RectangleEdge.TOP);

		return chart;
	}

	protected String makeChartTitle(
			Optional<SystemActivityType> left,
			Optional<SystemActivityType> right,
			DateAggregation aggreg) {
		String ret = "";
		if (left != null && left.isPresent()) {
			ret = left.get().name();
		}
		if (right != null && right.isPresent()) {
			ret = (ret == null ? "" : ret + ", ") + right.get().name();
		}
		ret += " aggregated " + aggreg.name();
		return ret;
	}

	@Operation(summary = "search activity by names", description = "list aggregated activities for an activity type and searched systems")
	@GetMapping("/name/{selector}/stats/{activity}/{aggreg}")
	public ResponseEntity<List<SystemDateActivity>> groupActivityNames(
			@PathVariable @Parameter(description = "how to deduce system ids from a name") SystemSelectorName selector,
			@PathVariable @Parameter(description = "selected activity") SystemActivityType activity,
			@PathVariable @Parameter(description = "period to aggregte activity over") DateAggregation aggreg,
			@RequestParam @Parameter(description = "(comma separated) names to transform into system ids. Example : jita,Amarr,doDixiE") List<String> names,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "days to limit the search, default 30") Optional<Integer> days) {
		return RestControllerHelper.makeResponse(
				systemStatisticsService.groupActivities(
						solarSystemService.selectNames(selector, names),
						activity,
						aggreg,
						RestControllerHelper.sinceDefault(days, 30)),
				accept);
	}

	@Operation(summary = "search activity by ids", description = "list aggregated activities for an activity type and searched systems")
	@GetMapping("/id/{selector}/stats/{activity}/{aggreg}")
	public ResponseEntity<List<SystemDateActivity>> groupActivityIds(
			@PathVariable @Parameter(description = "how to deduce system ids from an id") SystemSelectorId selector,
			@PathVariable @Parameter(description = "selected activity") SystemActivityType activity,
			@PathVariable @Parameter(description = "period to aggregte activity over") DateAggregation aggreg,
			@RequestParam @Parameter(description = "(comma separated) ids to transform into system ids") List<Integer> ids,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "days to limit the search, default 30") Optional<Integer> days) {
		return RestControllerHelper.makeResponse(
				systemStatisticsService.groupActivities(
						solarSystemService.selectIds(selector, ids),
						activity,
						aggreg,
						RestControllerHelper.sinceDefault(days, 30)),
				accept);
	}

}
