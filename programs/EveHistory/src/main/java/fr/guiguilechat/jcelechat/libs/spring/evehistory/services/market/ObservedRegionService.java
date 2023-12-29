package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.ObservedRegionRepository;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;


@Service
public class ObservedRegionService {

	@Autowired
	private ObservedRegionRepository repo;

	public ObservedRegion save(ObservedRegion entity) {
		return repo.save(entity);
	}

	public List<Integer> observedRegions() {
		return repo.listObservedRegionIds();
	}

	public Map<ObservedRegion, MarketFetchResult> listRequests() {
		Map<ObservedRegion, MarketFetchResult> ret = new HashMap<>();
		repo.listRequests().stream().forEach(arr -> ret.put((ObservedRegion) arr[0], (MarketFetchResult) arr[1]));
		return ret;
	}

	public boolean existsByRegionId(int regionId) {
		return repo.existsByRegionId(regionId);
	}

	public ObservedRegion byRegionId(int regionId) {
		return repo.findByRegionId(regionId);
	}

	public boolean observeRegion(Region region) {
		if (region == null) {
			return false;
		}
		ObservedRegion data = repo.findByRegionId(region.id);
		if (data == null) {
			save(ObservedRegion.builder().regionId(region.id).build());
		} else {
			data.setActive(true);
		}
		return true;
	}

	public boolean observeRegion(int regionId) {
		return observeRegion(Region.loadById().get(regionId));
	}

	public void observeRegions(int... regionIds) {
		if (regionIds != null) {
			observeRegions(IntStream.of(regionIds).mapToObj(Region.loadById()::get).filter(o -> o != null).toList());
		}
	}

	public void observeRegions(Iterable<Region> regions) {
		if (regions != null) {
			for (Region region : regions) {
				observeRegion(region);
			}
		}
	}

	/**
	 * create if needed the corresponding region observation.
	 *
	 * @param regionId
	 * @return the existing region if present, or a new one that is not observed if
	 *           absent.
	 */
	public ObservedRegion forRegion(int regionId) {
		ObservedRegion ret = repo.findByRegionId(regionId);
		if (ret == null) {
			ret = ObservedRegion.builder().regionId(regionId).active(false).build();
			save(ret);
		}
		return ret;
	}
}
