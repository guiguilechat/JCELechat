package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

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
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResourceService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
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
    extends ARemoteResourceService<MarketRegion, Integer, R_get_markets_region_id_orders[], MarketRegionRepository> {

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
		log.debug("got {} response updates", responseOk.size());
		super.updateResponseOk(responseOk);
		Map<Integer, SolarSystem> idToSolarSystem = solarSystemService.createIfAbsent(
		    responseOk.values()
		        .stream().flatMap(Stream::of)
		        .map(o -> o.system_id)
		        .distinct().toList());
		log.trace("got {} solar systems to set", idToSolarSystem.size());

		Map<Integer, Type> idToType = typeService.createIfAbsent(
		    responseOk.values()
		        .stream().flatMap(Stream::of)
		        .map(o -> o.type_id)
		        .distinct().toList());
		log.trace("got {} types to set", idToType.size());

		for (Entry<MarketRegion, R_get_markets_region_id_orders[]> e : responseOk.entrySet()) {
			createForRegion(e.getKey(), e.getValue(), idToSolarSystem, idToType);
		}
	}

	public void createForRegion(MarketRegion region, R_get_markets_region_id_orders[] orders,
	    Map<Integer, SolarSystem> idToSolarSystem,
	    Map<Integer, Type> idToType) {
		marketLineService.clearRegion(region);
		log.trace("removed all orders for region id {}", region.getId());
		List<MarketLine> newOrders = marketLineService.saveAll(
		    Stream.of(orders)
		        .map(order -> MarketLine.of(order, region, idToSolarSystem.get(order.system_id),
		            idToType.get(order.type_id)))
		        .toList());
		log.trace(" saved {} orders for region id {}", newOrders.size(), region.getId());
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
