package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff.OrderCreationRepository;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.CacheInvalidator;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.DiscoveringRemoteNumberEntityUpdater;
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
public class MarketRegionUpdater
		extends
			DiscoveringRemoteNumberEntityUpdater<
					MarketRegion,
					Integer,
					R_get_markets_region_id_orders[],
					MarketRegionRepository,
					MarketRegionService> {

	@Lazy
	private final MarketLineService marketLineService;

	@Lazy
	private final TempMarketLineService tempMarketLineService;

	private final OrderCreationRepository orderCreationRepository;

	@Override
	protected Requested<R_get_markets_region_id_orders[]> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_markets_region_id_orders[]> ret =
				ESIRawPublic.INSTANCE
						.requestGetPages((p, props) -> ESIRawPublic.INSTANCE.get_markets_orders(
								order_type.all,
								p, id, null, props), properties)
						.mapBody(l -> l.toArray(R_get_markets_region_id_orders[]::new));
		return ret;
	}

	public boolean useTempTable = true;

	@Override
	protected void updateResponseOk(Map<MarketRegion, R_get_markets_region_id_orders[]> responseOk) {
		long startms = System.currentTimeMillis();
		log.trace("applying {} market orders updates for regions {}", responseOk.size(),
				responseOk.keySet().stream().map(MarketRegion::getId).toList());
		super.updateResponseOk(responseOk);
		if (useTempTable) {
			updateTempTable(responseOk);
			return;
		}

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
		marketLineService.insertAll(translated);
		long postSaved = System.currentTimeMillis();
		log.trace(" saved {} orders for {} regions in {}s, at {} orders/s", translated.size(), responseOk.size(),
				(postSaved - postCreated) / 1000,
				postSaved - postCreated == 0 ? 0 : translated.size() * 1000 / (postSaved - postCreated));

	}

	/**
	 * update the list of orders by puting them in temp table first
	 *
	 * @param responseOk
	 */
	protected void updateTempTable(Map<MarketRegion, R_get_markets_region_id_orders[]> responseOk) {
		tempMarketLineService.clear();
		List<TempMarketLine> translated = new ArrayList<>();
		for (Entry<MarketRegion, R_get_markets_region_id_orders[]> e : responseOk.entrySet()) {
			translated.addAll(createTempForRegion(e.getKey(), e.getValue()));
		}
		tempMarketLineService.insertAll(translated);

		// process the changes
		for (MarketRegion r : responseOk.keySet()) {
			log.trace("  creating new orders for region {}", r.getId());
			int newOrders =
						orderCreationRepository.addFromTempTable(r.getId(),
							r.getPreviousLastModified());
			if (newOrders > 0) {
				log.debug("  created {} new orders for region {}", newOrders, r.getId());
			}
		}

		marketLineService.clearRegions(responseOk.keySet());
		marketLineService.copyFromTemp();
	}

	protected List<MarketLine> createForRegion(MarketRegion region, R_get_markets_region_id_orders[] orders) {
		return Stream.of(orders)
				.map(order -> MarketLine.of(order, region.getId(), region.getLastModified()))
				.toList();
	}

	protected List<TempMarketLine> createTempForRegion(MarketRegion region, R_get_markets_region_id_orders[] orders) {
		return Stream.of(orders)
				.map(order -> TempMarketLine.of(order, region.getId(), region.getLastModified()))
				.toList();
	}

	@Override
	protected Requested<List<Integer>> discoverRemoteIds(Map<String, String> p) {
		return ESIRawPublic.INSTANCE.get_universe_regions(p).mapBody(List::of);
	}

	/**
	 * try to fetch only that amount of lines at a time, to reduce server DB hogging
	 */
	private final static long TARGET_LINE_FETCH = 400000;

	@Override
	protected List<MarketRegion> listToUpdate() {
		List<MarketRegion> ret = super.listToUpdate();
		if (ret == null || ret.isEmpty()) {
			return ret;
		}
		List<MarketRegion> reduced = new ArrayList<>();
		long totalDoneLines = 0L;
		for (MarketRegion mr : ret) {
			totalDoneLines += mr.getNbLines();
			if (totalDoneLines + mr.getNbLines() > TARGET_LINE_FETCH) {
				if (reduced.isEmpty()) {
					reduced.add(mr);
					totalDoneLines += mr.getNbLines();
				}
				if (reduced.size() != ret.size()) {
					log.trace("  market regions list reduced to {}, total last lines={}, max is {}",
							reduced.size(),
							totalDoneLines,
							TARGET_LINE_FETCH);
					return reduced;
				}
			} else {
				reduced.add(mr);
				totalDoneLines += mr.getNbLines();
			}
		}
		log.trace("  market regions list kept to {}, total {} fetched lines, max={}",
				ret.size(),
				totalDoneLines,
				TARGET_LINE_FETCH);
		return ret;
	}

	// cache

	/**
	 * notified when one+ region has its orders modified
	 */
	public interface MarketRegionListener extends CacheInvalidator {
	}

	@Getter
	@Lazy
	private final Optional<List<MarketRegionListener>> listeners;

}
