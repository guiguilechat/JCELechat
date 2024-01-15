package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.StargateRepository;

@Service
public class StargateService {

	@Autowired
	private StargateRepository repo;

	public void clear() {
		repo.deleteAll();
	}

	public List<Stargate> saveAll(Iterable<Stargate> entities) {
		return repo.saveAll(entities);
	}

	public Stargate findById(int stargateId) {
		return repo.findById(stargateId).orElse(null);
	}

}
