package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.market")
@Order(4) // depends on region, type
public class MarketRegionService
    extends ARemoteEntityService<MarketRegion, Integer, R_get_markets_region_id_orders[], MarketRegionRepository> {

	@Lazy
	private final MarketLineService marketLineService;

	@Lazy
	private final RegionService regionService;

	@Lazy
	private final SolarSystemService solarSystemService;

	@Lazy
	private final TypeService typeService;

	@Override
	protected Requested<R_get_markets_region_id_orders[]> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_markets_region_id_orders[]> ret = ESIRawPublic.INSTANCE
		    .requestGetPages((p, props) -> ESIRawPublic.INSTANCE.get_markets_orders(
		        order_type.all,
		        p, id, null, props), properties)
		    .mapBody(l -> l.toArray(R_get_markets_region_id_orders[]::new));
		return ret;
	}

	@Override
	protected MarketRegion create(Integer entityId) {
		MarketRegion ret = new MarketRegion();
		ret.setId(entityId);
		ret.setRegion(regionService.createIfAbsent(entityId));
		return ret;
	}

	@Override
	protected void updateResponseOk(Map<MarketRegion, R_get_markets_region_id_orders[]> responseOk) {
		long startms = System.currentTimeMillis();
		log.trace("applying {} market orders updates for regions {}", responseOk.size(),
		    responseOk.keySet().stream().map(MarketRegion::getId).toList());
		super.updateResponseOk(responseOk);

		marketLineService.clearRegions(responseOk.keySet());
		long postClear = System.currentTimeMillis();
		log.trace(" removed all existing orders for the {} regions in {}s", responseOk.size(),
		    (postClear - startms) / 1000);

		List<MarketLine> translated = new ArrayList<>();
		for (Entry<MarketRegion, R_get_markets_region_id_orders[]> e : responseOk.entrySet()) {
			translated.addAll(createForRegion(e.getKey(), e.getValue()));
		}
		long postCreated = System.currentTimeMillis();
		log.trace(" translated the {} orders in {}s", translated.size(), (postCreated - postClear) / 1000);
		marketLineService.saveAll(translated);
		long postSaved = System.currentTimeMillis();
		log.trace(" saved {} orders for {} regions in {}s, at {} orders/s", translated.size(), responseOk.size(),
		    (postSaved - postCreated) / 1000,
		    postSaved - postCreated == 0 ? 0 : translated.size() * 1000 / (postSaved - postCreated));
	}

	public List<MarketLine> createForRegion(MarketRegion region, R_get_markets_region_id_orders[] orders) {
		return Stream.of(orders)
		    .map(order -> MarketLine.of(order, region))
		    .toList();
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_regions(p).mapBody(List::of);
	}

	// cache

	/**
	 * notified when one+ region has its orders modified
	 */
	public static interface MarketRegionListener extends EntityUpdateListener {
	}

	@Getter
	@Lazy
	private final Optional<List<MarketRegionListener>> listeners;

}
