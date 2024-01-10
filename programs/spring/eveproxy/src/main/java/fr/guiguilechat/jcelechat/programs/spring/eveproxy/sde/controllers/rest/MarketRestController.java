package fr.guiguilechat.jcelechat.programs.spring.eveproxy.sde.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.market.model.Line;
import fr.guiguilechat.jcelechat.libs.spring.market.services.LineService;

@RestController
@RequestMapping("/api/market")
public class MarketRestController {

	@Autowired
	private LineService lService;

	record TypeMarketDTO(double minSO, double maxBO) {
		static TypeMarketDTO of(List<Line> bos, List<Line> sos) {
			double so = sos.isEmpty() ? Double.NaN : sos.get(0).getOrder().price;
			double bo = bos.isEmpty() ? Double.NaN : bos.get(bos.size() - 1).getOrder().price;
			return new TypeMarketDTO(so, bo);
		}
	}

	@GetMapping("/jita/byType/{typeId}")
	public ResponseEntity<?> jitaByType(@PathVariable int typeId) {
		List<Line> bos = lService.forJita(typeId, true);
		List<Line> sos = lService.forJita(typeId, false);
		return ResponseEntity.ok(TypeMarketDTO.of(bos, sos));
	}

	@GetMapping("/byRegion/{regionId}/byType/{typeId}")
	public ResponseEntity<?> byRegionByType(@PathVariable int regionId, @PathVariable int typeId) {
		List<Line> bos = lService.forRegion(regionId, typeId, true);
		List<Line> sos = lService.forRegion(regionId, typeId, false);
		return ResponseEntity.ok(TypeMarketDTO.of(bos, sos));
	}

	@GetMapping("/byRegion/{locationId}/byType/{typeId}")
	public ResponseEntity<?> byLocationByType(@PathVariable long locationId, @PathVariable int typeId) {
		List<Line> bos = lService.forLocation(locationId, typeId, true);
		List<Line> sos = lService.forLocation(locationId, typeId, false);
		return ResponseEntity.ok(TypeMarketDTO.of(bos, sos));
	}

}
