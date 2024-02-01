package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
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
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/market")
public class MarketRestController {

	@Autowired
	private RegionLineService rlService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private RegionService regionService;

	public static record PlaceFilter(Region region, long locationId) implements Serializable {
		public static PlaceFilter ofRegion(Region region) {
			return new PlaceFilter(region, -1);
		}

		public static PlaceFilter ofLocation(long locationId) {
			return new PlaceFilter(null, locationId);
		}

		public Integer regionId() {
			return region() == null ? null : region().getRegionId();
		}

		public List<RegionLine> bos(RegionLineService rlService, int typeId) {
			if (locationId() > 0) {
				return rlService.forLocation(locationId, typeId, true);
			}
			if (region() != null) {
				return rlService.forRegion(region().getRegionId(), typeId, true);
			}
			return rlService.forAll(typeId, true);
		}

		public List<RegionLine> sos(RegionLineService rlService, int typeId) {
			if (locationId() > 0) {
				return rlService.forLocation(locationId, typeId, false);
			}
			if (region() != null) {
				return rlService.forRegion(region().getRegionId(), typeId, false);
			}
			return rlService.forAll(typeId, false);
		}

		List<SellStat> gains(RegionLineService rlService, int typeId) {
			if (locationId() > 0) {
				return rlService.sellLocationGain(locationId(), typeId);
			}
			if (region() != null) {
				return rlService.sellRegionGain(region().getRegionId(), typeId);
			}
			return rlService.sellAllGain(typeId);
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
			return new PlaceFilter(null, -1);
		}
		Region reg = null;
		long locationId = -1;
		switch (placeFiltering.toLowerCase()) {
			case "rid":
			case "regionid":
			case "ri":
				reg = regionService.byId(Integer.parseInt(placeFilter)).orElse(null);
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

	public List<Type> typesFilter(String typeFiltering, String typeFilter) {
		return switch (Objects.requireNonNullElse(typeFiltering, "name").toLowerCase()) {
			case "groupname", "gname", "gn" -> typeService.byGroupName(typeFilter);
			case "groupid", "gid" -> typeService.byGroupId(Integer.parseInt(typeFilter));
			case "typeid", "id", "tid" -> List.of(typeService.byId(Integer.parseInt(typeFilter)).orElse(null));
			case "name", "tname", "tn" -> typeService.byName(typeFilter);
			default -> typeService.byName(typeFilter);
		};
	}

	ResponseEntity<TypeMarketStats> makeMarketStatsResponse(int typeId, Integer regionId, Long locationId,
			List<RegionLine> bos, List<RegionLine> sos, Optional<String> accept) {
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

	static record TypeMarketStats(PriceFilter filter, List<PriceLimitData> sellorders, List<PriceLimitData> buyorders) {

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

	@GetMapping("/{placeFiltering}/{placeFilter}/typeId/{typeId}")
	public ResponseEntity<?> byPlaceByType(@PathVariable String placeFiltering, @PathVariable String placeFilter,
			@PathVariable int typeId,
			@RequestParam Optional<String> accept) {

		PlaceFilter place = placeFilter(placeFiltering, placeFilter);
		List<RegionLine> bos = place.bos(rlService, typeId);
		List<RegionLine> sos = place.sos(rlService, typeId);
		return makeMarketStatsResponse(typeId, place.regionId(), null, bos, sos, accept);
	}

	@GetMapping("/jita/typeId/{typeId}")
	public ResponseEntity<?> jitaByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return byPlaceByType("lid", "" + RegionLineService.JITAIV_ID, typeId, accept);
	}

	@GetMapping("/{placeFiltering}/{placeFilter}/{typeFiltering}/{typeFilter}/chart")
	public void chartbyLocationByType(@PathVariable String placeFiltering, @PathVariable String placeFilter,
			@PathVariable String typeFiltering,	@PathVariable String typeFilter,
			HttpServletResponse response,
			@RequestParam Optional<String> accept,
			@RequestParam Optional<String> bcolor) throws IOException {
		List<Type> types = typesFilter(typeFiltering, typeFilter);
		PlaceFilter place = placeFilter(placeFiltering, placeFilter);

		XYSeriesCollection ds = new XYSeriesCollection();
		for (Type type : types) {
			if (type == null) {
				continue;
			}
			List<SellStat> gains = place.gains(rlService, type.getTypeId());
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
		JFreeChart chart = new JFreeChart("sell value for " + place.toLegend(), JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		try {
			plot.setBackgroundPaint((Color) Color.class.getField(bcolor.orElse("white")).get(null));
			chart.setBackgroundPaint((Color) Color.class.getField(bcolor.orElse("white")).get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// no care
		}
		RestControllerHelper.addResponseChart(response, chart, accept);
	}

	@GetMapping("/jita/{typeFiltering}/{typeFilter}/chart")
	public void chartJitaByType(@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			HttpServletResponse response, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> bcolor) throws IOException {
		chartbyLocationByType("lid", "" + RegionLineService.JITAIV_ID, typeFiltering, typeFilter, response, accept, bcolor);
	}

	@GetMapping("/sos/byTypeId/{typeId}")
	public ResponseEntity<?> soByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return RestControllerHelper.makeResponse(rlService.sellLocations(typeId), accept);
	}

	@GetMapping("/bos/byTypeId/{typeId}")
	public ResponseEntity<?> boByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return RestControllerHelper.makeResponse(rlService.buyLocations(typeId), accept);
	}

}
