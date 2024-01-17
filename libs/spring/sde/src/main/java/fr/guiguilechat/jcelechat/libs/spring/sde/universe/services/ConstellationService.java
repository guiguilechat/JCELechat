package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

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
		repo.flush();
	}

	public List<Constellation> saveAll(Iterable<Constellation> entities) {
		return repo.saveAll(entities);
	}

}
