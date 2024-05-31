package fr.guiguilechat.jcelechat.libs.spring.market.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionContractItemService {

	private final RegionContractItemRepository repo;

	public RegionContractItem save(RegionContractItem entity) {
		return repo.save(entity);
	}

	public List<RegionContractItem> saveAll(Iterable<RegionContractItem> entities) {
		return repo.saveAll(entities);
	}

}
