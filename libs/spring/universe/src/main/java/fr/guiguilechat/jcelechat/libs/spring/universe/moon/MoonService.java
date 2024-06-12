package fr.guiguilechat.jcelechat.libs.spring.universe.moon;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_moons_moon_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.moon")
public class MoonService extends
    ARemoteFetchedResourceService<Moon, Integer, R_get_universe_moons_moon_id, MoonRepository> {

	@Lazy
	private final SolarSystemService solarSystemService;

	@Override
	protected Moon create(Integer entityId) {
		Moon ret = new Moon();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_moons_moon_id> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_universe_moons_moon_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_moons(id, properties);
		return ret;
	}

	@Override
	protected void updateResponseOk(Moon data, Requested<R_get_universe_moons_moon_id> response) {
		super.updateResponseOk(data, response);
		data.setSolarSystem(solarSystemService.createIfAbsent(response.getOK().system_id));
	}

}
