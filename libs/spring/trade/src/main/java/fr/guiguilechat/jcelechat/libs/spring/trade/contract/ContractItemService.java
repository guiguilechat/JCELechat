package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Category;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import jakarta.transaction.Transactional;
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

	@Transactional
	public Stream<MarketOrder> streamBOs(Collection<Integer> typeIds) {
		return repo.listBOs(typeIds).map(MarketOrder::of);
	}

	@Transactional
	public Stream<MarketOrder> streamSOs(Collection<Integer> typeIds) {
		return repo.listSOs(typeIds).map(MarketOrder::of);
	}


	public List<Category> categories() {
		return repo.listCategories();
	}

}
