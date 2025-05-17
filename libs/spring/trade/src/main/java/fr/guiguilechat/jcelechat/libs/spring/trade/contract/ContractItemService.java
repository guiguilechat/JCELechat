package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.category.Category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractItemService {

	private final ContractItemRepository repo;

	public ContractItem save(ContractItem entity) {
		return repo.saveAndFlush(entity);
	}

	public List<ContractItem> saveAll(Iterable<ContractItem> entities) {
		return repo.saveAllAndFlush(entities);
	}


	public List<Category> categories() {
		return repo.listCategories();
	}

}
