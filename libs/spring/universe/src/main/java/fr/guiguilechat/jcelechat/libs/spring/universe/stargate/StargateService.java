package fr.guiguilechat.jcelechat.libs.spring.universe.stargate;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stargates_stargate_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.stargate")
public class StargateService extends
    ARemoteFetchedResourceService<Stargate, Integer, R_get_universe_stargates_stargate_id, StargateRepository> {

	@Lazy
	private final SolarSystemService solarSystemService;

	@Override
	protected Stargate create(Integer entityId) {
		Stargate ret = new Stargate();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_stargates_stargate_id> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_universe_stargates_stargate_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_stargates(id, properties);
		return ret;
	}

	@Override
	protected void updateResponseOk(Stargate data, Requested<R_get_universe_stargates_stargate_id> response) {
		super.updateResponseOk(data, response);
		data.setSolarSystem(solarSystemService.createIfAbsent(response.getOK().system_id));
		data.setDestination(createIfAbsent(response.getOK().destination.stargate_id));
	}

}
