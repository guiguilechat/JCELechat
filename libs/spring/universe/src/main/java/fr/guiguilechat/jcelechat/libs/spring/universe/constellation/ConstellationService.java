package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.constellation")
@Order(2)
public class ConstellationService
    extends
    ARemoteFetchedResourceService<Constellation, Integer, R_get_universe_constellations_constellation_id, ConstellationRepository> {

	@Lazy
	private final RegionService regionService;

	@Override
	protected Constellation create(Integer entityId) {
		Constellation ret = new Constellation();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_constellations_constellation_id> fetchData(Integer id,
	    Map<String, String> properties) {
		Requested<R_get_universe_constellations_constellation_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_constellations(id, properties);
		return ret;
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_constellations(p).mapBody(List::of);
	}

	@Override
	protected void updateResponseOk(Constellation data,
	    Requested<R_get_universe_constellations_constellation_id> response) {
		super.updateResponseOk(data, response);
		data.setRegion(regionService.createIfAbsent(response.getOK().region_id));
	}

}
