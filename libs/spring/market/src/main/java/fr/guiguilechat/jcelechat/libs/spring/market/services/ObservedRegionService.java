package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.ObservedRegionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObservedRegionService {

	final private ObservedRegionRepository repo;

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

	@Value(value = "${market.regions.add:}")
	private List<Integer> defaultRegions;

	@PostConstruct
	public void addDefaultRegions() {
		if (defaultRegions != null && !defaultRegions.isEmpty()) {
			for (Integer rid : defaultRegions) {
				if (rid == null) {
					continue;
				}
				if (!repo.existsById(rid)) {
					log.info("activate default region " + rid);
					activate(rid);
				}
			}
		}
	}

}
