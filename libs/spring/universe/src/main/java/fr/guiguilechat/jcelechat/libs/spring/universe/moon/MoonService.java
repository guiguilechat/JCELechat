package fr.guiguilechat.jcelechat.libs.spring.universe.moon;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_moons_moon_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.moon")
// depend on solarsystem
@Order(4)
public class MoonService extends
    ARemoteEntityService<Moon, Integer, R_get_universe_moons_moon_id, MoonRepository> {

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

	protected void updateResponseOk(Moon data, R_get_universe_moons_moon_id response,
	    Map<Integer, SolarSystem> idToSystem) {
		data.setSolarSystem(idToSystem.get(response.system_id));
	}

	@Override
	protected void updateResponseOk(Map<Moon, R_get_universe_moons_moon_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, SolarSystem> idToSystem = solarSystemService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.system_id).distinct().toList());
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToSystem));
	}

}
