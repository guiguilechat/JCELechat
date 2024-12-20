package fr.guiguilechat.jcelechat.libs.spring.trade.orders;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService.ContractItemsListener;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractItemService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketRegionService.MarketRegionListener;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Facade to unify the contract and market orders.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketOrderService implements ContractItemsListener, MarketRegionListener {

	@Lazy
	private final MarketLineService marketLineService;

	@Lazy
	private final ContractItemService contractItemService;

	@Lazy
	private final ContractInfoService contractInfoService;

	@Transactional
	@Cacheable("MarketOrdersSellOrdersForTypes")
	public Map<Integer, List<MarketOrder>> sellOrders(Collection<Integer> typeIds, Set<Long> alllowedLocations) {
		Map<Integer, List<MarketOrder>> ret = Stream
		    .concat(contractItemService.streamSOs(typeIds), marketLineService.streamSOs(typeIds).map(MarketOrder::of))
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
		    .concat(contractItemService.streamBOs(typeIds), marketLineService.streamBOs(typeIds).map(MarketOrder::of))
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
		    .concat(contractItemService.streamSOs(List.of(typeId)),
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
		    .concat(contractItemService.streamBOs(List.of(typeId)),
		        marketLineService.streamBOs(List.of(typeId)).map(MarketOrder::of))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).max().orElse(Double.POSITIVE_INFINITY)));
	}

	@Getter
	private final List<String> cacheList = List.of(
	    "MarketOrdersSellOrdersForTypes",
	    "MarketOrdersBuyOrdersForTypes",
	    "MarketOrdersLowestSellForType",
	    "MarketOrdersHighestBuyForType");


}
