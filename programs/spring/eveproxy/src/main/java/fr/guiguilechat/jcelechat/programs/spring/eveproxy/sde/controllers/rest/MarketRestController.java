package fr.guiguilechat.jcelechat.programs.spring.eveproxy.sde.controllers.rest;

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

import fr.guiguilechat.jcelechat.libs.spring.market.model.Line;
import fr.guiguilechat.jcelechat.libs.spring.market.services.LineService;

@RestController
@RequestMapping("/api/market")
public class MarketRestController {

	@Autowired
	private LineService lService;

	record TypeMarketDTO(double minSO, long volSO, long volSO1pct, long volSO5pct, double maxBO, long volBO,
			long volBO1pct, long volBO5pct) {
		static TypeMarketDTO of(List<Line> bos, List<Line> sos) {
			double so = Double.NaN;
			long volSO = 0l;
			long volSO1pct = 0l;
			long volSO5pct = 0l;
			if (!sos.isEmpty()) {
				so = sos.get(0).getOrder().price;
				for (Line l : sos) {
					volSO += l.getOrder().volume_remain;
					if (l.getOrder().price <= 1.01 * so) {
						volSO1pct += l.getOrder().volume_remain;
					}
					if (l.getOrder().price <= 1.05 * so) {
						volSO5pct += l.getOrder().volume_remain;
					}
				}
			}
			double bo = Double.NaN;
			long volBO = 0l;
			long volBO1pct = 0l;
			long volBO5pct = 0l;
			if (!bos.isEmpty()) {
				bo = bos.get(bos.size() - 1).getOrder().price;
				for (Line l : bos) {
					volBO += l.getOrder().volume_remain;
					if (l.getOrder().price >= 0.99 * bo) {
						volBO1pct += l.getOrder().volume_remain;
					}
					if (l.getOrder().price >= 0.95 * bo) {
						volBO5pct += l.getOrder().volume_remain;
					}
				}
			}
			return new TypeMarketDTO(so, volSO, volSO1pct, volSO5pct, bo, volBO, volBO1pct, volBO5pct);
		}
	}

	@GetMapping("/jita/byTypeId/{typeId}")
	public ResponseEntity<?> jitaByType(@PathVariable int typeId, @RequestParam Optional<String> accept) {
		List<Line> bos = lService.forJita(typeId, true);
		List<Line> sos = lService.forJita(typeId, false);
		return makeResponse(bos, sos, accept);
	}

	@GetMapping("/byRegionId/{regionId}/byTypeId/{typeId}")
	public ResponseEntity<?> byRegionByType(@PathVariable int regionId, @PathVariable int typeId,
			@RequestParam Optional<String> accept) {
		List<Line> bos = lService.forRegion(regionId, typeId, true);
		List<Line> sos = lService.forRegion(regionId, typeId, false);
		return makeResponse(bos, sos, accept);
	}

	@GetMapping("/byLocationId/{locationId}/byTypeId/{typeId}")
	public ResponseEntity<?> byLocationByType(@PathVariable long locationId, @PathVariable int typeId,
			@RequestParam Optional<String> accept) {
		List<Line> bos = lService.forLocation(locationId, typeId, true);
		List<Line> sos = lService.forLocation(locationId, typeId, false);
		return makeResponse(bos, sos, accept);
	}

	ResponseEntity<?> makeResponse(List<Line> bos, List<Line> sos, Optional<String> accept) {
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
		ResponseEntity<TypeMarketDTO> ret = new ResponseEntity<>(TypeMarketDTO.of(bos, sos), responseHeaders,
				HttpStatus.OK);
		return ret;
	}

}
