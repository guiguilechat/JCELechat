package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.RegionRepository;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.SDEUpdateService.SdeUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService implements SdeUpdateListener {

	final private RegionRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Region> saveAll(Iterable<Region> entities) {
		return repo.saveAll(entities);
	}

	public List<Region> byUniverse(String universe) {
		return repo.findByUniverse(universe);
	}

	public Optional<Region> byId(int regionId) {
		return repo.findById(regionId);
	}

	public List<Region> byName(String name) {
		return repo.findByNameEqualsIgnoreCase(name);
	}

	@Cacheable("SDERegionsNameById")
	public Map<Integer, String> namesById() {
		return repo.findAll().stream().collect(Collectors.toMap(Region::getRegionId, Region::getName));
	}

	private final static List<String> caches = List.of(
			"SDERegionsNameById");

	@Override
	public List<String> listSDECaches() {
		return caches;
	}

}
