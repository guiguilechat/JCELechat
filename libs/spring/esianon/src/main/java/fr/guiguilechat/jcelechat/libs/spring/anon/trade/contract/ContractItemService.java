package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
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

	public List<ContractItem> loadWithType(ContractInfo contract) {
		return repo.findWithTypeNameByFetchResourceOrderByTypeNameAsc(contract);
	}

}
