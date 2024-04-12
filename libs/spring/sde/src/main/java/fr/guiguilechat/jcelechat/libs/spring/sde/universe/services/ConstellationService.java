package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.ConstellationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConstellationService {

	final private ConstellationRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Constellation> saveAll(Iterable<Constellation> entities) {
		return repo.saveAll(entities);
	}

	public Map<Integer, Constellation> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Constellation::getConstellationId, c -> c));
	}

}
