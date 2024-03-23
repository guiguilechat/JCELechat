package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.SolarSystemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolarSystemService {

	final private SolarSystemRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<SolarSystem> saveAll(Iterable<SolarSystem> entities) {
		entities.forEach(SolarSystem::updateValues);
		return repo.saveAll(entities);
	}

	public SolarSystem findById(int solarSystemId) {
		return repo.findById(solarSystemId).orElse(null);
	}

	public List<SolarSystem> adjacent(SolarSystem source) {
		return repo.adjacent(source);
	}

}
