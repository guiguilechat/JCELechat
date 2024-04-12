package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

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

}
