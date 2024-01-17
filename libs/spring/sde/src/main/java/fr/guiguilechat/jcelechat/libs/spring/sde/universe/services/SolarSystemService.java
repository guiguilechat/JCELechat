package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.SolarSystemRepository;

@Service
public class SolarSystemService {

	@Autowired
	private SolarSystemRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<SolarSystem> saveAll(Iterable<SolarSystem> entities) {
		return repo.saveAll(entities);
	}

	public SolarSystem findById(int solarSystemId) {
		return repo.findById(solarSystemId).orElse(null);
	}

	public List<SolarSystem> adjacent(SolarSystem source) {
		return repo.adjacent(source);
	}

}
