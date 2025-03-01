package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.history;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService.PriceVolumeAcc;

public record DailyExchanges(HistoryAggreg filter, List<PriceVolume> volumes) {

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