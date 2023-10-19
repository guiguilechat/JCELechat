package fr.guiguilechat.jcelechat.libs.spring.evehistory.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.MarketFetchResultRepository;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;

@Service
public class MarketFetchResultService {

	@Autowired
	private MarketFetchResultRepository repo;

	@Autowired
	private MarketFetchLineService lineService;

	public MarketFetchResult save(MarketFetchResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.save(entity);
	}

	public List<MarketFetchResult> findLastResults() {
		List<MarketFetchResult> ret = repo.findLastResults();
		return ret;
	}

	public boolean existsByRegionId(int regionId) {
		return repo.existsByRegionId(regionId);
	}

	public boolean observeRegion(int regionId) {
		Region region = Region.loadById().get(regionId);
		if (region == null) {
			return false;
		}
		if (!repo.existsByRegionId(regionId)) {
			repo.save(MarketFetchResult.builder().failed(true).regionId(regionId).build());
		}
		return true;
	}

	public void observeRegions(int... regionIds) {
		if (regionIds != null) {
			for (int regionId : regionIds) {
				observeRegion(regionId);
			}
		}
	}

	public List<Integer> observedRegions() {
		return repo.listRegionIds();
	}

	public List<MarketFetchResult> findAnalyzable() {
		return repo.findAnalyzable();
	}

	/**
	 * analyze a market fetch result. Update all orders in that result to represent
	 * if they are newly created, if they are the last time this order appear, if
	 * the price of volume has been updated since previous result. Then remove all
	 * the orders which are none of those, and update the result to have the number
	 * of retained orders.
	 *
	 */
	@Transactional(propagation = Propagation.NESTED, isolation = Isolation.SERIALIZABLE)
	public void analyze(MarketFetchResult result) {
		lineService.analyzeLines(result);
		result.setLinesUpdated(lineService.countOrders(result));
		result.setAnalyzed(true);
		repo.save(result);
	}

}
