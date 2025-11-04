package fr.guiguilechat.jcelechat.libs.spring.sde.space.station;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import jakarta.transaction.Transactional;

@Service
public class StationService extends
		SdeEntityService<Station, Integer, StationRepository> {

	public StationService() {
		super(Station::new);
	}

	@Transactional
	public Map<Long, SolarSystem> getSolarSystems(Iterable<Integer> stationIds) {
		return repo().findAllByIdIn(stationIds)
		    .filter(sta -> sta.getSolarSystem() != null)
		    .collect(Collectors.toMap(sta -> (long) sta.getId(), Station::getSolarSystem));
	}

	@Transactional
	public Map<Integer, String> resolveNames(Iterable<Integer> stationIds) {
		return repo().findAllByIdIn(stationIds)
				.collect(Collectors.toMap(Station::getId, Station::name));
	}


}
