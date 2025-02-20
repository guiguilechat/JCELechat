package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Category;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface ContractItemRepository extends IFetchedListElementRepository<ContractInfo, ContractItem> {

	@Query("""
select
	distinct(type.group.category)
from
	EsiMarketContractItem
""") List<Category> listCategories();

}
