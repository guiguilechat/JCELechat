package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market;

import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.LegendPosition;
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

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade.ContractFacadeBpc;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade.ContractFacadeBpo;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade.ContractMarketAggregator;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.HistoryLineService.PriceVolumeAcc;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.HistoryLineService.WeightStrategy;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.SlidingAverage;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.ChartTheme;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.dto.TypeData;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.history.DailyExchanges;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.history.HistoryAggreg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/market/history")
@RequiredArgsConstructor
public class MarketHistoryRestController {

	final private ContractFacadeBpc contractFacadeBpc;

	final private ContractFacadeBpo contractFacadeBpo;

	final private ContractMarketAggregator contractMarketAggregator;

	final private HistoryLineService historyLineService;

	private final TypeService typeService;

	private final int NB_STEPS = 10;

	@GetMapping("/byRegionId/{regionId}/byTypeId/{typeId}/byWeightName/{weightname}/daily")
	public ResponseEntity<DailyExchanges> byRegionByType(@PathVariable int regionId, @PathVariable int typeId,
			@PathVariable String weightname,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		WeightStrategy weighter = WeightStrategy.of(weightname);
		List<PriceVolumeAcc> priceVolumes = historyLineService.groupPrices(regionId, typeId, weighter, NB_STEPS);
		return RestControllerHelper.makeResponse(
				DailyExchanges.of(new HistoryAggreg(typeId, regionId, weighter.toString(), NB_STEPS), priceVolumes),
				accept);
	}

	@GetMapping("/byTypeId/{typeId}")
	@Transactional
	public ResponseEntity<TypeData<List<AggregatedHL>>> byType(
			@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam Optional<Integer> days) {
		Type type = typeService.ofId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		Instant from = Instant.now().minus(days.orElse(365 * 10) + 1, ChronoUnit.DAYS);
		List<AggregatedHL> data = contractMarketAggregator.aggregatedSales(typeId, from);
		return RestControllerHelper.makeResponse(TypeData.of(type, data),
				accept);
	}

	public enum ChartBuilder {
		jfreechart,
		xchart;

		public static ChartBuilder orDefault(Optional<ChartBuilder> param) {
			ChartBuilder orDefault = jfreechart;
			if (param == null || param.isEmpty()) {
				return orDefault;
			}
			return param.get();
		}
	}

	/**
	 * @param typeId   type id we want the history of
	 * @param response http response to add data into.
	 * @param accept   format of the image. values depend on the builder chosen.
	 * @param builder  builder for the chart. Can be "jfreechart" or "xchart"
	 *                   (default) .
	 * @throws IOException
	 */
	@Operation(summary = "type sales chart", description = "create a chart of the sales of an item")
	@Transactional // otherwise bug from retrieving data in pg
	@GetMapping("/byTypeId/{typeId}/chart")
	public void chartHistoryByType(
			@PathVariable @Parameter(description = "type id we want to chart the history of") int typeId,
			HttpServletResponse response,
			@RequestParam @Parameter(description = "format of the image. values depend on the builder chosen. typically jpg, or png (ignore case)") Optional<String> accept,
			@RequestParam @Parameter(description = "builder for the chart. Can be \"jfreechart\" or \"xchart\"(default)") Optional<ChartBuilder> builder,
			@RequestParam @Parameter(description = "theme to use for the chart color. Can be a number (0x123, 547 , etc) or a racial style(from the game styling)") Optional<String> theme,
			@RequestParam @Parameter(description = "list of days to use to draw the average price") Optional<List<Integer>> averageDays,
			@RequestParam @Parameter(description = "maximum days to show. Default 10 years") Optional<Integer> days)
					throws IOException {
		Type type = typeService.ofId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		Instant from = Instant.now().minus(days.orElse(365 * 10) + 1, ChronoUnit.DAYS);
		List<AggregatedHL> fetchedData = contractMarketAggregator.aggregatedSales(typeId, from);
		if (fetchedData.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " has no sale record");
		}
		String title = "universe sales of " + type.nameId()
				+ (days.isPresent() ? " over the last " + days.get() + " days" : "");
		RestControllerHelper.setResponseTitle(response, type.name());

		ChartTheme ct = ChartTheme.forName(theme.orElse(null));
		switch (ChartBuilder.orDefault(builder)) {
		case jfreechart:
			RestControllerHelper.addResponseJFreeChart(response,
					drawJFreeChart(fetchedData, averageDays, title, "items", ct),
					accept);
			break;
		case xchart:
			RestControllerHelper.addResponseXChart(response, drawXChart(fetchedData, title), accept);
			break;
		default:
			throw new UnsupportedOperationException("unsupported case " + ChartBuilder.orDefault(builder));
		}
	}

	/**
	 * @param typeId   type id we want the history of
	 * @param response http response to add data into.
	 * @param accept   format of the image. values depend on the builder chosen.
	 * @param builder  builder for the chart. Can be "jfreechart" or "xchart"
	 *                   (default) .
	 * @throws IOException
	 */
	@Operation(summary = "modified type sales chart", description = "create a chart of the sales of a modified item")
	@Transactional // otherwise bug from retrieving data in pg
	@GetMapping("/byTypeId/{typeId}/copy/{copy}/me/{me}/te/{te}/chart")
	public void chartContractHistoryByType(
			@PathVariable @Parameter(description = "type id we want to chart the history of") int typeId,
			@PathVariable @Parameter(description = "true to only consider BPC, false to only conser BPO") boolean copy,
			@PathVariable @Parameter(description = "me value to consider") int me,
			@PathVariable @Parameter(description = "te value to consider") int te,
			HttpServletResponse response,
			@RequestParam @Parameter(description = "format of the image. values depend on the builder chosen. typically jpg, or png (ignore case)") Optional<String> accept,
			@RequestParam @Parameter(description = "builder for the chart. Can be \"jfreechart\" or \"xchart\"(default)") Optional<ChartBuilder> builder,
			@RequestParam @Parameter(description = "theme to use for the chart color. Can be a number (0x123, 547 , etc) or a racial style(from the game styling)") Optional<String> theme,
			@RequestParam @Parameter(description = "list of days to use to draw the average price") Optional<List<Integer>> averageDays)
					throws IOException {
		log.trace("start page creation for type " + typeId);
		Type type = typeService.ofId(typeId);
		Instant from = Instant.EPOCH;
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		log.trace("requesting history aggregates for type " + typeId);
		List<AggregatedHL> fetchedData = copy ? contractFacadeBpc.aggregatedSales(typeId, from, me, te)
				: contractFacadeBpo.aggregatedSales(typeId, from, me, te);
		log.trace(" received {} history aggregates for type {}",
				fetchedData.size(),
				typeId);
		if (fetchedData.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " has no sale record");
		}

		String title = "public contract sales of " + type.name() + "(" + type.getId() + ")" + (copy ? " (CP)" : "")
				+ " " + me + "/" + te;
		RestControllerHelper.setResponseTitle(response, type.name() + " " + (copy ? " (CP)" : "")
				+ " " + me + "/" + te);

		ChartTheme ct = ChartTheme.forName(theme.orElse(null));
		switch (ChartBuilder.orDefault(builder)) {
		case jfreechart:
			RestControllerHelper.addResponseJFreeChart(response,
					drawJFreeChart(fetchedData, averageDays, title, copy ? "runs" : "items", ct),
					accept);
			break;
		case xchart:
			RestControllerHelper.addResponseXChart(response, drawXChart(fetchedData, title), accept);
			break;
		default:
			throw new UnsupportedOperationException("unsupported case " + ChartBuilder.orDefault(builder));
		}
		log.trace(" completed page creation for type " + typeId);

	}

	private static final List<Integer> DEFAULT_AVERAGE_DAYS = List.of(7, 30);

	/**
	 * @param sortedData   the list of history data to place on the chart
	 * @param averageDays  additional average days to show. Defaults to
	 *                     {@link #DEFAULT_AVERAGE_DAYS}
	 * @param title        chart title
	 * @param quantityUnit "unit" for stack items or "runs" for bpc
	 * @param theme        color theme of the chart
	 * @return the chart of the history
	 */
	private JFreeChart drawJFreeChart(List<AggregatedHL> sortedData, Optional<List<Integer>> averageDays, String title,
			String quantityUnit,
			ChartTheme theme) {
		Color bgColor = theme.backGroundColor();
		Color textColor = theme.textColor();
		List<Integer> requestedCumulatedDays = averageDays.orElse(DEFAULT_AVERAGE_DAYS);
		// first color is for immediate, next colors are for cumulated
		List<Color> priceColors = theme.firstAxisColor(1 + requestedCumulatedDays.size());
		List<Color> volColors = theme.secondAxisColor(1 + requestedCumulatedDays.size());
		// we limit the number of cumulated series to how much colors we have.
		// if we have 1 vol and 1 price colors, these will be assigned to the immediate
		// series
		// if we have 2 vol and 1 price colors, we have the immediate and first
		// cumulated, but only the cumulated vol will be charted
		int nbCumulAverages = Math.min(
				requestedCumulatedDays.size(),
				Math.max(priceColors.size(), volColors.size()) - 1);
		List<Integer> cumulatedDays = nbCumulAverages < requestedCumulatedDays.size()
				? requestedCumulatedDays.subList(0, nbCumulAverages)
						: requestedCumulatedDays;
		XYPlot plot = new XYPlot();
		plot.setBackgroundPaint(bgColor);
		plot.setDomainGridlinePaint(textColor);
		plot.setRangeGridlinePaint(textColor);
		// plot.setDomainGridlinesVisible(false);

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

		TimeSeries averagePriceSeries = new TimeSeries("average price");
		TimeSeries volumeTradedSeries = new TimeSeries("daily " + quantityUnit + " traded");
		List<SlidingAverage> slidingAverages = cumulatedDays.stream().map(SlidingAverage::new).toList();
		List<TimeSeries> cumulatedPriceSeries = IntStream.range(0,
				Math.min(nbCumulAverages, priceColors.size() - 1))
				.mapToObj(daysIndex -> new TimeSeries("average price " + cumulatedDays.get(daysIndex) + "d"))
				.toList();
		List<TimeSeries> cumulatedVolumeSeries = IntStream.range(0,
				Math.min(nbCumulAverages, volColors.size() - 1))
				.mapToObj(
						daysIndex -> new TimeSeries("daily " + quantityUnit + " traded " + cumulatedDays.get(daysIndex) + "d"))
				.toList();
		for (AggregatedHL e : sortedData) {
			OffsetDateTime aggregDate = e.getDate().atOffset(ZoneOffset.UTC);
			aggregDate.get(ChronoField.DAY_OF_MONTH);
			RegularTimePeriod period = new Day(aggregDate.getDayOfMonth(),
					aggregDate.getMonthValue(),
					aggregDate.getYear());
			if (e.getAveragePrice() != null) {
				averagePriceSeries.add(period, e.getAveragePrice());
			}
			volumeTradedSeries.add(period, e.getVolume());
			for (int i = 0; i < slidingAverages.size(); i++) {
				SlidingAverage sa = slidingAverages.get(i);
				sa.add(e);
				Double avgPrice = sa.averagePrice();
				if (avgPrice != null && i < cumulatedPriceSeries.size()) {
					TimeSeries priceSeries = cumulatedPriceSeries.get(i);
					priceSeries.add(period, avgPrice);
				}
				if (i < cumulatedVolumeSeries.size()) {
					TimeSeries volSeries = cumulatedVolumeSeries.get(i);
					volSeries.add(period, sa.averageDailyVolume());
				}
			}
		}

		NumberAxis priceAxis = new NumberAxis("price");
		priceAxis.setNumberFormatOverride(RestControllerHelper.NUMBER_FORMAT_PRICES);
		priceAxis.setTickLabelPaint(textColor);
		priceAxis.setLabelPaint(textColor);
		plot.setRangeAxis(0, priceAxis);
		TimeSeriesCollection priceCollections = new TimeSeriesCollection();
		priceCollections.addSeries(averagePriceSeries);
		cumulatedPriceSeries.forEach(priceCollections::addSeries);
		plot.setDataset(0, priceCollections);
		XYLineAndShapeRenderer priceRenderer = new XYLineAndShapeRenderer(false, true);

		priceRenderer.setSeriesPaint(0, priceColors.get(0));
		for (int i = 0; i < cumulatedPriceSeries.size(); i++) {
			int seriesindex = i + 1;
			priceRenderer.setSeriesPaint(seriesindex, priceColors.get(seriesindex));
			priceRenderer.setSeriesLinesVisible(seriesindex, true);
			priceRenderer.setSeriesShapesVisible(seriesindex, false);
		}

		plot.setRenderer(0, priceRenderer);
		plot.mapDatasetToRangeAxis(0, 0);

		NumberAxis quantityAxis = new NumberAxis("quantity");
		quantityAxis.setNumberFormatOverride(RestControllerHelper.NUMBER_FORMAT_PRICES);
		quantityAxis.setTickLabelPaint(textColor);
		quantityAxis.setLabelPaint(textColor);
		quantityAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		plot.setRangeAxis(1, quantityAxis);
		TimeSeriesCollection qttyCollections = new TimeSeriesCollection();
		qttyCollections.addSeries(volumeTradedSeries);
		cumulatedVolumeSeries.forEach(qttyCollections::addSeries);
		plot.setDataset(1, qttyCollections);

		XYBarRenderer quantityRenderer = new ClusteredXYBarRenderer();
		quantityRenderer.setBarPainter(new StandardXYBarPainter());
		quantityRenderer.setShadowVisible(false);
		quantityRenderer.setMargin(0.1);

		quantityRenderer.setSeriesPaint(0, volColors.get(0));
		for (int i = 0; i < cumulatedVolumeSeries.size(); i++) {
			int seriesindex = i + 1;
			quantityRenderer.setSeriesPaint(seriesindex, volColors.get(seriesindex));
		}

		plot.setRenderer(1, quantityRenderer);
		plot.mapDatasetToRangeAxis(1, 1);

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		chart.setBackgroundPaint(bgColor);
		chart.getTitle().setPaint(textColor);
		chart.getLegend().setItemPaint(textColor);
		chart.getLegend().setBackgroundPaint(bgColor);
		return chart;
	}

	private Chart<?, ?> drawXChart(List<AggregatedHL> sortedData, String title) {
		OHLCChart ret = new OHLCChartBuilder().width(2000).height(1000).title(title).yAxisTitle("price").build();
		ret.getStyler().setYAxisMin(0.0);
		ret.getStyler().setLegendPosition(LegendPosition.OutsideS);
		List<Date> xData = new ArrayList<>();
		List<Double> openData = new ArrayList<>();
		List<Double> highData = new ArrayList<>();
		List<Double> lowData = new ArrayList<>();
		List<Double> closeData = new ArrayList<>();
		List<Long> volumeTraded = new ArrayList<>();
		Instant lastDate = null;
		Double lastVal = null;
		for (AggregatedHL e : sortedData) {
			Instant aggregDate = e.getDate();
			if (lastDate != null) {
				long daysDiff = ChronoUnit.DAYS.between(lastDate, aggregDate);
				for (int days = 1; days < daysDiff; days++) {
					Date missingDate = Date.from(lastDate.plus(days, ChronoUnit.DAYS));
					xData.add(missingDate);
					openData.add(lastVal);
					highData.add(lastVal);
					lowData.add(lastVal);
					closeData.add(lastVal);
					volumeTraded.add(0L);
				}
			}
			lastDate = aggregDate;
			if (aggregDate.equals(Instant.now().truncatedTo(ChronoUnit.DAYS))) {
				continue;
			}
			if (e.getAveragePrice() != null) {
				xData.add(Date.from(aggregDate));
				double val = e.getAveragePrice();
				if (lastVal != null) {
					openData.add(lastVal);
				} else {
					openData.add(val * 0.999);
				}
				closeData.add(val);
				lowData.add(e.getLowestPrice() != null ? e.getLowestPrice().doubleValue() : val);
				highData.add(e.getHighestPrice() != null ? Math.min(e.getHighestPrice().doubleValue(), val * 2) : val);
				volumeTraded.add(e.getVolume());
				lastVal = val;
			}
		}

		ret.addSeries("price", xData, openData, highData, lowData, closeData);
		return ret;
	}

	public URI uri(int typeId, String accept, ChartBuilder builder, String theme, Integer days) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "chartHistoryByType", "" + typeId, null,
						Optional.ofNullable(accept),
						Optional.ofNullable(builder),
						Optional.ofNullable(theme),
						null,
						Optional.ofNullable(days))
				.build()
				.toUri();
	}

	public URI uri(Type type, String accept, ChartBuilder builder, String theme, Integer days) {
		return uri(type.getId(), accept, builder, theme, days);
	}

	public URI uri(int typeId) {
		return uri(typeId, null, null, null, null);
	}

	public URI uri(Type type) {
		return uri(type.getId());
	}

	public URI uri(int typeId, boolean copy, int me, int te, String accept, ChartBuilder builder, String theme) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "chartContractHistoryByType", "" + typeId,
						copy,
						me,
						te,
						null,
						Optional.ofNullable(accept),
						Optional.ofNullable(builder),
						Optional.ofNullable(theme),
						null)
				.build()
				.toUri();
	}

	public URI uri(Type type, boolean copy, int me, int te, String accept, ChartBuilder builder, String theme) {
		return uri(type.getId(), copy, me, te, accept, builder, theme);
	}

	public URI uri(int typeId, boolean copy, int me, int te) {
		return uri(typeId, copy, me, te, null, null, null);
	}

	public URI uri(Type type, boolean copy, int me, int te) {
		return uri(type.getId(), copy, me, te);
	}

	public String typeUrl(int typeId, boolean copy, int me, int te) {
		return uri(typeId, copy, me, te).toString();
	}

	public String typeUrl(Type type, boolean copy, int me, int te) {
		return typeUrl(type.getId(), copy, me, te);
	}

	public String typeUrl(int typeId) {
		return uri(typeId, null, null, null, null).toString();
	}

	public String typeUrl(Type type) {
		return typeUrl(type.getId());
	}

}
