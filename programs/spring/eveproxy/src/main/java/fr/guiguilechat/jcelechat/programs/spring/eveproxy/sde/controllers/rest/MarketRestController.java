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

	record TypeMarketDTO(double minSO, long volSO, long volSO5pct, double maxBO, long volBO, long volBO5pct) {
		static TypeMarketDTO of(List<Line> bos, List<Line> sos) {
			double so = Double.NaN;
			long volSO = 0l;
			long volSO5pct = 0l;
			if (!sos.isEmpty()) {
				so = sos.get(0).getOrder().price;
				for (Line l : sos) {
					volSO += l.getOrder().volume_remain;
					if (l.getOrder().price <= 1.05 * so) {
						volSO5pct += l.getOrder().volume_remain;
					}
				}
			}
			double bo = Double.NaN;
			long volBO = 0l;
			long volBO5pct = 0l;
			if (!bos.isEmpty()) {
				bo = bos.get(bos.size() - 1).getOrder().price;
				for (Line l : bos) {
					volBO += l.getOrder().volume_remain;
					if (l.getOrder().price >= 0.95 * bo) {
						volBO5pct += l.getOrder().volume_remain;
					}
				}
			}
			return new TypeMarketDTO(so, volSO, volSO5pct, bo, volBO, volBO5pct);
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

	@GetMapping("/byLocation/{locationId}/byType/{typeId}")
	public ResponseEntity<?> byLocationByType(@PathVariable long locationId, @PathVariable int typeId) {
		List<Line> bos = lService.forLocation(locationId, typeId, true);
		List<Line> sos = lService.forLocation(locationId, typeId, false);
		return ResponseEntity.ok(TypeMarketDTO.of(bos, sos));
	}

}
