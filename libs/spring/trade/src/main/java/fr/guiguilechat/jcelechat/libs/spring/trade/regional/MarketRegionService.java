package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

import java.util.ArrayList;
import java.util.HashMap;
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
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystemService;
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

	boolean saveUseIncrement = false;

	@Override
	protected void updateResponseOk(Map<MarketRegion, R_get_markets_region_id_orders[]> responseOk) {
		if (saveUseIncrement) {
			updateWithIncrement(responseOk);
		} else {
			updateNoIncrement(responseOk);
		}
	}

	protected void updateNoIncrement(Map<MarketRegion, R_get_markets_region_id_orders[]> responseOk) {
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
		marketLineService.createAll(translated);
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

	protected void updateWithIncrement(Map<MarketRegion, R_get_markets_region_id_orders[]> responseOk) {
		long startms = System.currentTimeMillis();
		log.trace("applying {} market orders updates for regions {}", responseOk.size(),
				responseOk.keySet().stream().map(MarketRegion::getId).toList());
		List<MarketLine> updated = new ArrayList<>(),
				created = new ArrayList<>(),
				deleted = new ArrayList<>()
		;
		for (Entry<MarketRegion, R_get_markets_region_id_orders[]> e : responseOk.entrySet()) {
			updateWithIncrement(e.getKey(), e.getValue(), updated, created, deleted);
		}
		long postProcess = System.currentTimeMillis();
		log.debug("  processed the orders in {} s", (postProcess - startms) / 1000);
		if (!updated.isEmpty()) {
			marketLineService.saveAll(updated);
		}
		long postUpdate = System.currentTimeMillis();
		log.debug("  updated {} orders in {} s", updated.size(), (postUpdate - postProcess) / 1000);
		if (!deleted.isEmpty()) {
			marketLineService.deleteAll(deleted);
		}
		long postDelete = System.currentTimeMillis();
		log.debug("  deleted {} orders in {} s", deleted.size(), (postDelete - postUpdate) / 1000);
		if (!created.isEmpty()) {
			marketLineService.createAll(created);
		}
		long postSaved = System.currentTimeMillis();
		log.debug("  created {} orders in {} s", created.size(), (postSaved - postDelete) / 1000);
		log.debug("saved market orders for {} regions in {}s",
				responseOk.size(),
				(postSaved - startms) / 1000);
	}

	protected void updateWithIncrement(MarketRegion mr, R_get_markets_region_id_orders[] receivedOrders,
			List<MarketLine> updated, List<MarketLine> created, List<MarketLine> deleted) {
		long startms = System.currentTimeMillis();
		log.trace(" fetching the existing orders in region {}", mr.getRegion().getId());
		Map<Long, MarketLine> storedOrders = marketLineService.cachedOrders(mr.getRegion().getId());
		long postFetch = System.currentTimeMillis();
		log.trace("  fetched {} orders of region {} in {} s", storedOrders.size(), mr.getRegion().getId(),
				(postFetch - startms) / 1000);

		Map<Long, MarketLine> toRemoveOrders = new HashMap<>(storedOrders);
		Map<Long, MarketLine> regionOrdersAfter = new HashMap<>();
		for (R_get_markets_region_id_orders ro : receivedOrders) {
			// order still exists so not to be removed
			toRemoveOrders.remove(ro.order_id);
			MarketLine existing = storedOrders.get(ro.order_id);
			if (existing == null) {
				existing = MarketLine.of(ro, mr);
				created.add(existing);
			} else {
				boolean changed = existing.updateReceived(ro);
				if (changed) {
					updated.add(existing);
				}
			}
			regionOrdersAfter.put(existing.getOrderId(), existing);
		}
		deleted.addAll(toRemoveOrders.values());
		marketLineService.updateCachedOrders(mr.getRegion().getId(), regionOrdersAfter);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_regions(p).mapBody(List::of);
	}

	// cache

	/**
	 * notified when one+ region has its orders modified
	 */
	public interface MarketRegionListener extends EntityUpdateListener {
	}

	@Getter
	@Lazy
	private final Optional<List<MarketRegionListener>> listeners;

}
