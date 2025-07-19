package fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt;

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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.asteroidbelt")
// depend on solarsystem
@Order(4)
public class AsteroidBeltService extends
    ARemoteEntityService<AsteroidBelt, Integer, R_get_universe_asteroid_belts_asteroid_belt_id, AsteroidBeltRepository> {

	@Lazy
	private final SolarSystemService solarSystemService;

	@Override
	protected AsteroidBelt create(Integer entityId) {
		AsteroidBelt ret = new AsteroidBelt();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_asteroid_belts_asteroid_belt_id> fetchData(Integer id,
	    Map<String, String> properties) {
		Requested<R_get_universe_asteroid_belts_asteroid_belt_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_asteroid_belts(id, properties);
		return ret;
	}

	protected void updateResponseOk(AsteroidBelt data,
	    R_get_universe_asteroid_belts_asteroid_belt_id response,
	    Map<Integer, SolarSystem> idToSystem) {
		data.setSolarSystem(idToSystem.get(response.system_id));
	}

	@Override
	protected void updateResponseOk(Map<AsteroidBelt, R_get_universe_asteroid_belts_asteroid_belt_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, SolarSystem> idToSystem = solarSystemService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.system_id).distinct().toList());
		responseOk.entrySet().stream().forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToSystem));
	}

}
