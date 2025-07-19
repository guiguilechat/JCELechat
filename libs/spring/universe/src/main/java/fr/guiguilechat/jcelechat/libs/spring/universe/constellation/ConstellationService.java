package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.constellation")
@Order(2)
public class ConstellationService
    extends
    ARemoteEntityService<Constellation, Integer, R_get_universe_constellations_constellation_id, ConstellationRepository> {

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

	protected void updateResponseOk(Constellation data,
	    R_get_universe_constellations_constellation_id response,
	    Map<Integer, Region> idToRegion) {
		data.setRegion(idToRegion.get(response.region_id));
	}

	@Override
	protected void updateResponseOk(Map<Constellation, R_get_universe_constellations_constellation_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, Region> idToRegion = regionService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.region_id).distinct().toList());
		responseOk.entrySet().stream().forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToRegion));
	}

	//
	// usage
	//

	public List<Integer> listIdsByUniverse(String universe) {
		return repo().listIdsByUniverse(universe);
	}

	public List<Integer> listIdsByRegionId(int regionId) {
		return repo().listIdsByRegionId(regionId);
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

}
