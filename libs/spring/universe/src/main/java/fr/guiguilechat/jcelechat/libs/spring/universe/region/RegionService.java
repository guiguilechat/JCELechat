package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.region")
@Order(1)
public class RegionService
    extends ARemoteFetchedResourceService<Region, Integer, R_get_universe_regions_region_id, RegionRepository> {

	@Override
	protected Region create(Integer entityId) {
		Region ret = new Region();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_regions_region_id> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_universe_regions_region_id> ret = ESIRawPublic.INSTANCE.get_universe_regions(id, properties);
		return ret;
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_regions(p).mapBody(List::of);
	}

	public Map<Integer, String> namesById() {
		return repo().findAll().stream().collect(Collectors.toMap(Region::getId, Region::getName));
	}

	public List<Region> byName(String name) {
		return repo().findByNameEqualsIgnoreCase(name);
	}

public List<Region> byUniverse(String universe) {
	return repo().findByUniverse(universe);
}

}
