package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public Map<Long, Planet> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Planet::getPlanetId, c -> c));
	}

}
