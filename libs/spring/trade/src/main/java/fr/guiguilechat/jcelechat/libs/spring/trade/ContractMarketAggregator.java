package fr.guiguilechat.jcelechat.libs.spring.trade;

import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeBpo;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeNonBp;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService.ContractItemsListener;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.AggregatedHL;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketRegionService.MarketRegionListener;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
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

	@Transactional
	@Cacheable("MarketOrdersSellOrdersForTypes")
	public Map<Integer, List<MarketOrder>> sellOrders(Collection<Integer> typeIds, Set<Long> alllowedLocations) {
		Map<Integer, List<MarketOrder>> ret = Stream
				.concat(contractFacadeNonBp.streamSOs(typeIds), marketLineService.streamSOs(typeIds).map(MarketOrder::of))
				.filter(mo -> alllowedLocations == null || alllowedLocations.contains(mo.getLocationId()))
				.collect(Collectors.groupingBy(MarketOrder::getTypeId));
		ret.values().forEach(l -> l.sort(Comparator.comparing(MarketOrder::getPrice)));
		return ret;
	}

	public List<MarketOrder> sellOrders(int typeId) {
		return sellOrders(List.of(typeId), null).getOrDefault(typeId, List.of());
	}

	@Transactional
	@Cacheable("MarketOrdersBuyOrdersForTypes")
	public Map<Integer, List<MarketOrder>> buyOrders(Collection<Integer> typeIds, Set<Long> alllowedLocations) {
		Map<Integer, List<MarketOrder>> ret = Stream
				.concat(contractFacadeNonBp.streamBOs(typeIds), marketLineService.streamBOs(typeIds).map(MarketOrder::of))
				.filter(mo -> alllowedLocations == null || alllowedLocations.contains(mo.getLocationId()))
				.collect(Collectors.groupingBy(MarketOrder::getTypeId));
		ret.values().forEach(l -> l.sort(Comparator.comparing(mo -> -mo.getPrice())));
		return ret;
	}

	public List<MarketOrder> buyOrders(int typeId) {
		return buyOrders(List.of(typeId), null).getOrDefault(typeId, List.of());
	}

	@Transactional
	@Cacheable("MarketOrdersLowestSellForType")
	public Map<Integer, Double> lowestSellByRegion(int typeId) {
		return Stream
				.concat(contractFacadeNonBp.streamSOs(List.of(typeId)),
						marketLineService.streamSOs(List.of(typeId)).map(MarketOrder::of))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).min().orElse(Double.POSITIVE_INFINITY)));
	}

	@Transactional
	@Cacheable("MarketOrdersHighestBuyForType")
	public Map<Integer, Double> highestBuyByRegion(int typeId) {
		return Stream
				.concat(contractFacadeNonBp.streamBOs(List.of(typeId)),
						marketLineService.streamBOs(List.of(typeId)).map(MarketOrder::of))
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
		log.debug("fetched history for type {} since {} in {} ms (fetch {} market in {}, {} contract in {}, merge in {})",
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

	@Getter
	private final List<String> cacheList = List.of(
			"MarketOrdersSellOrdersForTypes",
			"MarketOrdersBuyOrdersForTypes",
			"MarketOrdersLowestSellForType",
			"MarketOrdersHighestBuyForType");

}
