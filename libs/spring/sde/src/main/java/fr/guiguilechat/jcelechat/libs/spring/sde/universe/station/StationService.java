package fr.guiguilechat.jcelechat.libs.spring.sde.universe.station;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class StationService extends
		SdeEntityService<Station, Integer, StationRepository> {

	public StationService() {
		super(Station::new);
	}

	public Map<Integer, String> namesById() {
		return repo().findAll().stream().collect(Collectors.toMap(Station::getId, s -> "station" + s.getId()));
	}

	public Map<Long, SolarSystem> getSolarSystems(Iterable<Integer> stationIds) {
		return repo().findAllByIdIn(stationIds)
		    .filter(sta -> sta.getSolarSystem() != null)
		    .collect(Collectors.toMap(sta -> (long) sta.getId(), Station::getSolarSystem));
	}

}
