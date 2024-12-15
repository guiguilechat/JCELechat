package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import fr.guiguilechat.jcelechat.libs.spring.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService.PriceVolumeAcc;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService.WeightStrategy;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryRestController {

	final private HistoryLineService hlService;

	private final TypeService typeService;

	private final int NB_STEPS = 10;

	record HistoryAggreg(int typeId, Integer regionId, String weightName, int steps) {
	}

	record PriceVolume(BigDecimal price, BigDecimal volumeAbove, BigDecimal volumeBelow) {
	}

	record DailyExchanges(HistoryAggreg filter, List<PriceVolume> volumes) {

		static List<PriceVolume> convertVolumes(List<PriceVolumeAcc> groups) {
			ArrayList<PriceVolume> ret = new ArrayList<>();
			groups.forEach(pv -> ret.add(new PriceVolume(
			    new BigDecimal(pv.price).setScale(2, RoundingMode.HALF_EVEN),
			    new BigDecimal(pv.above).setScale(2, RoundingMode.HALF_EVEN),
			    new BigDecimal(pv.below).setScale(2, RoundingMode.HALF_EVEN))));
			return ret;
		}

		public static DailyExchanges of(HistoryAggreg filter, List<PriceVolumeAcc> groups) {
			return new DailyExchanges(filter, convertVolumes(groups));
		}
	}

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
	public ResponseEntity<TypeDataDto<List<AggregatedHL>>> byType(@PathVariable int typeId,
	    @RequestParam Optional<String> accept) {
		Type type = typeService.byId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		List<AggregatedHL> data = hlService.byType(typeId);
		return RestControllerHelper.makeResponse(TypeDataDto.of(type, data),
		    accept);
	}

	@Transactional
	@GetMapping("/byTypeId/{typeId}/chart")
	public void chartHistoryByType(
	    @PathVariable int typeId,
	    HttpServletResponse response,
	    @RequestParam Optional<String> accept) throws IOException {
		Type type = typeService.byId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		List<AggregatedHL> data = hlService.byType(typeId);
		JFreeChart chart = drawChart(data, "universe sales of " + type.name());
		RestControllerHelper.addResponseChart(response, chart, accept);
	}

	private JFreeChart drawChart(List<AggregatedHL> data, String title) {
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
		List<AggregatedHL> sortedStatsList = Stream.concat(
		    data.stream(),
		    Stream.of(new AggregatedHL(Instant.now().truncatedTo(ChronoUnit.DAYS), 0, null, null,
		        null, 0, 0)))
		    .sorted(Comparator.comparing(AggregatedHL::getDate)).toList();

		{
			TimeSeries averagePrice = new TimeSeries("average price");
			TimeSeries minPrice = new TimeSeries("minimum price");
			TimeSeries maxPrice = new TimeSeries("maximum price");
			TimeSeries volumeTraded = new TimeSeries("number traded");
			Instant lastDate = null;
			for (AggregatedHL e : sortedStatsList) {
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
		}

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
		    plot, true);
		chart.setBackgroundPaint(Color.WHITE);

		return chart;
	}

	public URI uri(Type type) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "chartHistoryByType", "" + type.getId(), null, null)
		    .build()
		    .toUri();
	}

}
