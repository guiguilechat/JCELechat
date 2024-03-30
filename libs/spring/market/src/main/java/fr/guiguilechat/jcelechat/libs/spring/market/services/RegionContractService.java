package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionContract;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.RegionContractRepository;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionContractUpdateService.ContractItemsUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionContractUpdateService.ContractUpdateListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionContractService implements ContractUpdateListener, ContractItemsUpdateListener {

	final private RegionContractRepository repo;

	public RegionContract save(RegionContract entity) {
		return repo.save(entity);
	}

	public List<RegionContract> saveAll(Iterable<RegionContract> entities) {
		return repo.saveAll(entities);
	}

	public Map<Long, RegionContract> allPresentInRegionById(int regionId) {
		return repo.findByRegionRegionIdAndRemovedFalse(regionId).stream()
				.collect(Collectors.toMap(RegionContract::getContractId, rc -> rc));
	}

	/**
	 * @return the next 1000 contracts that need to be fetched their items
	 */
	public List<RegionContract> nextFetch() {
		return repo.findTop1000ByTypeAndFetchedFalseAndRemovedFalseOrderByContractId(
				get_contracts_public_region_id_type.item_exchange);
	}

	public int countMissingFetch() {
		return repo.countMissingItems(get_contracts_public_region_id_type.item_exchange);
	}

	public List<MarketOrder> listBOs(int typeId) {
		return repo.listBOs(typeId).stream().map(MarketOrder::of).toList();
	}

	public List<MarketOrder> listSOs(int typeId) {
		return repo.listSOs(typeId).stream().map(MarketOrder::of).toList();
	}
}
