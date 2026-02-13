package fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfoUpdater.ContractItemsListener;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.aggregated.AggregatedDailyHistory;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.aggregated.AggregatedDailyHistoryService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketRegionUpdater.MarketRegionListener;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.tools.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Facade to provide integration of market history/offers with contract
 * history/offers, for unresearched types only (stacks or 0/0 contracts and
 * sales)
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractMarketAggregator implements ContractItemsListener, MarketRegionListener {

	@Lazy
	private final AggregatedDailyHistoryService aggregatedDailyHistoryService;

	@Lazy
	private final ContractFacadeBpo contractFacadeBpo;

	@Lazy
	private final ContractFacadeBpc contractFacadeBpc;

	@Lazy
	private final ContractFacadeNonBp contractFacadeNonBp;

	@Lazy
	private final MarketLineService marketLineService;

	@Lazy
	final private HistoryLineService historyLineService;

	@Lazy
	private final StationService stationService;

	public void resolveLocationNames(List<MarketOrder> orders) {
		Map<Integer, String> stationId2Name = stationService.resolveNames(orders.stream()
				.filter(mo -> mo.getLocationId() < Integer.MAX_VALUE)
				.map(mo -> (int) mo.getLocationId())
				.distinct().toList());
		for (MarketOrder mo : orders) {
			mo.resolveLocationName(stationId2Name, Map.of());
		}
	}

	/**
	 * @param typeId
	 * @return the merged contract and regional market orders to sell given type,
	 *         0ME, 0TE, noncopy, for isks only
	 */
	@Cacheable("MarketOrdersSellOrdersForType")
	public List<MarketOrder> sellOrders(int typeId) {
		List<MarketOrder> ret = Stream.concat(
				contractFacadeNonBp.streamSOs(typeId),
				marketLineService.streamSOs(typeId))
				.sorted(Comparator.comparing(MarketOrder::getPrice))
				.toList();
		resolveLocationNames(ret);
		return ret;
	}

	@Cacheable("MarketOrdersBuyOrdersForType")
	public List<MarketOrder> buyOrders(int typeId) {
		List<MarketOrder> ret = Stream.concat(
				contractFacadeNonBp.streamBOs(typeId),
				marketLineService.streamBOs(typeId))
				.sorted(Comparator.comparing(mo -> -mo.getPrice()))
				.toList();
		resolveLocationNames(ret);
		return ret;
	}

	@Cacheable("MarketOrdersLowestSellForType")
	public Map<Integer, Double> lowestSellByRegion(int typeId) {
		return Stream
				.concat(contractFacadeNonBp.streamSOs(typeId),
						marketLineService.streamSOs(List.of(typeId)).map(MarketOrder::of))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).min().orElse(Double.POSITIVE_INFINITY)));
	}

	@Cacheable("MarketOrdersHighestBuyForType")
	public Map<Integer, Double> highestBuyByRegion(int typeId) {
		return Stream
				.concat(contractFacadeNonBp.streamBOs(typeId),
						marketLineService.streamBOs(typeId))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).max().orElse(Double.POSITIVE_INFINITY)));
	}

	public List<AggregatedHL> aggregatedSales(int typeId, Instant from) {
		return aggregatedDailyHistoryService.typeHistory(typeId, from.atOffset(ZoneOffset.UTC).toLocalDate(), null)
				.stream()
				.map(AggregatedDailyHistory::toAggregtedHL)
				.toList();
	}

	@Cacheable("MarketHistoryAggregatedPresent")
	public boolean hasHistory(int typeId, int me, int te, boolean copy) {
		return aggregatedDailyHistoryService.hasEntry(typeId, me, te, copy);
	}

	@Getter
	private final List<String> cacheList = List.of(
			"MarketHistoryAggregatedPresent",
			"MarketOrdersSellOrdersForType",
			"MarketOrdersBuyOrdersForType",
			"MarketOrdersLowestSellForType",
			"MarketOrdersHighestBuyForType");

}
