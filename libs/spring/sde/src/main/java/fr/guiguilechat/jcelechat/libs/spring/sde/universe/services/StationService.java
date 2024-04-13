package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.StationRepository;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.SDEUpdateService.SdeUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StationService implements SdeUpdateListener {

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

	public Map<Integer, Station> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Station::getStationId, c -> c));
	}

	@Cacheable("SDEStationsNameById")
	public Map<Integer, String> namesById() {
		return repo.findAll().stream().collect(Collectors.toMap(Station::getStationId, Station::getName));
	}

	private final static List<String> caches = List.of(
			"SDEStationsNameById");

	@Override
	public List<String> listSDECaches() {
		return caches;
	}

}
