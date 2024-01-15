package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.RegionRepository;

@Service
public class RegionService {

	@Autowired
	private RegionRepository repo;

	public void clear() {
		repo.deleteAll();
	}

	public List<Region> saveAll(Iterable<Region> entities) {
		return repo.saveAll(entities);
	}

	public List<Region> byUniverse(String universe) {
		return repo.findByUniverse(universe);
	}

}
