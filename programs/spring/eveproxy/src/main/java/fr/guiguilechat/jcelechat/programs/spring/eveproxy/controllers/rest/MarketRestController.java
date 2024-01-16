package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.List;
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

	record OffersStat(
			/** best price offered for the type */
			double best,
			/** total volume or orders available */
			long volume,
			/** volume of orders with price around 1% of the best */
			long vol1pc,
			/** volume of orders with price around 5% of best */
			long vol5pc) {

	}

	record TypeMarketStats(OffersStat sell, OffersStat buy) {

		static TypeMarketStats of(List<RegionLine> bos, List<RegionLine> sos) {
			double so = Double.NaN;
			long volSO = 0l;
			long volSO1pct = 0l;
			long volSO5pct = 0l;
			if (!sos.isEmpty()) {
				so = sos.get(0).getOrder().price;
				for (RegionLine l : sos) {
					volSO += l.getOrder().volume_remain;
					if (l.getOrder().price <= 1.01 * so) {
						volSO1pct += l.getOrder().volume_remain;
					}
					if (l.getOrder().price <= 1.05 * so) {
						volSO5pct += l.getOrder().volume_remain;
					}
				}
			}
			OffersStat soStats = new OffersStat(so, volSO, volSO1pct, volSO5pct);
			double bo = Double.NaN;
			long volBO = 0l;
			long volBO1pct = 0l;
			long volBO5pct = 0l;
			if (!bos.isEmpty()) {
				bo = bos.get(bos.size() - 1).getOrder().price;
				for (RegionLine l : bos) {
					volBO += l.getOrder().volume_remain;
					if (l.getOrder().price >= 0.99 * bo) {
						volBO1pct += l.getOrder().volume_remain;
					}
					if (l.getOrder().price >= 0.95 * bo) {
						volBO5pct += l.getOrder().volume_remain;
					}
				}
			}
			OffersStat boStats = new OffersStat(bo, volBO, volBO1pct, volBO5pct);
			return new TypeMarketStats(soStats, boStats);
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
		return makeMarketStatsResponse(bos, sos, accept);
	}

	@GetMapping("/byLocationId/{locationId}/byTypeId/{typeId}")
	public ResponseEntity<?> byLocationByType(@PathVariable long locationId, @PathVariable int typeId,
			@RequestParam Optional<String> accept) {
		List<RegionLine> bos = rlService.forLocation(locationId, typeId, true);
		List<RegionLine> sos = rlService.forLocation(locationId, typeId, false);
		return makeMarketStatsResponse(bos, sos, accept);
	}

	ResponseEntity<TypeMarketStats> makeMarketStatsResponse(List<RegionLine> bos, List<RegionLine> sos, Optional<String> accept) {
		return makeResponse(TypeMarketStats.of(bos, sos), accept);
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
