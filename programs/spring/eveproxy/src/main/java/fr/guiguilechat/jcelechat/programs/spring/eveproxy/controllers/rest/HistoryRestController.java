package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.market.services.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.HistoryLineService.PriceVolumeAcc;
import fr.guiguilechat.jcelechat.libs.spring.market.services.HistoryLineService.WeightStrategy;

@RestController
@RequestMapping("/api/history")
public class HistoryRestController {

	@Autowired
	private HistoryLineService hlService;

	private final int NB_STEPS = 5;


	record HistoryAggreg(int typeId, Integer regionId, String weightName) {
	}

	record PriceVolume(double price, double volume) {
	}

	record DailyExchanges(HistoryAggreg filter, List<PriceVolume> below, List<PriceVolume> above) {

		static List<PriceVolume> convertBelow(List<PriceVolumeAcc> groups) {
			ArrayList<PriceVolume> ret = new ArrayList<>();
			groups.forEach(pv -> ret.add(new PriceVolume(pv.price, pv.below)));
			return ret;
		}

		static List<PriceVolume> convertAbove(List<PriceVolumeAcc> groups) {
			ArrayList<PriceVolume> ret = new ArrayList<>();
			groups.forEach(pv -> ret.add(new PriceVolume(pv.price, pv.above)));
			return ret;
		}

		public DailyExchanges(HistoryAggreg filter, List<PriceVolumeAcc> groups) {
			this(filter, convertBelow(groups), convertAbove(groups));
		}
	}

	@GetMapping("/byRegionId/{regionId}/byTypeId/{typeId}/byWeightName/{weightname}/daily")
	public ResponseEntity<?> byRegionByType(@PathVariable int regionId, @PathVariable int typeId,
			@PathVariable String weightname,
			@RequestParam Optional<String> accept) {
		WeightStrategy weighter = WeightStrategy.of(weightname);
		List<PriceVolumeAcc> priceVolumes = hlService.groupPrices(regionId, typeId, weighter, NB_STEPS);
		return RestControllerHelper.makeResponse(
				new DailyExchanges(new HistoryAggreg(typeId, regionId, weighter.toString()), priceVolumes), accept);
	}

}
