package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SDEUpdateService.SdeUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.ObservedRegionService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** when the SDE is updated, add all the regions to be observed */
@Slf4j
@Service
@RequiredArgsConstructor
public class MarketSyncService implements SdeUpdateListener {

	final private RegionService rService;

	final private ObservedRegionService orService;

	@Value("${eveproxy.market.regionsskip:false}")
	private boolean skipRegionSync;

	public void observeAllEveUni() {
		if (skipRegionSync) {
			return;
		}
		Set<Integer> observed = orService.listActiveMarket().stream().map(ObservedRegion::getRegionId)
				.collect(Collectors.toSet());
		List<Region> toObserve = rService.byUniverse("eve").stream().filter(r -> !observed.contains(r.getId()))
				.toList();
		if (!toObserve.isEmpty()) {
			log.info("adding " + toObserve.size() + " new market regions to observe");
		}
		for (Region r : toObserve) {
			orService.activateMarket(r.getId());
			orService.activateContracts(r.getId());
		}
	}

	@Override
	public void afterSdeUpdate() {
		observeAllEveUni();
	}

}
