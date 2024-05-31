package fr.guiguilechat.jcelechat.libs.spring.market.order;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.contract.RegionContractService;
import fr.guiguilechat.jcelechat.libs.spring.market.contract.RegionContractUpdateService.ContractUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.market.regional.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.regional.RegionMarketUpdateService.MarketUpdateListener;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Facade to unify the contract and market orders.
 */
@Service
@RequiredArgsConstructor
public class MarketOrderService implements ContractUpdateListener, MarketUpdateListener {

	private final RegionLineService regionLineService;

	private final RegionContractService regionContractService;

	@Transactional
	@Cacheable("MarketOrdersSellOrdersForType")
	public List<MarketOrder> sellOrders(int typeId) {
		return Stream.concat(regionContractService.streamSOs(typeId), regionLineService.streamSOs(typeId))
				.sorted(Comparator.comparing(MarketOrder::getPrice))
				.toList();
	}

	@Transactional
	@Cacheable("MarketOrdersBuyOrdersForType")
	public List<MarketOrder> buyOrders(int typeId) {
		return Stream.concat(regionContractService.streamBOs(typeId), regionLineService.streamBOs(typeId))
				.sorted(Comparator.comparing(mo -> -mo.getPrice()))
				.toList();
	}

	@Transactional
	@Cacheable("MarketOrdersLowestSellForType")
	public Map<Integer, Double> lowestSellByRegion(int typeId) {
		return Stream.concat(regionContractService.streamSOs(typeId), regionLineService.streamSOs(typeId))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).min().orElse(Double.POSITIVE_INFINITY)));
	}

	@Transactional
	@Cacheable("MarketOrdersHighestBuyForType")
	public Map<Integer, Double> highestBuyByRegion(int typeId) {
		return Stream.concat(regionContractService.streamBOs(typeId), regionLineService.streamBOs(typeId))
				.collect(Collectors.groupingBy(MarketOrder::getRegionId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						k -> k.getValue().stream().mapToDouble(MarketOrder::getPrice).max().orElse(Double.POSITIVE_INFINITY)));
	}

	/**
	 * cache management
	 */

	private static final List<String> CACHES = List.of(
			"MarketOrdersSellOrdersForType",
			"MarketOrdersBuyOrdersForType",
			"MarketOrdersLowestSellForType",
			"MarketOrdersHighestBuyForType");

	@Override
	public List<String> listContractCaches(int regionId) {
		return CACHES;
	}

	@Override
	public List<String> listMarketCaches(int regionId) {
		return CACHES;
	}

}
