package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLine;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService.OfferStat;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/market")
@RequiredArgsConstructor
public class MarketRestController {

	final private MarketLineService rlService;

	final private TypeService typeService;

	final private RegionService regionService;

	/** which market to observe ? Either a region or a location */
	public static record PlaceFilter(Region region, Long locationId) implements Serializable {

		public static PlaceFilter ofRegion(Region region) {
			return new PlaceFilter(region, null);
		}

		public static PlaceFilter ofLocation(long locationId) {
			return new PlaceFilter(null, locationId);
		}

		public Integer regionId() {
			return region() == null ? null : region().getId();
		}

		public List<MarketLine> bos(MarketLineService rlService, int typeId) {
			if (locationId() > 0) {
				return rlService.forLocation(locationId, typeId, true);
			}
			if (region() != null) {
				return rlService.forRegion(region().getId(), typeId, true);
			}
			return rlService.forAll(typeId, true);
		}

		public List<MarketLine> sos(MarketLineService rlService, int typeId) {
			if (locationId() > 0) {
				return rlService.forLocation(locationId, typeId, false);
			}
			if (region() != null) {
				return rlService.forRegion(region().getId(), typeId, false);
			}
			return rlService.forAll(typeId, false);
		}

		List<OfferStat> gains(MarketLineService rlService, int typeId) {
			if (locationId() > 0) {
				return rlService.offerStatsLocation(locationId(), typeId);
			}
			if (region() != null) {
				return rlService.offerStatsRegion(region().getId(), typeId);
			}
			return rlService.offerStatsAll(typeId);
		}

		public String toLegend() {
			if (locationId() > 0) {
				return "locationId=" + locationId();
			}
			if (region() != null) {
				return "region " + region().getName();
			}
			return "all";
		}

	}

	public PlaceFilter placeFilter(String placeFiltering, String placeFilter) {
		if (placeFiltering == null || placeFilter == null || placeFilter.equalsIgnoreCase("all")) {
			return new PlaceFilter(null, null);
		}
		Region reg = null;
		Long locationId = null;
		switch (placeFiltering.toLowerCase()) {
			case "rid":
			case "regionid":
			case "ri":
				reg = regionService.byId(Integer.parseInt(placeFilter));
			break;
			case "rname":
			case "rn":
			case "regionname":
				List<Region> regs = regionService.byName(placeFilter);
				reg = regs.isEmpty() ? null : regs.get(0);
			break;
			case "lid":
			case "li":
			case "locationid":
				locationId = Long.parseLong(placeFilter);
			break;
		}
		return new PlaceFilter(reg, locationId);
	}

	ResponseEntity<TypeMarketStats> makeMarketStatsResponse(int typeId, Integer regionId, Long locationId,
			List<MarketLine> bos, List<MarketLine> sos, Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(TypeMarketStats.of(typeId, regionId, locationId, bos, sos), accept);
	}

	static record PriceLimitData(
			long volume,
			Double massPrice,
			Double avgPrice) {
	}

	static record PriceFilter(int typeId, Integer regionId, Long locationId) {
	}

	@RequiredArgsConstructor
	static class PriceAccumulator {

		private final double priceLimit;

		private final boolean requireInf;

		public long volume = 0L;
		public Double massPrice = null;
		private double totValue = 0.0;
		public Double avgPrice = null;

		protected boolean accept(double orderPrice, double limit) {
			return requireInf ? orderPrice <= limit : orderPrice >= limit;
		}

		public PriceAccumulator accumulate(Iterable<MarketLine> lines) {
			for (MarketLine l : lines) {
				if (accept(l.getPrice(), priceLimit)) {
					volume += l.getVolumeRemain();
					totValue += l.getVolumeRemain() * l.getPrice();
					if (massPrice == null || !accept(l.getPrice(), massPrice)) {
						massPrice = l.getPrice();
					}
				}
			}
			if (volume > 0L) {
				avgPrice = totValue / volume;
			}
			return this;
		}

		public PriceLimitData toData() {
			return new PriceLimitData(volume, massPrice, avgPrice);
		}
	}

	static record TypeMarketStats(PriceFilter filter, List<PriceLimitData> sellorders, List<PriceLimitData> buyorders) {

		static TypeMarketStats of(int typeId, Integer regionId, Long locationId, List<MarketLine> bos,
		    List<MarketLine> sos) {
			PriceFilter filter = new PriceFilter(typeId, regionId, locationId);

			List<PriceLimitData> sosData = Collections.emptyList();
			if (!sos.isEmpty()) {
				double bestSo = sos.get(0).getPrice();
				sosData = DoubleStream.of(bestSo, bestSo * 1.01, bestSo * 1.05, bestSo * 1.1, Double.POSITIVE_INFINITY)
						.mapToObj(limit -> new PriceAccumulator(limit, true).accumulate(sos).toData())
						.toList();
			}

			List<PriceLimitData> bosData = Collections.emptyList();
			if (!bos.isEmpty()) {
				double bestBo = bos.get(bos.size() - 1).getPrice();
				bosData = DoubleStream.of(bestBo, bestBo * 0.99, bestBo * 0.95, bestBo * 0.90, 0)
						.mapToObj(limit -> new PriceAccumulator(limit, false).accumulate(bos).toData())
						.toList();
			}

			return new TypeMarketStats(filter, sosData, bosData);
		}
	}

	@Operation(summary = "get market stats", description = "get by and sell prices by volume steps ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of prices")
	})
	@GetMapping("/{placeFiltering}/{placeFilter}/typeId/{typeId}/stats")
	public ResponseEntity<TypeMarketStats> statsByPlaceByType(@PathVariable String placeFiltering,
			@PathVariable String placeFilter,
			@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {

		PlaceFilter place = placeFilter(placeFiltering, placeFilter);
		List<MarketLine> bos = place.bos(rlService, typeId);
		List<MarketLine> sos = place.sos(rlService, typeId);
		return makeMarketStatsResponse(typeId, place.regionId(), place.locationId(), bos, sos, accept);
	}

	@Operation(summary = "get market stats", description = "get by and sell prices by volume steps ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of prices")
	})
	@GetMapping("/jita/typeId/{typeId}/stats")
	public ResponseEntity<TypeMarketStats> statsJitaByType(@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return statsByPlaceByType("lid", "" + MarketLineService.JITAIV_ID, typeId, accept);
	}

	public static record MarketOffer(int volume, double price) {
		public MarketOffer(MarketLine line) {
			this(line.getVolumeRemain(), line.getPrice());
		}
	}

	List<MarketOffer> offers(String placeFiltering, String placeFilter, int typeId, boolean buy_order) {
		PlaceFilter place = placeFilter(placeFiltering, placeFilter);
		return (buy_order ? place.bos(rlService, typeId) : place.sos(rlService, typeId))
				.stream().map(MarketOffer::new).toList();
	}

	@GetMapping("/{placeFiltering}/{placeFilter}/typeId/{typeId}/sos")
	public ResponseEntity<List<MarketOffer>> sosByPlaceByType(@PathVariable String placeFiltering,
			@PathVariable String placeFilter,
			@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(offers(placeFiltering, placeFilter, typeId, false), accept);
	}

	@GetMapping("/jita/typeId/{typeId}/sos")
	public ResponseEntity<List<MarketOffer>> sosJitaByType(
			@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(offers("lid", "" + MarketLineService.JITAIV_ID, typeId, false), accept);
	}

	@GetMapping("/{placeFiltering}/{placeFilter}/typeId/{typeId}/bos")
	public ResponseEntity<List<MarketOffer>> bosByPlaceByType(@PathVariable String placeFiltering,
			@PathVariable String placeFilter,
			@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(offers(placeFiltering, placeFilter, typeId, true), accept);
	}

	@GetMapping("/jita/typeId/{typeId}/bos")
	public ResponseEntity<List<MarketOffer>> bosJitaByType(
			@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(offers("lid", "" + MarketLineService.JITAIV_ID, typeId, true), accept);
	}

	@GetMapping("/{placeFiltering}/{placeFilter}/{typeFiltering}/{typeFilter}/chart")
	public void chartbyLocationByType(@PathVariable String placeFiltering, @PathVariable String placeFilter,
			@PathVariable String typeFiltering, @PathVariable String typeFilter,
			HttpServletResponse response,
			@RequestParam Optional<String> accept,
			@RequestParam Optional<String> bcolor) throws IOException {
		List<Type> types = typeService.typesFilter(typeFiltering, typeFilter);
		PlaceFilter place = placeFilter(placeFiltering, placeFilter);

		XYSeriesCollection ds = new XYSeriesCollection();
		for (Type type : types) {
			if (type == null) {
				continue;
			}
			List<OfferStat> gains = place.gains(rlService, type.getId());
			if (gains.isEmpty()) {
				continue;
			}
			XYSeries series = new XYSeries(type.name());
			for (OfferStat ss : gains) {
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
		JFreeChart chart = new JFreeChart("sell value for " + place.toLegend(), JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		try {
			plot.setBackgroundPaint((Color) Color.class.getField(bcolor.orElse("white")).get(null));
			chart.setBackgroundPaint((Color) Color.class.getField(bcolor.orElse("white")).get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// no care
		}
		RestControllerHelper.addResponseJFreeChart(response, chart, accept);
	}

	@GetMapping("/jita/{typeFiltering}/{typeFilter}/chart")
	public void chartJitaByType(@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			HttpServletResponse response, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> bcolor) throws IOException {
		chartbyLocationByType("lid", "" + MarketLineService.JITAIV_ID, typeFiltering, typeFilter, response, accept, bcolor);
	}

	@GetMapping("/selllocations/byTypeId/{typeId}")
	public ResponseEntity<List<LocatedBestOffer>> soByType(@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(rlService.sellLocations(typeId), accept);
	}

	@GetMapping("/buylocations/byTypeId/{typeId}")
	public ResponseEntity<List<LocatedBestOffer>> boByType(@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(rlService.buyLocations(typeId), accept);
	}

}
