package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanetService {

	private final PlanetRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Planet> saveAll(Iterable<Planet> entities) {
		return repo.saveAll(entities);
	}

}
