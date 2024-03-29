package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.RegionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {

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

}
