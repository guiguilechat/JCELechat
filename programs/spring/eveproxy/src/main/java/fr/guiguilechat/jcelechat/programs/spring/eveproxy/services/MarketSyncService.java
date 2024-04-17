package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.services.ObservedRegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.SDEUpdateService.SdeUpdateListener;
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
		List<Region> toObserve = rService.byUniverse("eve").stream().filter(r -> !observed.contains(r.getRegionId()))
				.toList();
		if (!toObserve.isEmpty()) {
			log.info("adding " + toObserve.size() + " new market regions to observe");
		}
		for (Region r : toObserve) {
			orService.activateMarket(r.getRegionId());
			orService.activateContracts(r.getRegionId());
		}
	}

	@Override
	public void onSDEUpdate() {
		observeAllEveUni();
	}

}
