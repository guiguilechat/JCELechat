package fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfoUpdater.ContractItemsListener;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.HistoryLineService;
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
	private final ContractFacadeBpo contractFacadeBpo;

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
		long start = System.currentTimeMillis();
		List<AggregatedHL> marketHistory = historyLineService.byType(typeId, from);
		long postMarketFetch = System.currentTimeMillis();
		List<AggregatedHL> contractHistory = contractFacadeBpo.aggregatedSales(typeId, from, 0, 0);
		long postContractFetch = System.currentTimeMillis();
		Map<? extends Instant, List<AggregatedHL>> byDate = Stream.concat(marketHistory.stream(), contractHistory.stream())
				.collect(Collectors.groupingBy((Function<? super AggregatedHL, ? extends Instant>) AggregatedHL::getDate));
		List<AggregatedHL> ret = byDate.values().stream()
		    .map(l -> l.stream().reduce((BinaryOperator<AggregatedHL>) AggregatedHL::merge).get())
				.sorted(Comparator.comparing(AggregatedHL::getDate))
				.toList();
		long postMerge = System.currentTimeMillis();
		log.trace(
				"fetched history for type {} since {} in {} ms (fetch {} market in {}, {} contract in {}, merge in {})",
				typeId,
				from,
				postMerge - start,
				marketHistory.size(),
				postMarketFetch - start,
				contractHistory.size(),
				postContractFetch - postMarketFetch,
				postMerge - postContractFetch);
		return ret;
	}

	@Cacheable("MarketHistoryAggregatedPresent")
	public boolean hasHistory(int typeId) {
		return historyLineService.hasEntry(typeId)
				|| contractFacadeNonBp.hasEntry(typeId);
	}

	@Getter
	private final List<String> cacheList = List.of(
			"MarketHistoryAggregatedPresent",
			"MarketOrdersSellOrdersForType",
			"MarketOrdersBuyOrdersForType",
			"MarketOrdersLowestSellForType",
			"MarketOrdersHighestBuyForType");

}
