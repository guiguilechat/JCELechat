package fr.guiguilechat.jcelechat.libs.spring.market.contract;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.order.MarketOrder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractInfoService {

	final private ContractInfoRepository repo;

	public ContractInfo save(ContractInfo entity) {
		return repo.save(entity);
	}

	public List<ContractInfo> saveAll(Iterable<ContractInfo> entities) {
		return repo.saveAll(entities);
	}

	public Map<Long, ContractInfo> allPresentInRegionById(int regionId) {
		return repo.findByRegionRegionIdAndRemovedFalse(regionId).stream()
				.collect(Collectors.toMap(ContractInfo::getContractId, rc -> rc));
	}

	/**
	 * @return the next 1000 contracts that need to be fetched their items
	 */
	public List<ContractInfo> nextFetch() {
		return repo.findTop1000ByTypeAndFetchedFalseAndRemovedFalseOrderByContractId(
				get_contracts_public_region_id_type.item_exchange);
	}

	public int countMissingFetch() {
		return repo.countMissingItems(get_contracts_public_region_id_type.item_exchange);
	}

	@Transactional
	public Stream<MarketOrder> streamBOs(int typeId) {
		return repo.listBOs(typeId).map(MarketOrder::of);
	}

	@Transactional
	public Stream<MarketOrder> streamSOs(int typeId) {
		return repo.listSOs(typeId).map(MarketOrder::of);
	}
}
