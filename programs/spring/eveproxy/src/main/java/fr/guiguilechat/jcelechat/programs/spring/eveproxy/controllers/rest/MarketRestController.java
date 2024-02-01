package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.DoubleStream;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.SellStat;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/market")
public class MarketRestController {

	@Autowired
	private RegionLineService rlService;

	@Autowired
	private TypeService typeService;

	record PriceFilter(int typeId, Integer regionId, Long locationId) {
	}

	record PriceLimitData(
			long volume,
			Double massPrice,
			Double avgPrice) {
	}

	@RequiredArgsConstructor
	static class PriceAccumulator {

		private final double priceLimit;

		private final boolean requireInf;

		public long volume = 0l;
		public Double massPrice = null;
		private double totValue = 0.0;
		public Double avgPrice = null;

		protected boolean accept(double orderPrice, double limit) {
			return requireInf ? orderPrice <= limit : orderPrice >= limit;
		}

		public PriceAccumulator accumulate(Iterable<RegionLine> lines) {
			for (RegionLine l : lines) {
				if (accept(l.getOrder().price, priceLimit)) {
					volume += l.getOrder().volume_remain;
					totValue += l.getOrder().volume_remain * l.getOrder().price;
					if (massPrice == null || !accept(l.getOrder().price, massPrice)) {
						massPrice = l.getOrder().price;
					}
				}
			}
			if (volume > 0l) {
				avgPrice = totValue / volume;
			}
			return this;
		}

		public PriceLimitData toData() {
			return new PriceLimitData(volume, massPrice, avgPrice);
		}
	}

	record TypeMarketStats(PriceFilter filter, List<PriceLimitData> sellorders, List<PriceLimitData> buyorders) {

		static TypeMarketStats of(int typeId, Integer regionId, Long locationId, List<RegionLine> bos,
				List<RegionLine> sos) {
			PriceFilter filter = new PriceFilter(typeId, regionId, locationId);

			List<PriceLimitData> sosData = Collections.emptyList();
			if (!sos.isEmpty()) {
				double bestSo = sos.get(0).getOrder().price;
				sosData = DoubleStream.of(bestSo, bestSo * 1.01, bestSo * 1.05, bestSo * 1.1, Double.POSITIVE_INFINITY)
						.mapToObj(limit -> new PriceAccumulator(limit, true).accumulate(sos).toData())
						.toList();
			}

			List<PriceLimitData> bosData = Collections.emptyList();
			if (!bos.isEmpty()) {
				double bestBo = bos.get(bos.size() - 1).getOrder().price;
				bosData = DoubleStream.of(bestBo, bestBo * 0.99, bestBo * 0.95, bestBo * 0.90, 0)
						.mapToObj(limit -> new PriceAccumulator(limit, false).accumulate(bos).toData())
						.toList();
			}

			return new TypeMarketStats(filter, sosData, bosData);
		}
	}

	@GetMapping("/byRegionId/{regionId}/byTypeId/{typeId}")
	public ResponseEntity<?> byRegionByType(@PathVariable int regionId, @PathVariable int typeId,
			@RequestParam Optional<String> accept) {
		List<RegionLine> bos = rlService.forRegion(regionId, typeId, true);
		List<RegionLine> sos = rlService.forRegion(regionId, typeId, false);
		return makeMarketStatsResponse(typeId, regionId, null, bos, sos, accept);
	}

	@GetMapping("/byLocationId/{locationId}/byTypeId/{typeId}")
	public ResponseEntity<?> byLocationByType(@PathVariable long locationId, @PathVariable int typeId,
			@RequestParam Optional<String> accept) {
		List<RegionLine> bos = rlService.forLocation(locationId, typeId, true);
		List<RegionLine> sos = rlService.forLocation(locationId, typeId, false);
		return makeMarketStatsResponse(typeId, null, locationId, bos, sos, accept);
	}

	@GetMapping("/jita/byTypeId/{typeId}")
	public ResponseEntity<?> jitaByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return byLocationByType(RegionLineService.JITAIV_ID, typeId, accept);
	}

	@GetMapping("/byLocationId/{locationId}/{filterBy}/{filter}/chart")
	public void chartbyLocationByType(@PathVariable long locationId, @PathVariable String filterBy,
			@PathVariable String filter,
			HttpServletResponse response,
			@RequestParam Optional<String> accept,
			@RequestParam Optional<String> bcolor) throws IOException {
		List<Type> types = null;
		types = switch (Objects.requireNonNullElse(filterBy, "name").toLowerCase()) {
			case "groupname", "gname", "gn" -> typeService.byGroupName(filter);
			case "groupid", "gid" -> typeService.byGroupId(Integer.parseInt(filter));
			case "typeid", "id", "tid" -> List.of(typeService.byId(Integer.parseInt(filter)).orElse(null));
			case "name", "tname", "tn" -> typeService.byName(filter);
			default -> typeService.byName(filter);
		};

		XYSeriesCollection ds = new XYSeriesCollection();
		for (Type type : types) {
			if (type == null) {
				continue;
			}
			List<SellStat> gains = rlService.sellGain(locationId, type.getTypeId());
			if (gains.isEmpty()) {
				continue;
			}
			XYSeries series = new XYSeries(type.getName());
			for (SellStat ss : gains) {
				series.add(new XYDataItem(ss.cumulQtty(), ss.cumulValue() / 1000000));
			}
			series.add(new XYDataItem(0.0, 0.0));
			ds.addSeries(series);
		}

		NumberAxis xAxis = new NumberAxis("volume");
		NumberAxis yAxis = new NumberAxis("M isk");
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
		renderer.setUseFillPaint(true);
		XYPlot plot = new XYPlot(ds, xAxis, yAxis, renderer);
		plot.setOrientation(PlotOrientation.VERTICAL);
		JFreeChart chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		try {
			plot.setBackgroundPaint((Color) Color.class.getField(bcolor.orElse("white")).get(null));
			chart.setBackgroundPaint((Color) Color.class.getField(bcolor.orElse("white")).get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// no care
		}
		RestControllerHelper.addResponseChart(response, chart, accept);
	}

	@GetMapping("/jita/{filterBy}/{filter}/chart")
	public void chartJitaByType(@PathVariable String filterBy, @PathVariable String filter,
			HttpServletResponse response, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> bcolor) throws IOException {
		chartbyLocationByType(RegionLineService.JITAIV_ID, filterBy, filter, response, accept, bcolor);
	}

	ResponseEntity<TypeMarketStats> makeMarketStatsResponse(int typeId, Integer regionId, Long locationId,
			List<RegionLine> bos, List<RegionLine> sos, Optional<String> accept) {
		return RestControllerHelper.makeResponse(TypeMarketStats.of(typeId, regionId, locationId, bos, sos), accept);
	}

	@GetMapping("/byTypeId/{typeId}/so")
	public ResponseEntity<?> soByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return RestControllerHelper.makeResponse(rlService.sellLocations(typeId), accept);
	}

	@GetMapping("/byTypeId/{typeId}/bo")
	public ResponseEntity<?> boByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return RestControllerHelper.makeResponse(rlService.buyLocations(typeId), accept);
	}

}
