package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.ObservedRegionRepository;

@Service
public class ObservedRegionService {

	@Autowired
	private ObservedRegionRepository repo;

	public void activate(int regionId, boolean active) {
		ObservedRegion stored = repo.findById(regionId).orElse(null);
		if (stored == null) {
			if (active) {
				repo.save(ObservedRegion.builder().regionId(regionId).build());
			}
		} else if (active != stored.isActive()) {
			stored.setActive(active);
			repo.save(stored);
		}
	}

	public void activate(int regionId) {
		activate(regionId, true);
	}

	public void deActivate(int regionId) {
		activate(regionId, false);
	}

	public List<ObservedRegion> listActive() {
		return repo.findByActiveTrue();
	}

	public ObservedRegion save(ObservedRegion region) {
		return repo.save(region);
	}

}
