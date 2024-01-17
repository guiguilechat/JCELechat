package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/market")
public class MarketRestController {

	@Autowired
	private RegionLineService rlService;

	/**
	 * presents data as a response depending on the accept value.
	 *
	 * @return a new responsentity with data provided, and content Type matching the
	 *           accept if provided. Default is json.
	 */
	static <T> ResponseEntity<T> makeResponse(T data, Optional<String> accept) {
		HttpHeaders responseHeaders = new HttpHeaders();
		switch (accept.orElse("json")) {
			case "xml":
				responseHeaders.setContentType(MediaType.APPLICATION_XML);
			break;
			case "json":
			default:
				responseHeaders.setContentType(MediaType.APPLICATION_JSON);
			break;
		}
		return new ResponseEntity<>(data, responseHeaders, HttpStatus.OK);
	}

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

	@GetMapping("/jita/byTypeId/{typeId}")
	public ResponseEntity<?> jitaByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return byLocationByType(RegionLineService.JITAIV_ID, typeId, accept);
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

	ResponseEntity<TypeMarketStats> makeMarketStatsResponse(int typeId, Integer regionId, Long locationId,
			List<RegionLine> bos, List<RegionLine> sos, Optional<String> accept) {
		return makeResponse(TypeMarketStats.of(typeId, regionId, locationId, bos, sos), accept);
	}

	@GetMapping("/byTypeId/{typeId}/so")
	public ResponseEntity<?> soByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return makeResponse(rlService.sellLocations(typeId), accept);
	}

	@GetMapping("/byTypeId/{typeId}/bo")
	public ResponseEntity<?> boByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		return makeResponse(rlService.buyLocations(typeId), accept);
	}

}
