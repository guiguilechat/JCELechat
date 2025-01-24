package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Stream;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
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

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeDataDto;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeBpc;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeBpo;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService.PriceVolumeAcc;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService.WeightStrategy;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history.DailyExchanges;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history.HistoryAggreg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryRestController {

	final private ContractFacadeBpc contractFacadeBpc;

	final private ContractFacadeBpo contractFacadeBpo;

	final private HistoryLineService hlService;

	private final TypeService typeService;

	private final int NB_STEPS = 10;

	@GetMapping("/byRegionId/{regionId}/byTypeId/{typeId}/byWeightName/{weightname}/daily")
	public ResponseEntity<DailyExchanges> byRegionByType(@PathVariable int regionId, @PathVariable int typeId,
	    @PathVariable String weightname,
	    @RequestParam Optional<String> accept) {
		WeightStrategy weighter = WeightStrategy.of(weightname);
		List<PriceVolumeAcc> priceVolumes = hlService.groupPrices(regionId, typeId, weighter, NB_STEPS);
		return RestControllerHelper.makeResponse(
		    DailyExchanges.of(new HistoryAggreg(typeId, regionId, weighter.toString(), NB_STEPS), priceVolumes),
		    accept);
	}

	@GetMapping("/byTypeId/{typeId}")
	@Transactional
	public ResponseEntity<TypeDataDto<List<AggregatedHL>>> byType(
	    @PathVariable int typeId,
	    @RequestParam Optional<String> accept,
	    @RequestParam Optional<Integer> days) {
		Type type = typeService.byId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		List<AggregatedHL> data = hlService.byType(typeId, Instant.now().minus(days.orElse(365 * 10), ChronoUnit.DAYS));
		return RestControllerHelper.makeResponse(TypeDataDto.of(type, data),
		    accept);
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
	    @RequestParam @Parameter(description = "builder for the chart. Can be \"jfreechart\" or \"xchart\"(default)") Optional<String> builder,
	    @RequestParam @Parameter(description = "maximum days to show. Default 10 years") Optional<Integer> days)
	    throws IOException {
		Type type = typeService.byId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		List<AggregatedHL> fetchedData = hlService.byType(typeId,
		    Instant.now().minus(days.orElse(365 * 10) + 1, ChronoUnit.DAYS));
		if (fetchedData.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " has no sale record");
		}
		List<AggregatedHL> sortedData = Stream.concat(
		    fetchedData.stream(),
		    Stream.of(new AggregatedHL(Instant.now().truncatedTo(ChronoUnit.DAYS), 0, null, null,
		        null, 0, 0)))
		    .sorted(Comparator.comparing(AggregatedHL::getDate)).toList();

		String title = "universe sales of " + type.name()
		    + (days.isPresent() ? " over the last " + days.get() + " days" : "");
		switch (builder.orElse("xchart").toLowerCase()) {
		case "jfreechart":
			RestControllerHelper.addResponseJFreeChart(response, drawJFreeChart(sortedData, title), accept);
			break;
		case "xchart":
		default:
			RestControllerHelper.addResponseXChart(response, drawXChart(sortedData, title), accept);
			break;
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
	    @RequestParam @Parameter(description = "builder for the chart. Can be \"jfreechart\" or \"xchart\"(default)") Optional<String> builder)
	    throws IOException {
		Type type = typeService.byId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		List<AggregatedHL> fetchedData = copy ? contractFacadeBpc.aggregatedSales(typeId, me, te)
		    : contractFacadeBpo.aggregatedSales(typeId, me, te);
		if (fetchedData.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " has no sale record");
		}
		List<AggregatedHL> sortedData = Stream.concat(
		    fetchedData.stream(),
		    Stream.of(new AggregatedHL(Instant.now().truncatedTo(ChronoUnit.DAYS), 0, null, null,
		        null, 0, 0)))
		    .sorted(Comparator.comparing(AggregatedHL::getDate)).toList();

		String title = "contract sales of " + type.name() + (copy ? " (CP)" : "") + " " + me + "/" + te;
		switch (builder.orElse("xchart").toLowerCase()) {
		case "jfreechart":
			RestControllerHelper.addResponseJFreeChart(response, drawJFreeChart(sortedData, title), accept);
			break;
		case "xchart":
		default:
			RestControllerHelper.addResponseXChart(response, drawXChart(sortedData, title), accept);
			break;
		}

	}

	private JFreeChart drawJFreeChart(List<AggregatedHL> sortedData, String title) {
		XYPlot plot = new XYPlot();
		// tick on first day of week https://stackoverflow.com/a/11052090
		@SuppressWarnings("serial")
		DateAxis timeAxis = new DateAxis(null) {
			@Override
			protected Date previousStandardDate(Date date, DateTickUnit unit) {
				Date prevDate = super.previousStandardDate(date, unit);

				Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
				calendar.setTime(prevDate);

				int firstDayOfWeek = calendar.getFirstDayOfWeek();
				if (firstDayOfWeek != calendar.get(Calendar.DAY_OF_WEEK)) {
					calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
				}

				return calendar.getTime();
			}
		};
		timeAxis.setLowerMargin(0.02);
		timeAxis.setUpperMargin(0.02);
		plot.setDomainAxis(timeAxis);

		TimeSeries averagePrice = new TimeSeries("average price");
		TimeSeries minPrice = new TimeSeries("minimum price");
		TimeSeries maxPrice = new TimeSeries("maximum price");
		TimeSeries volumeTraded = new TimeSeries("number traded");
		Instant lastDate = null;
		for (AggregatedHL e : sortedData) {
			Instant aggregDate = (e.getDate());
			if (lastDate != null) {
				long daysDiff = ChronoUnit.DAYS.between(lastDate, aggregDate);
				for (int days = 1; days < daysDiff; days++) {
					Date missingDate = Date.from(lastDate.plus(days, ChronoUnit.DAYS));
					// System.err
					// .println("adding missing day " + missingDate + " as missing days " + days + "
					// out of " + daysDiff
					// + " from " + lastDate + " to " + aggregDate);
					RegularTimePeriod period = new Day(missingDate);
					volumeTraded.add(period, 0);
				}
			}
			lastDate = aggregDate;
			if (aggregDate.equals(Instant.now().truncatedTo(ChronoUnit.DAYS))) {
				continue;
			}
			RegularTimePeriod period = new Day(Date.from(aggregDate));
			if (e.getAveragePrice() != null) {
				averagePrice.add(period, e.getAveragePrice().doubleValue());
			}
			if (e.getLowestPrice() != null) {
				minPrice.add(period, e.getLowestPrice().doubleValue());
			}
			if (e.getHighestPrice() != null) {
				maxPrice.add(period, e.getHighestPrice().doubleValue());
			}
			volumeTraded.add(period, e.getVolume());
		}

		NumberAxis priceAxis = new NumberAxis("price");
		priceAxis.setNumberFormatOverride(RestControllerHelper.NUMBER_FORMAT_PRICES);
		plot.setRangeAxis(0, priceAxis);
		TimeSeriesCollection priceCollections = new TimeSeriesCollection();
		priceCollections.addSeries(averagePrice);
		// priceCollections.addSeries(minPrice);
		// priceCollections.addSeries(maxPrice);
		plot.setDataset(0, priceCollections);
		XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(false, true);
		plot.setRenderer(0, renderer0);
		plot.mapDatasetToRangeAxis(0, 0);

		NumberAxis quantityAxis = new NumberAxis("quantity");
		quantityAxis.setNumberFormatOverride(RestControllerHelper.NUMBER_FORMAT_PRICES);
		plot.setRangeAxis(1, quantityAxis);
		TimeSeriesCollection qttyCollections = new TimeSeriesCollection();
		qttyCollections.addSeries(volumeTraded);
		plot.setDataset(1, qttyCollections);

		XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false, true);
		plot.setRenderer(1, renderer1);
		plot.mapDatasetToRangeAxis(1, 1);

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
		    plot, true);
		chart.setBackgroundPaint(Color.WHITE);

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
			Instant aggregDate = (e.getDate());
			if (lastDate != null) {
				long daysDiff = ChronoUnit.DAYS.between(lastDate, aggregDate);
				for (int days = 1; days < daysDiff; days++) {
					Date missingDate = Date.from(lastDate.plus(days, ChronoUnit.DAYS));
					xData.add(missingDate);
					openData.add(lastVal);
					highData.add(lastVal);
					lowData.add(lastVal);
					closeData.add(lastVal);
					volumeTraded.add(0l);
				}
			}
			lastDate = aggregDate;
			if (aggregDate.equals(Instant.now().truncatedTo(ChronoUnit.DAYS))) {
				continue;
			}
			if (e.getAveragePrice() != null) {
				xData.add(Date.from(aggregDate));
				double val = e.getAveragePrice().doubleValue();
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

	public URI uri(int typeId, String accept, String builder, Integer days) {
		return MvcUriComponentsBuilder
		    .fromMethodName(getClass(), "chartHistoryByType", "" + typeId, null,
		        Optional.ofNullable(accept),
		        Optional.ofNullable(builder),
		        Optional.ofNullable(days))
		    .build()
		    .toUri();
	}

	public URI uri(Type type, String accept, String builder, Integer days) {
		return uri(type.getId(), accept, builder, days);
	}

	public URI uri(int typeId) {
		return uri(typeId, null, null, null);
	}

	public URI uri(Type type) {
		return uri(type.getId());
	}


	public URI uri(int typeId, boolean copy, int me, int te, String accept, String builder) {
		return MvcUriComponentsBuilder
		    .fromMethodName(getClass(), "chartContractHistoryByType", "" + typeId,
		        copy,
		        me,
		        te,
		        null,
		        Optional.ofNullable(accept),
		        Optional.ofNullable(builder))
		    .build()
		    .toUri();
	}

	public URI uri(Type type, boolean copy, int me, int te, String accept, String builder) {
		return uri(type.getId(), copy, me, te, accept, builder);
	}

	public URI uri(int typeId, boolean copy, int me, int te) {
		return uri(typeId, copy, me, te, null, null);
	}

	public URI uri(Type type, boolean copy, int me, int te) {
		return uri(type.getId(), copy, me, te);
	}

}
