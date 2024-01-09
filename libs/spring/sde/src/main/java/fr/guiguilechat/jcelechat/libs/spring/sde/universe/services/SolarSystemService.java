package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.SolarSystemRepository;

@Service
public class SolarSystemService {

	@Autowired
	private SolarSystemRepository repo;

	public void clear() {
		repo.deleteAll();
	}

	public void saveAll(Iterable<SolarSystem> entities) {
		repo.saveAll(entities);
	}

	public SolarSystem findById(int solarSystemId) {
		return repo.findById(solarSystemId).orElse(null);
	}

}
