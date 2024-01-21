package fr.guiguilechat.jcelechat.libs.spring.mer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.Kill;
import fr.guiguilechat.jcelechat.libs.spring.mer.repositories.KillRepository;

@Service
public class KillService {

	@Autowired
	private KillRepository repo;

	public void saveAll(Iterable<Kill> entities) {
		repo.saveAll(entities);
	}

	public Kill save(Kill entity) {
		return repo.save(entity);
	}

}
