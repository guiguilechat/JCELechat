package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.services.ObservedRegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketSyncService {

	@Autowired
	private RegionService rService;

	@Autowired
	private ObservedRegionService orService;

	@Scheduled(fixedRateString = "${proxy.sync.fetchperiod:3600000}", initialDelayString = "${proxy.sync.fetchdelay:30000}")
	public void observeAllEveUni() {
		Set<Integer> observed = orService.listActive().stream().map(ObservedRegion::getRegionId)
				.collect(Collectors.toSet());
		List<Region> toObserve = rService.byUniverse("eve").stream().filter(r -> !observed.contains(r.getRegionId()))
				.toList();
		if (!toObserve.isEmpty()) {
			log.info("adding " + toObserve.size() + " new regions to observe");
		}
		for (Region r : toObserve) {
				orService.activate(r.getRegionId());
		}
	}

}
