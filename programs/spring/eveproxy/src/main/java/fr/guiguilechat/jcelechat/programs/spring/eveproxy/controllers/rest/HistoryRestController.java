package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService.PriceVolumeAcc;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService.WeightStrategy;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryRestController {

	final private HistoryLineService hlService;

	private final int NB_STEPS = 10;


	record HistoryAggreg(int typeId, Integer regionId, String weightName, int steps) {
	}

	record PriceVolume(BigDecimal price, BigDecimal volumeAbove, BigDecimal volumeBelow) {
	}

	record DailyExchanges(HistoryAggreg filter, List<PriceVolume> volumes) {

		static List<PriceVolume> convertVolumes(List<PriceVolumeAcc> groups) {
			ArrayList<PriceVolume> ret = new ArrayList<>();
			groups.forEach(pv -> ret.add(new PriceVolume(
					new BigDecimal(pv.price).setScale(2, RoundingMode.HALF_EVEN),
					new BigDecimal(pv.above).setScale(2, RoundingMode.HALF_EVEN),
					new BigDecimal(pv.below).setScale(2, RoundingMode.HALF_EVEN))));
			return ret;
		}


		public static DailyExchanges of(HistoryAggreg filter, List<PriceVolumeAcc> groups) {
			return new DailyExchanges(filter, convertVolumes(groups));
		}
	}

	@GetMapping("/byRegionId/{regionId}/byTypeId/{typeId}/byWeightName/{weightname}/daily")
	public ResponseEntity<DailyExchanges> byRegionByType(@PathVariable int regionId, @PathVariable int typeId,
			@PathVariable String weightname,
			@RequestParam Optional<String> accept) {
		WeightStrategy weighter = WeightStrategy.of(weightname);
		List<PriceVolumeAcc> priceVolumes = hlService.groupPrices(regionId, typeId, weighter, NB_STEPS);
		return RestControllerHelper.makeResponse(
				DailyExchanges.of(new HistoryAggreg(typeId, regionId, weighter.toString(), NB_STEPS), priceVolumes),
				accept);
	}

	@GetMapping("/byTypeId/{typeId}")
	public ResponseEntity<List<AggregatedHL>> byType(@PathVariable int typeId,
	    @RequestParam Optional<String> accept) {
		return RestControllerHelper.makeResponse(hlService.byType(typeId),
		    accept);
	}

}
