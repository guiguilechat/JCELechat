package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

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

	public void saveAll(Iterable<Region> entities) {
		repo.saveAll(entities);
	}

}
