package fr.guiguilechat.jcelechat.libs.spring.universe.star;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stars_star_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.stargate")
// depend on solarsystem type
@Order(4)
public class StarService extends
    ARemoteFetchedResourceService<Star, Integer, R_get_universe_stars_star_id, StarRepository> {

	@Lazy
	private final SolarSystemService solarSystemService;

	@Lazy
	private final TypeService typeService;

	@Override
	protected Star create(Integer entityId) {
		Star ret = new Star();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_stars_star_id> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_universe_stars_star_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_stars(id, properties);
		return ret;
	}

	@Override
	protected void updateResponseOk(Star data, Requested<R_get_universe_stars_star_id> response) {
		super.updateResponseOk(data, response);
		data.setSolarSystem(solarSystemService.createIfAbsent(response.getOK().solar_system_id));
		data.setType(typeService.createIfAbsent(response.getOK().type_id));
	}

}
