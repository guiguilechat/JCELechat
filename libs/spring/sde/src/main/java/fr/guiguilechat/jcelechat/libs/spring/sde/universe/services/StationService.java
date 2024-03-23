package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.StationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StationService {

	final private StationRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Station> saveAll(Iterable<Station> entities) {
		return repo.saveAll(entities);
	}

	public Station findById(int stationId) {
		return repo.findById(stationId).orElse(null);
	}

}
