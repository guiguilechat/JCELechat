package fr.guiguilechat.jcelechat.libs.spring.trade2.regional;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObservedRegionService {

	final private ObservedRegionRepository repo;

	public ObservedRegion save(ObservedRegion region) {
		return repo.save(region);
	}

	// market

	public void activateMarket(int regionId, boolean active) {
		ObservedRegion stored = repo.findById(regionId).orElse(null);
		if (stored == null) {
			if (active) {
				repo.save(ObservedRegion.builder()
						.regionId(regionId)
						.activeMarket(true)
						.build());
			}
		} else if (active != stored.isActiveMarket()) {
			stored.setActiveMarket(active);
			repo.save(stored);
		}
	}

	public void activateMarket(int regionId) {
		activateMarket(regionId, true);
	}

	public void deActivateMarket(int regionId) {
		activateMarket(regionId, false);
	}

	public List<ObservedRegion> listActiveMarket() {
		return repo.findByActiveMarketTrue();
	}

	// contract

	public void activateContracts(int regionId, boolean active) {
		ObservedRegion stored = repo.findById(regionId).orElse(null);
		if (stored == null) {
			if (active) {
				repo.save(ObservedRegion.builder()
						.regionId(regionId)
						.activeContracts(true)
						.build());
			}
		} else if (active != stored.isActiveContracts()) {
			stored.setActiveContracts(active);
			repo.save(stored);
		}
	}

	public void activateContracts(int regionId) {
		activateContracts(regionId, true);
	}

	public void deActivateContracts(int regionId) {
		activateContracts(regionId, false);
	}

	public List<ObservedRegion> listActiveContracts() {
		return repo.findByActiveContractsTrue();
	}

	// auto inject

	@Value(value = "${market.regions.add:}")
	private List<Integer> defaultMarketRegions;

	@Value(value = "${contracts.regions.add:}")
	private List<Integer> defaultContractsRegions;

	@PostConstruct
	public void addDefaultRegions() {
		if (defaultMarketRegions != null && !defaultMarketRegions.isEmpty()) {
			for (Integer rid : defaultMarketRegions) {
				if (rid == null) {
					continue;
				}
				if (!repo.existsById(rid)) {
					log.info("activate market in default region " + rid);
					activateMarket(rid);
				}
			}
		}
		if (defaultContractsRegions != null && !defaultContractsRegions.isEmpty()) {
			for (Integer rid : defaultContractsRegions) {
				if (rid == null) {
					continue;
				}
				if (!repo.existsById(rid)) {
					log.info("activate contracts in default region " + rid);
					activateContracts(rid);
				}
			}
		}
	}

}
