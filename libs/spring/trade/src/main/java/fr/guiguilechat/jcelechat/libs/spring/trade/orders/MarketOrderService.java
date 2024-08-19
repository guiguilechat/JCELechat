package fr.guiguilechat.jcelechat.libs.spring.trade.orders;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
	private final MarketLineService regionLineService;

	@Lazy
	private final ContractItemService contractItemService;

	@Transactional
	@Cacheable("MarketOrdersSellOrdersForTypes")
	public List<MarketOrder> sellOrders(Collection<Integer> typeIds) {
		return Stream.concat(contractItemService.streamSOs(typeIds), regionLineService.streamSOs(typeIds))
				.sorted(Comparator.comparing(MarketOrder::getPrice))
				.toList();
	}

	@Transactional
	@Cacheable("MarketOrdersBuyOrdersForTypes")
	public List<MarketOrder> buyOrders(Collection<Integer> typeIds) {
		return Stream.concat(contractItemService.streamBOs(typeIds), regionLineService.streamBOs(typeIds))
				.sorted(Comparator.comparing(mo -> -mo.getPrice()))
				.toList();
	}

	@Transactional
	@Cacheable("MarketOrdersLowestSellForType")
	public Map<Integer, Double> lowestSellByRegion(int typeId) {
		return Stream.concat(contractItemService.streamSOs(List.of(typeId)), regionLineService.streamSOs(List.of(typeId)))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).min().orElse(Double.POSITIVE_INFINITY)));
	}

	@Transactional
	@Cacheable("MarketOrdersHighestBuyForType")
	public Map<Integer, Double> highestBuyByRegion(int typeId) {
		return Stream.concat(contractItemService.streamBOs(List.of(typeId)), regionLineService.streamBOs(List.of(typeId)))
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
