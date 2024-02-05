package fr.guiguilechat.jcelechat.libs.spring.evehistory.market.controllers.rest;

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
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.services.MarketFetchLineService;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;

@RestController
@RequestMapping("/api/market/line")
public class RestMarketFetchLine {

	@Autowired
	private MarketFetchLineService service;

	/**
	 * list the order updates . Defaults period to [now-1d:now[ . Starting date
	 * can't be lower than end date -1d
	 */
	@GetMapping("/region/{regionId}/type/{typeId}/updates")
	public ResponseEntity<?> updates(@PathVariable int regionId, @PathVariable int typeId,
			@RequestParam Optional<String> from, @RequestParam Optional<String> to) {
		if (Region.getRegion(regionId) == null) {
			return ResponseEntity.status(404).body("region " + regionId + " not found");
		}
		Instant toDate = Instant.now();
		if (to.isPresent()) {
			toDate = ESITools.fieldInstant(to.get());
		}
		Instant fromDate = toDate.minus(Duration.ofDays(1));
		if (from.isPresent()) {
			Instant requestFromDate = ESITools.fieldInstant(from.get());
			if (requestFromDate.isAfter(fromDate)) {
				fromDate=requestFromDate;
			}
		}
		record LineUpdate(long orderid, Instant issued, double price, int volume, boolean isBuyOrder) {
			public LineUpdate(MarketFetchLine line) {
				this(line.getOrder().order_id, line.getIssuedDate(), line.getOrder().price, line.getOrder().volume_remain,
						line.getOrder().is_buy_order);
			}
		}
		return ResponseEntity
				.ok(service.listUpdates(regionId, typeId, fromDate, toDate).stream().map(LineUpdate::new).toList());
	}

	/**
	 */
	@GetMapping("/region/{regionId}/type/{typeId}/daily")
	public ResponseEntity<?> sales(@PathVariable int regionId, @PathVariable int typeId,
			@RequestParam Optional<String> from, @RequestParam Optional<String> to) {
		if (Region.getRegion(regionId) == null) {
			return ResponseEntity.status(404).body("region " + regionId + " not found");
		}
		Instant toDate = Instant.now();
		if (to.isPresent()) {
			toDate = ESITools.fieldInstant(to.get());
		}
		Instant fromDate = toDate.minus(Duration.ofDays(30));
		if (from.isPresent()) {
			Instant requestFromDate = ESITools.fieldInstant(from.get());
			if (requestFromDate.isAfter(fromDate)) {
				fromDate = requestFromDate;
			}
		}
		return ResponseEntity
				.ok(service.listDailyOnTypeRegionFromTo(regionId, typeId, fromDate, toDate));
	}

	/**
	 */
	@GetMapping("/errors/daily")
	public ResponseEntity<?> dailyErrors() {
		return ResponseEntity
				.ok(service.listDailyLineErrorsGroupeByRegion());
	}

	/**
	 */
	@GetMapping("/region/{regionId}/stats")
	public ResponseEntity<?> hourlyStats(@PathVariable int regionId,
			@RequestParam Optional<String> from, @RequestParam Optional<String> to) {
		if (Region.getRegion(regionId) == null) {
			return ResponseEntity.status(404).body("region " + regionId + " not found");
		}
		Instant toDate = Instant.now();
		if (to.isPresent()) {
			toDate = ESITools.fieldInstant(to.get());
		}
		Instant fromDate = toDate.minus(Duration.ofDays(30));
		if (from.isPresent()) {
			Instant requestFromDate = ESITools.fieldInstant(from.get());
			if (requestFromDate.isAfter(fromDate)) {
				fromDate = requestFromDate;
			}
		}
		return ResponseEntity
				.ok(service.getLinesHourStatsForRegion(regionId, fromDate, toDate));
	}
}
