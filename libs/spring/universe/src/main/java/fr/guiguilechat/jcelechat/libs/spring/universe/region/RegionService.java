package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.region")
@Order(1)
public class RegionService
    extends ARemoteEntityService<Region, Integer, R_get_universe_regions_region_id, RegionRepository>
    implements SdeUpdateListener {

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

	protected int nbRegionsLoaded;

	@Override
	public void beforeSdeUpdate() {
		nbRegionsLoaded = 0;
	}

	static final Pattern ENTRYNAME_REGION_PATTERN = Pattern.compile(
	    "universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/region\\.yaml");

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		Matcher matcher = ENTRYNAME_REGION_PATTERN.matcher(entryName);
		if (matcher.matches()) {
			try (InputStream is = fileContent.get()) {
				Region r = createIfAbsent(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ERegion.from(is).regionID);
				r.setUniverse(matcher.group(1));
				save(r);
				nbRegionsLoaded++;
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
	}

	@Override
	public void afterSdeUpdate() {
		log.info("updated {} regions", nbRegionsLoaded);
	}

	//
	// usage
	//

	public Map<Integer, String> namesById() {
		return repo().findAll().stream().collect(Collectors.toMap(Region::getId, Region::name));
	}

	public List<Region> byName(String name) {
		return repo().findByNameEqualsIgnoreCase(name);
	}

	public List<Region> byUniverse(String universe) {
		return repo().findByUniverse(universe);
	}

	public List<String> listUniverses() {
		return repo().listUniverses();
	}

	public List<Integer> listIdsByUniverse(String universe) {
		return repo().listIdsByUniverse(universe);
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

}
