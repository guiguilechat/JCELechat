package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

	record VolumePrice(long volume, double price) {
		static VolumePrice of(Map.Entry<Long, Double> price) {
			return new VolumePrice(price.getKey(), price.getValue());
		}
	};

	@SuppressWarnings("serial")
	static class OfferStats extends ArrayList<VolumePrice> {

		public static OfferStats of(VolumePrice... prices) {
			var ret = new OfferStats();
			if (prices != null) {
				ret.addAll(Arrays.asList(prices));
			}
			return ret;
		}

		public static OfferStats of(Map<Long, Double> pricesByVolume) {
			var ret = new OfferStats();
			ret.addAll(pricesByVolume.entrySet().stream().map(VolumePrice::of)
					.sorted(Comparator.comparing(VolumePrice::volume)).toList());
			return ret;
		}

	}

	record TypeMarketStats(PriceFilter filter, OfferStats sellorders, OfferStats buyorders) {

		static TypeMarketStats of(int typeId, Integer regionId, Long locationId, List<RegionLine> bos,
				List<RegionLine> sos) {
			PriceFilter filter = new PriceFilter(typeId, regionId, locationId);
			double bestSo = Double.NaN;
			double worstSo = Double.NaN;
			long volSoTot = 0l;
			long volSo1pct = 0l;
			double bestSo1pct = Double.NaN;
			long volSo5pct = 0l;
			double bestSo5pct = Double.NaN;
			long volSo10pct = 0l;
			double bestSo10pct = Double.NaN;
			if (!sos.isEmpty()) {
				bestSo = bestSo1pct = bestSo5pct = bestSo10pct = sos.get(0).getOrder().price;
				worstSo = sos.get(sos.size() - 1).getOrder().price;
				for (RegionLine l : sos) {
					volSoTot += l.getOrder().volume_remain;
					if (l.getOrder().price <= 1.01 * bestSo) {
						volSo1pct += l.getOrder().volume_remain;
						bestSo1pct = Math.max(bestSo1pct, l.getOrder().price);
					}
					if (l.getOrder().price <= 1.05 * bestSo) {
						volSo5pct += l.getOrder().volume_remain;
						bestSo5pct = Math.max(bestSo5pct, l.getOrder().price);
					}
					if (l.getOrder().price <= 1.1 * bestSo) {
						volSo10pct += l.getOrder().volume_remain;
						bestSo10pct = Math.max(bestSo10pct, l.getOrder().price);
					}
				}
			}
			OfferStats soStats = OfferStats.of(Map.of(
					1l, bestSo,
					volSo1pct, bestSo1pct,
					volSo5pct, bestSo5pct,
					volSo10pct, bestSo10pct,
					volSoTot, worstSo));

			double bestBo = Double.NaN;
			double worstBo = Double.NaN;
			long volBo = 0l;
			long volBo1pct = 0l;
			double bestBo1pct = Double.NaN;
			long volBo5pct = 0l;
			double bestBo5pct = Double.NaN;
			long volBo10pct = 0l;
			double bestBo10pct = Double.NaN;
			if (!bos.isEmpty()) {
				bestBo = bestBo1pct = bestBo5pct = bestBo10pct = bos.get(bos.size() - 1).getOrder().price;
				worstBo = bos.get(0).getOrder().price;
				for (RegionLine l : bos) {
					volBo += l.getOrder().volume_remain;
					if (l.getOrder().price >= 0.99 * bestBo) {
						volBo1pct += l.getOrder().volume_remain;
						bestBo1pct = Math.min(bestBo1pct, l.getOrder().price);
					}
					if (l.getOrder().price >= 0.95 * bestBo) {
						volBo5pct += l.getOrder().volume_remain;
						bestBo5pct = Math.min(bestBo5pct, l.getOrder().price);
					}
					if (l.getOrder().price >= 0.9 * bestBo) {
						volBo10pct += l.getOrder().volume_remain;
						bestBo10pct = Math.min(bestBo10pct, l.getOrder().price);
					}
				}
			}
			OfferStats boStats = OfferStats.of(Map.of(
					1l, bestBo,
					volBo1pct, bestBo1pct,
					volBo5pct, bestBo5pct,
					volBo10pct, bestBo10pct,
					volBo, worstBo));

			return new TypeMarketStats(filter, soStats, boStats);
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
