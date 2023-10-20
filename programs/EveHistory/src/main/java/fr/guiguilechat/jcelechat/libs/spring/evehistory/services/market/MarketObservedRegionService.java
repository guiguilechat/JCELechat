package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketObservedRegionRepository;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;


@Service
public class MarketObservedRegionService {

	@Autowired
	private MarketObservedRegionRepository repo;

	private MarketObservedRegion save(MarketObservedRegion entity) {
		return repo.save(entity);
	}

	public List<Integer> observedRegions() {
		return repo.listRegionIds();
	}

	public boolean existsByRegionId(int regionId) {
		return repo.existsByRegionId(regionId);
	}

	public boolean observeRegion(Region region) {
		if (region == null) {
			return false;
		}
		if (!existsByRegionId(region.id)) {
			save(MarketObservedRegion.builder().regionId(region.id).build());
		}
		return true;
	}

	public boolean observeRegion(int regionId) {
		return observeRegion(Region.loadById().get(regionId));
	}

	public void observeRegions(int... regionIds) {
		if (regionIds != null) {
			for (int regionId : regionIds) {
				observeRegion(regionId);
			}
		}
	}

	public void observeRegions(Iterable<Region> regions) {
		if (regions != null) {
			for (Region region : regions) {
				observeRegion(region);
			}
		}
	}
}
