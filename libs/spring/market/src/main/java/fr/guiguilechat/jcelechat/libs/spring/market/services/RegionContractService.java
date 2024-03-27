package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionContract;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.RegionContractRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionContractService {

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

	public List<RegionContract> nextFetch(int regionId) {
		return repo.findByRegionRegionIdAndFetchedFalseAndRemovedFalseAndType(regionId,
				get_contracts_public_region_id_type.item_exchange);
	}
}
