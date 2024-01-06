package fr.guiguilechat.jcelechat.libs.spring.evehistory.controllers.rest.market;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.MarketFetchResultService;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.ObservedRegionService;

@RestController
@RequestMapping("/api/market/result")
public class RestMarketFetchResult {

	@Autowired
	private MarketFetchResultService service;

	@Autowired
	private ObservedRegionService orService;

	record ResultTimes(Instant created, long fetchMS, long createOrdersMS, long analyzeMS, int lines) {
		public ResultTimes(MarketFetchResult mfr) {
			this(mfr.getCreatedDate(), mfr.getDurationFetchMS(), mfr.getDurationCreateOrderMS(), mfr.getDurationAnalyzeMS(),
					mfr.getLinesFetched());
		}
	}

	@GetMapping("/region/{regionId}/times")
	public ResponseEntity<?> updates(@PathVariable int regionId) {
		ObservedRegion or = orService.byRegionId(regionId);
		if (or == null) {
			return ResponseEntity.status(404).body("region " + regionId + " not observed");
		}
		return ResponseEntity
				.ok(service.listInRegionAndStatus(or, STATUS.LINESANALYZED).stream().map(ResultTimes::new).toList());
	}

}
