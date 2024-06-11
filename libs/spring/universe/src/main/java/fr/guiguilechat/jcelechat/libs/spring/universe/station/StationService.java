package fr.guiguilechat.jcelechat.libs.spring.universe.station;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.station")
public class StationService extends
    ARemoteFetchedResourceService<Station, Integer, R_get_universe_stations_station_id, StationRepository> {

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

	@Override
	protected void updateResponseOk(Station data,
	    Requested<R_get_universe_stations_station_id> response) {
		super.updateResponseOk(data, response);
		R_get_universe_stations_station_id received = response.getOK();
		data.setSolarSystem(solarSystemService.createIfAbsent(received.system_id));
		data.setType(typeService.createIfAbsent(received.type_id));
	}

}
