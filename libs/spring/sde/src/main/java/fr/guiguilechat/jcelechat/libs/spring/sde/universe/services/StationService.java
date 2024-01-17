package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.StationRepository;

@Service
public class StationService {

	@Autowired
	private StationRepository repo;

	public void clear() {
		repo.deleteAll();
		repo.flush();
	}

	public List<Station> saveAll(Iterable<Station> entities) {
		return repo.saveAll(entities);
	}

	public Station findById(int stationId) {
		return repo.findById(stationId).orElse(null);
	}

}
