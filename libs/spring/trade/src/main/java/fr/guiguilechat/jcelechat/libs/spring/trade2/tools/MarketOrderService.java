package fr.guiguilechat.jcelechat.libs.spring.trade2.tools;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService.ContractItemsListener;
import fr.guiguilechat.jcelechat.libs.spring.trade2.regional.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractItemService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Facade to unify the contract and market orders.
 */
@Service
@RequiredArgsConstructor
public class MarketOrderService implements ContractItemsListener {

	private final RegionLineService regionLineService;

	private final ContractItemService contractItemService;

	@Transactional
	@Cacheable("MarketOrdersSellOrdersForType")
	public List<MarketOrder> sellOrders(int typeId) {
		return Stream.concat(contractItemService.streamSOs(typeId), regionLineService.streamSOs(typeId))
				.sorted(Comparator.comparing(MarketOrder::getPrice))
				.toList();
	}

	@Transactional
	@Cacheable("MarketOrdersBuyOrdersForType")
	public List<MarketOrder> buyOrders(int typeId) {
		return Stream.concat(contractItemService.streamBOs(typeId), regionLineService.streamBOs(typeId))
				.sorted(Comparator.comparing(mo -> -mo.getPrice()))
				.toList();
	}

	@Transactional
	@Cacheable("MarketOrdersLowestSellForType")
	public Map<Integer, Double> lowestSellByRegion(int typeId) {
		return Stream.concat(contractItemService.streamSOs(typeId), regionLineService.streamSOs(typeId))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).min().orElse(Double.POSITIVE_INFINITY)));
	}

	@Transactional
	@Cacheable("MarketOrdersHighestBuyForType")
	public Map<Integer, Double> highestBuyByRegion(int typeId) {
		return Stream.concat(contractItemService.streamBOs(typeId), regionLineService.streamBOs(typeId))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).max().orElse(Double.POSITIVE_INFINITY)));
	}

	@Getter
	private final List<String> cacheList = List.of(
	    "MarketOrdersSellOrdersForType",
	    "MarketOrdersBuyOrdersForType",
	    "MarketOrdersLowestSellForType",
	    "MarketOrdersHighestBuyForType");

}
