package fr.guiguilechat.jcelechat.libs.spring.universe.station;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResourceService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.station")
// depend on solarsystem type
@Order(4)
public class StationService extends
    ARemoteResourceService<Station, Integer, R_get_universe_stations_station_id, StationRepository> {

	@Lazy
	private final SolarSystemService solarSystemService;

	@Lazy
	private final TypeService typeService;

	@Override
	protected Station create(Integer entityId) {
		if (entityId == 30000001) {
			throw new RuntimeException();
		}
		Station ret = new Station();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_stations_station_id> fetchData(Integer id,
	    Map<String, String> properties) {
		Requested<R_get_universe_stations_station_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_stations(id, properties);
		return ret;
	}

	protected void updateResponseOk(Station data, R_get_universe_stations_station_id received,
	    Map<Integer, SolarSystem> idToSystem, Map<Integer, Type> idToType) {
		data.setSolarSystem(idToSystem.get(received.system_id));
		data.setType(idToType.get(received.type_id));
	}

	@Override
	protected void updateResponseOk(Map<Station, R_get_universe_stations_station_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, SolarSystem> idToSystem = solarSystemService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.system_id).distinct().toList());
		Map<Integer, Type> idToType = typeService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.type_id).distinct().toList());
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToSystem, idToType));
	}

	public Map<Integer, String> namesById() {
		return repo().findAll().stream().collect(Collectors.toMap(Station::getId, Station::name));
	}

}
