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
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff.OrderDeletionRepository;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff.OrderSaleRepository;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff.OrderUpdateRepository;
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

	private final OrderDeletionRepository orderDeletionRepository;

	private final OrderUpdateRepository orderUpdateRepository;

	private final OrderSaleRepository orderSaleRepository;

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
		for (Entry<MarketRegion, R_get_markets_region_id_orders[]> e : responseOk.entrySet()) {
			MarketRegion r = e.getKey();
			int rid = r.getId();

			// put lines in the temp table
			// delete from seems to keep records, so truncate instead
			long preDump = System.currentTimeMillis();
			tempMarketLineService.truncate();
			List<TempMarketLine> created = createTempForRegion(r, e.getValue());
			if (!created.isEmpty()) {
				tempMarketLineService.insertAll(created);
				long postDump = System.currentTimeMillis();
				log.debug("  dumped {} records for region {} in {}ms, @{}r/s",
						created.size(),
						rid,
						postDump - preDump,
						1000 * created.size() / Math.max(1, postDump - preDump));

				// process new orders
				long preNew = System.currentTimeMillis();
				int newOrders =
						orderCreationRepository.addFromTempTable(rid, r.getPreviousLastModified());
				long postNew = System.currentTimeMillis();
				if (newOrders > 0) {
					log.debug("  added {} order creations for region {} in {}ms, @{}i/s", newOrders, rid,
							postNew - preNew,
							1000 * newOrders / Math.max(1, postNew - preNew));
				}

				// updated orders
				long preUpdate = System.currentTimeMillis();
				int updatedorders = orderUpdateRepository.addFromTempTable();
				long postUpdate = System.currentTimeMillis();
				if (updatedorders > 0) {
					log.debug("  added {} order updates for region {} in {} ms, @{}i/s", updatedorders, rid,
							postUpdate - preUpdate, 1000 * updatedorders / Math.max(1, postUpdate - preUpdate));
				}

				// sales
				long preSales = System.currentTimeMillis();
				int salesOrders = orderSaleRepository.addFromTempTable();
				long postSales = System.currentTimeMillis();
				if (salesOrders > 0) {
					log.debug("  added {} order sales for region {} in {} ms, @{}i/s", salesOrders, rid,
							postSales - preSales, 1000 * salesOrders / Math.max(1, postSales - preSales));
				}
			}

			// process orders deletion
			long preDelete = System.currentTimeMillis();
			int deletedOrders =
					orderDeletionRepository.addFromTempTable(rid, r.getPreviousLastModified(), r.getLastModified());
			long postDelete = System.currentTimeMillis();
			if (deletedOrders > 0) {
				log.debug("  added {} order deletions for region {} in {}ms, @{}i/s", deletedOrders, rid,
						postDelete - preDelete,
						1000 * deletedOrders / Math.max(1, postDelete - preDelete));
			}

			if (!created.isEmpty()) {
				// move data from temp to the actual
				long preMove = System.currentTimeMillis();
				marketLineService.clearRegions(rid);
				marketLineService.copyFromTemp();
				long postMove = System.currentTimeMillis();
				log.debug("  moved {} records for region {} in {} ms, @ {}i/s",
						created.size(),
						rid,
						postMove - preMove,
						1000 * created.size() / Math.max(1, postMove - preMove));
				log.debug("   processed {} orders for region {} in {}ms", created.size(), rid,
						Math.max(1, postMove - preDump));
			}
		}
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
