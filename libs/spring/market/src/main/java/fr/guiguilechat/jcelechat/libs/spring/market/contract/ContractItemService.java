package fr.guiguilechat.jcelechat.libs.spring.market.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractItemService {

	private final ContractItemRepository repo;

	public ContractItem save(ContractItem entity) {
		return repo.save(entity);
	}

	public List<ContractItem> saveAll(Iterable<ContractItem> entities) {
		return repo.saveAll(entities);
	}

}
