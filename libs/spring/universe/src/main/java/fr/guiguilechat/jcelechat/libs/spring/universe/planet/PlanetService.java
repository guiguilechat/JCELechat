package fr.guiguilechat.jcelechat.libs.spring.universe.planet;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_planets_planet_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.planet")
// depend on solarsystem type
@Order(4)
public class PlanetService extends
    ARemoteFetchedResourceService<Planet, Integer, R_get_universe_planets_planet_id, PlanetRepository> {

	@Lazy
	private final SolarSystemService solarSystemService;

	@Lazy
	private final TypeService typeService;

	@Override
	protected Planet create(Integer entityId) {
		Planet ret = new Planet();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_planets_planet_id> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_universe_planets_planet_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_planets(id, properties);
		return ret;
	}

	protected void updateResponseOk(Planet data, R_get_universe_planets_planet_id response,
	    Map<Integer, SolarSystem> idToSystem, Map<Integer, Type> idToType) {
		data.setSolarSystem(idToSystem.get(response.system_id));
		data.setType(idToType.get(response.type_id));
	}

	@Override
	protected void updateResponseOk(Map<Planet, R_get_universe_planets_planet_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, SolarSystem> idToSystem = solarSystemService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.system_id).distinct().toList());
		Map<Integer, Type> idToType = typeService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.type_id).distinct().toList());
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToSystem, idToType));
	}

}
