package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.ConstellationRepository;

@Service
public class ConstellationService {

	@Autowired
	private ConstellationRepository repo;

	public void clear() {
		repo.deleteAll();
	}

	public void saveAll(Iterable<Constellation> entities) {
		repo.saveAll(entities);
	}

}