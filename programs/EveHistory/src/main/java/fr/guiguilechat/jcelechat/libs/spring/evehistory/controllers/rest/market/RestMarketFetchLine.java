package fr.guiguilechat.jcelechat.libs.spring.evehistory.controllers.rest.market;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.MarketFetchLineService;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;

@RestController
@RequestMapping("/api/market/line")
public class RestMarketFetchLine {

	@Autowired
	private MarketFetchLineService service;

	/**
	 * list the order updates . Defaults period to now, now-1h
	 */
	@GetMapping("/region/{regionId}/type/{typeId}/updates")
	public ResponseEntity<?> updates(@PathVariable int regionId, @PathVariable int typeId,
			@RequestParam Optional<String> from, @RequestParam Optional<String> to) {
		if (Region.getRegion(regionId) == null) {
			return ResponseEntity.status(404).body("region " + regionId + " not found");
		}
		Instant toDate = Instant.now();
		if (to.isPresent()) {
			toDate = ESITools.convertDate(to.get()).toInstant();
		}
		Instant fromDate = toDate.minus(Duration.ofHours(1));
		if (from.isPresent()) {
			fromDate = ESITools.convertDate(from.get()).toInstant();
		}
		return ResponseEntity.ok(service.listUpdates(regionId, typeId, fromDate, toDate));
	}
}
