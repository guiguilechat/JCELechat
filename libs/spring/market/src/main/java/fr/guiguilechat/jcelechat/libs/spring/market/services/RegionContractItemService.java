package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionContractItem;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.RegionContractItemRepository;
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
