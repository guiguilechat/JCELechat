package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.ConstellationService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.solarsystem")
public class SolarSystemService extends
    ARemoteFetchedResourceService<SolarSystem, Integer, R_get_universe_systems_system_id, SolarSystemRepository> {

	@Lazy
	private final ConstellationService constellationService;

	@Override
	protected SolarSystem create(Integer entityId) {
		SolarSystem ret = new SolarSystem();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_systems_system_id> fetchData(Integer id,
	    Map<String, String> properties) {
		Requested<R_get_universe_systems_system_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_systems(id, properties);
		return ret;
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_systems(p).mapBody(List::of);
	}

	@Override
	protected void updateResponseOk(SolarSystem data,
	    Requested<R_get_universe_systems_system_id> response) {
		super.updateResponseOk(data, response);
		data.setConstellation(constellationService.createIfAbsent(response.getOK().constellation_id));
	}

}
